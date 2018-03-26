# DashDemoMaven

A CRUD command line tool to store, sort and filter data about users in JSON, or CSV files.
test sequence scenario in ProposedTest.docx using Junit and Maven project management

## Installation and Set Up

Prerequisites:

* Eclipse IDE 
* jdk 8 or 10
* junit
* Maven
 
Install required packages to project libraries
```
json-simple-1.1.jar
jackson.all-1.9.0
opencsv-4.0.jar
commons-csv-1.1.jar
gson-2.2.2.jar
junit-4.10.jar
```
All this packages on Folder DashDemoMaven/jar or can clone it  
```
git clone https://github.com/ahmednasseranwer/DashDemoMaven/tree/master/src/lib
```

## Running Program 

* Run "com/main/Main/MainTask.java" in "src/main/java"
* In "ProposedTests.docx", Check table #1 that help to run program

### Select File Type :
```
Select File Type: 
1-Json
2-CSV	 
=> 1
```

### Select Opertion AddPerson
```
Select your Choice 
 1-Add Person 
 2-Update Person's information 
 3-List All Persons 
 4-Delete Specific Person 
 5-Filter by any Field 
 6-Sort on any Field
=> 1
Enter Person's First Name: 
Ahmed
Enter Person's Last Name: 
Nasser
Enter Person's Title: 
Software Enginner  
Enter Person's Phone: 
012
Enter Person's Age: 
24
Enter Person's Mail: 
ahmednasser@gmail
New Person is added with name Ahmed Nasser to JsonFile
```

## Running the tests manually

* Run "com/main/Main/MainTask.java" in "src/main/java" 
* Test program manually according to all testcases  in table#2 of "ProposedTests.docx" 

### Select Opertion List All Persons 
```
[[3-List All Persons]]
```
### Output Command Line Interface in JsonFile
```
File is Empty
```

### Output Command Line Interface in JCSVFile
```
File is Empty
```

## Running the tests Using Junit Testing 

* Run "JunitTestSuite.java" that calls ["TestJson.java","TestCSV.java"] contains all sequence scenarios testcases that Prepared in table#2 of "ProposedTests.docx"
* Before run "JunitTestSuite.java" check "CSVFile.csv" and "JsonFile.json" is empty
* See output in Junit and Console
 
## Authors 
**Ahmed Nasser**
