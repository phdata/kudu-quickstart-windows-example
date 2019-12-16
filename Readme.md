# Using Kudu Quickstart on Windows
This repository has a couple of different examples for using the Kudu Quickstart on Microsoft Windows. 

The code examples include the following use cases:

*  Launching the Kudu Quickstart Docker containers  
    *  Using Powershell  
    *  Using the Windows Subsystem for Linux (WSL)  
*  Running a spark application built using Maven in InteliJ  
*  Running the `spark-shell` and interacting with Kudu  

## Prerequisites
The base systems used for building this tutorial had the following prerequisites:  

1. Java 1.8 JDK (Oracle was used for testing)  
1. Scala 2.11.12
1. Maven 3.6
1. Spark 2.4.4
1. IntelliJ Community Edition

## Repo Organization
This example repo has the following root folders:

|Folder | Contents |
|-------|----------|
|spark-app | A sample Spark application that can be imported into IntelliJ using the Maven `pom.xml`. |
|spark-shell-powershell | Scripts to run `spark-shell` with Kudu support. Includes a scala script that can be used to interact with the Kudu Quickstart cluster. |

_See the appendices for additional resources for setting up the prerequisites._

# Start the Kudu Quickstart Cluster in Windows
There are two solutions so far for running the Kudu Quickstart Docker cluster in Windows:  

1. Launch the cluster using Powershell
1. Launch the cluster using Windows Subsystem for Linux (WSL)

For both scenarios, you can follow the instructions from the Kudu Quickstart tutorial, but the command for setting the `KUDU_QUICKSTART_IP` will be slightly different. The modifications to that section are described herein.

