Spring Batch- MultiResourceItemReader & HibernateItemWriter example
--------------------------------------------------------------------
In this post we will learn about how to use Spring Batch to read multiple flat files using MultiResourceItemReader and write to database with Hibernate (using HibernateItemWriter). We will also witness the usage of JobExecutionListener and itemProcessor.

Following technologies being used:
---------------------------------
- Spring Batch 3.0.1.RELEASE
- Spring core 4.2.4.RELEASE
- Hibernate 4.3.11.Final
- MySQL Server 5.6
- Joda Time 2.8
- JDK 1.8
- Eclipse JUNO Service Release 2 or STS

Create a fairly simple table in MySQL database which maps to our domain model.

`create table EXAM_RESULT (
   id INT NOT NULL auto_increment PRIMARY KEY,   
   student_name VARCHAR(30) NOT NULL,
   dob DATE NOT NULL,
   percentage  double NOT NULL
);`


Since we are using Hibernate this time, we’ve included hibernate-core dependency. We will be using a pooled data source ComboPooledDataSource. We also need mysql-connector-java to connect to MySQL database, we will also need usertype.core to handle the conversion between Jodatime and MySQL date. Spring dependency are pretty obvious as in earlier tutorials.

Only special thing here is declaration of @Type which will help Hibernate easily map between jodatime LocalDate and database specific Date.

ItemProcessor is Optional, and called after item read but before item write. It gives us the opportunity to perform a business logic on each item. In our case, for example, we will filter out all the items whose percentage is less than 60. So final result will only have records with percentage >= 60.

Job listener is Optional and provide the opportunity to execute some business logic before job start and after job completed.For example setting up environment can be done before job and cleanup can be done after job completed.

`com.websystique.springbatch.ExamResultJobListener`

Reference URL:
---------------
http://websystique.com/springbatch/spring-batch-multiresourceitemreader-hibernateitemwriter-example/

Note:
-----
SET SQL_SAFE_UPDATES = 0;
