Spring Batch- Read an XML file and write to MySQL Database
------------------------------------------------------------
In this post we will learn about how to use Spring Batch to read an XML file using StaxEventItemReader and write to MySQL Database using JdbcBatchItemWriter. We will also witness the usage of JobExecutionListener and itemProcessor.

Following technologies being used:
----------------------------------
- Spring Batch 3.0.1.RELEASE
- Spring core 4.2.4.RELEASE
- Spring jdbc 4.2.4.RELEASE
- Spring oxm 4.2.4.RELEASE
- MySQL Server 5.6
- Joda Time 2.8
- JDK 1.8
- Eclipse or STS


What we will do here is read the XML file (src/main/resources/examResult.XML) and write it’s content to MySQL database.

As we need to interact with db this time, we will use spring-jdbc support. Since we need to handle XML, we will use spring-oxm marshalling support. To interact with MySQL, we need mysql connector, and since we are also using joda-time for any date-time processing we might need, we will include that dependency as well.

And the mapped POJO with fields corresponding to the row content of above file:

`com.websystique.springbatch.model.ExamResult`