The Kudu Quickstart Tutorial can be found at [https://kudu.apache.org/docs/quickstart.html](https://kudu.apache.org/docs/quickstart.html)

## Launch Kudu Quickstart Cluster in Windows Powershell  
To set the `KUDU_QUICKSTART_IP` in Powershell, run the following command at the Powershell command line prior to bringing up the cluster:  

```$env:KUDU_QUICKSTART_IP=(Get-NetIPConfiguration | Where-Object {$_.IPv4DefaultGateway -ne $null -and $_.NetAdapter.Status -ne "Disconnected"}).IPv4Address.IPAddress```

After running the command, you can then continue the [Kudu Quickstart Tutorial](https://kudu.apache.org/docs/quickstart.html#_bring_up_the_cluster).

**To make it quick and easy** to launch a kudu cluster, this repo has a Powershell script for bringing up the cluster, `launch_kudu_powershell.ps1`. The script takes in an argument for the base directory of the kudu repository pulled from git.

### Launch Kudu Cluster with Powershell Scripts
The `launch_kudu_powershell.ps1` script usage for bringing up Kudu is as follows: 
 
```launch_kudu_powershell -kudu_dir git_location_for_kudu```


## Launch Kudu Quickstart Cluster in WSL  

***FRANK: Instructions Here***

# Using the Kudu Quickstart Spark Application in IntelliJ
There is a sample maven project in the `spark-app` directory called, `KuduQuickstartSparkExample`.

The `KuduQuickstartSparkExample` scala application is a Maven-backed project that can be used as an example to write Spark applications that interact with Kudu. Import the `pom.xml` as a project into IntelliJ to load up the project and explore it. You can run the project directly in IntelliJ.

*NOTE #1: As a Maven project, it should also be able to loaded in other IDEs, but IntelliJ was used to develop and test the application.*

*NOTE #2: By default, because of issues with loading resource files in a SparkSession, the application generates the data manually in `NasaData`. It's possible to use the resources directory by passing `use-resources` as a command line arg in the Run Configuration.*

# Running the Kudu Quickstart spark-shell Example
The `spark-shell` example is available to experiment with the Kudu cluster in an interactive Spark session.

To run this example, you must be in Powershell in the **spark-shell-powershell** directory.

## STEP 1: Launch Spark Shell
Enter the Spark shell by running the Powershell script, `start_spark_shell_w_kudu.ps1`, or by running the following command:  

```spark-shell --packages org.apache.kudu:kudu-spark2_2.11:1.11.1```

*The command to launch spark-shell will download the `kudu-spark2` package for use in the shell.*

## STEP 2: Execute Spark code to access Kudu

There is already a data file in the **spark-shell-powershell**, called `NASA_Labs_Facilities_clean.csv`. There is also a scala script with pre-written Spark code to generate the Kudu table, `default.nasa_labs`.

To load the scala script and run all the code for generating the Kudu table, run the following statement: `:load run_spark_kudu_example.scala`

### Code Within run_spark_kudu_example.scala

Below is a walkthrough of the code within the scala script. If you want to run this code on your own, copy/paste it into your running Spark shell environment.

#### Function to Set PK Columns as Not Nullable

You can create a schema manually for this dataset, but a general purpose function for setting specific columns as not nullable in the DataFrame is used instead.
  
```
  def setNotNull(df: DataFrame, columns: Seq[String]) : DataFrame = {
    val schema = df.schema
    // Modify [[StructField] for the specified columns.
    val newSchema = StructType(schema.map {
      case StructField(c, t, _, m)
        if columns.contains(c) => StructField(c, t, nullable = false, m)
      case y: StructField => y
    })
    // Apply new schema to the DataFrame
    df.sqlContext.createDataFrame(df.rdd, newSchema)
  }
```
  
  
#### Create a DataFrame with a Scaled-Down Version of the Data

The DataFrame will be instantiated, but the PK columns (agency, lab_name, facility) are nullable. This will not work with Kudu because PK columns cannot be null.  Use the `setNotNull` method to prepare the DataFrame for writing to Kudu.  

```
val dataFileName = "NASA_Labs_Facilities_clean.csv"

import spark.implicits._

val nasaDf = spark.
  read.
  option("header", "true").
  option("inferSchema", "true").
  csv(dataFileName).
  select(
	$"Agency".alias("agency"),
	$"Center".alias("lab_name"),
	$"Facility".alias("facility"),
	$"Center Search Status".alias("center_search_status"),
	$"Occupied".alias("occupied_year"),
	$"Status".alias("status"),
	$"URL Link".alias("url_link"),
	$"Record Date".alias("record_date"),
	$"Last Update".alias("last_update_date"),
	$"Address".alias("address"),
	$"City".alias("city"),
	$"State".alias("state"),
	$"Country".alias("country")
  )
  
  val nasaPreparedDf = setNotNull(nasaDf, Seq("agency", "lab_name", "facility"))
```

#### Instantiate the KuduContext and Create the Table

The code below will do three things:  

1. Initialize the KuduContext, configured to use the local cluster (Quickstart)
1. Delete the table, `default.nasa_labs`, if it exists
1. Create the Kudu table, `default.nasa_labs`, with: 
    * The schema of `nasaPreparedDf`
    * Primary Key of agency, lab_name, and facility
    * 3 replicas
    * Hash parition using lab_name, with 2 partitions  

```
val kuduContext = new KuduContext(
    "localhost:7051,localhost:7151,localhost:7251",
    spark.sparkContext
  )

  val nasaKuduTableName = "default.nasa_labs"
  if(kuduContext.tableExists(nasaKuduTableName)) {
    kuduContext.deleteTable(nasaKuduTableName)
  }
  
  kuduContext.createTable(nasaKuduTableName,
    nasaPreparedDf.schema, // Kudu schema with PK columns set as Not Nullable
    Seq("agency", "lab_name", "facility"), // Primary Key Columns
    new CreateTableOptions().setNumReplicas(3).addHashPartitions(List("lab_name").asJava, 2)
  )
```  

#### Write the DataFrame to Kudu

```kuduContext.insertRows(nasaPreparedDf, nasaKuduTableName)```

#### Read the DataFrame from Kudu

Read the data from Kudu, projecting only the agency, lab_name, facility, and occupied_year columns, while filtering only where the occupied_year is not null.  

```
val nasaKuduDf = spark.read.
    option("kudu.master", "localhost:7051,localhost:7151,localhost:7251").
    option("kudu.table", nasaKuduTableName).
    option("kudu.scanLocality", "leader_only").
    format("kudu").
    load.
    where(!$"occupied_year".isNull).
    select($"agency",$"lab_name",$"facility",$"occupied_year")
```

## Appendix A: Additional Resources
There are many tutorials for getting your Windows environment set up for Spark development using scala. Some of the useful ones that were used for setup are below:

1. Setup for Java, Scala, Spark