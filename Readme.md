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
|spark-app | A sample Spark application that can be imported into IntelliJ using the Maven pom.xml. |
|spark-shell-powershell | Scripts to run `spark-shell` with Kudu support. Includes a scala script that can be used to interact with the Kudu Quickstart cluster. |

_See the appendices for additional resources for setting up the prerequisites._