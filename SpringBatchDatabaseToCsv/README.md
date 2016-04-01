Spring Batch- Read From MySQL database & write to CSV file
-----------------------------------------------------------
In this post we will learn about how to use Spring Batch to read from MySQL database using JdbcCursorItemReader and write to a Flat file using FlatFileItemWriter. We will also witness the usage of JobExecutionListener and itemProcessor.

Following technologies being used:
----------------------------------
Spring Batch 3.0.1.RELEASE
Spring core 4.2.4.RELEASE
Spring jdbc 4.2.4.RELEASE
MySQL Server 5.6
Joda Time 2.8
JDK 1.8
Eclipse JUNO Service Release 2 or STS


We will be reading MySQL database and write to a flat file (project/csv/examResult.txt).

Step 2: Create Database Table and populate it with sample data

Create a fairly simple table in MySQL database which maps to our domain model(and sufficient for this example).

`create table EXAM_RESULT (
   student_name VARCHAR(30) NOT NULL,
   dob DATE NOT NULL,
   percentage  double NOT NULL
);`
 
`insert into exam_result(student_name,dob,percentage) 
value('Brian Burlet','1985-02-01',76),('Rita Paul','1993-02-01',92),('Han Yenn','1965-02-01',83),('Peter Pan','1987-02-03',62);`

As we need to interact with db this time, we will use spring-jdbc support. We will also need mysql connector to communicate with MySQL, and since we are also using joda-time for any date-time processing we might need, we will include that dependency as well.

Step 4: Create domain object & Mapper (RowMapper implementaion)

We will be mapping the data from database table to properties of our domain object.

`com.websystique.springbatch.model.ExamResult`


reference URL:
-----------------
http://websystique.com/springbatch/spring-batch-read-from-mysql-database-and-write-to-a-csv-file/


