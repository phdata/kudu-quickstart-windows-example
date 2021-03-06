import org.apache.kudu.client.CreateTableOptions
import org.apache.kudu.spark.kudu.KuduContext
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types.{StructField, StructType}
import scala.collection.JavaConverters._

// Spark Example Code
println("************************************\nExecuting main Spark/Kudu integration code...\n************************************")

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

    // the primary key columns cannot be null in Kudu
    // set them as non-nullable.
    val nasaPreparedDf = setNotNull(nasaDf, Seq("agency", "lab_name", "facility"))

    // initialize a KuduContext to interact with the local Kudu instance
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

    /************************************************************************
     *             EXAMPLE: WRITE DATA TO KUDU
     ************************************************************************/
    kuduContext.insertRows(nasaPreparedDf, nasaKuduTableName)

    /************************************************************************
     *             EXAMPLE: READ DATA FROM KUDU
     ************************************************************************/
    val nasaKuduDf = spark.read.
      option("kudu.master", "localhost:7051,localhost:7151,localhost:7251").
      option("kudu.table", nasaKuduTableName).
      option("kudu.scanLocality", "leader_only").
      format("kudu").
      load.
      where(!$"occupied_year".isNull).
      select($"agency",$"lab_name",$"facility",$"occupied_year")

    nasaKuduDf.show(false)

println("********************************************************************")
println("Check for Kudu tablet information at http://localhost:8050/tablets")
println("If Successful, you should have a Kudu table, default.nasa_labs")
println("********************************************************************")