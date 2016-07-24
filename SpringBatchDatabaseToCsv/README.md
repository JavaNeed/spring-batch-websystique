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


```sh
<!-- JobRepository and JobLauncher are configuration/setup classes -->
	<bean id="jobRepository"  class="org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean" />

	<bean id="jobLauncher" class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
		<property name="jobRepository" ref="jobRepository" />
	</bean>


	<!-- ================ ItemReader which reads from database and 
		returns the row mapped by rowMapper =======================-->
	<bean id="databaseItemReader" class="org.springframework.batch.item.database.JdbcCursorItemReader">

		<property name="dataSource" ref="dataSource" />

		<property name="sql" value="SELECT STUDENT_NAME, DOB, PERCENTAGE FROM EXAM_RESULT" />

		<property name="rowMapper">
			<bean class="com.websystique.springbatch.mapper.ExamResultRowMapper" />
		</property>
	</bean>


	<!-- ============== ItemWriter writes a line into output flat file =========== -->
	<bean id="flatFileItemWriter" class="org.springframework.batch.item.file.FlatFileItemWriter" scope="step">

		<property name="resource" value="file:csv/examResult.txt" />
		<property name="lineAggregator">

			<!-- An Aggregator which converts an object into delimited list of strings -->
			<bean class="org.springframework.batch.item.file.transform.DelimitedLineAggregator">

				<property name="delimiter" value="|" />
				<property name="fieldExtractor">
					<!-- Extractor which returns the value of beans property through reflection -->
					<bean class="org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor">
						<property name="names" value="studentName, percentage, dob" />
					</bean>
				</property>
			</bean>
		</property>
	</bean>


	<!-- Optional JobExecutionListener to perform business logic before and after the job -->
	<bean id="jobListener" class="com.websystique.springbatch.listener.ExamResultJobListener" />

	<!-- Optional ItemProcessor to perform business logic/filtering on the input records -->
	<bean id="itemProcessor" class="com.websystique.springbatch.processor.ExamResultItemProcessor" />

	<!-- Step will need a transaction manager -->
	<bean id="transactionManager"
		class="org.springframework.batch.support.transaction.ResourcelessTransactionManager" />

	<!-- ================== Actual Job =================-->
	<batch:job id="examResultJob">
		<batch:step id="step1">
			<batch:tasklet transaction-manager="transactionManager">
				<batch:chunk reader="databaseItemReader" writer="flatFileItemWriter"
					processor="itemProcessor" commit-interval="10" />
			</batch:tasklet>
		</batch:step>
		<batch:listeners>
			<batch:listener ref="jobListener" />
		</batch:listeners>
	</batch:job>
```sh


reference URL:
-----------------
http://websystique.com/springbatch/spring-batch-read-from-mysql-database-and-write-to-a-csv-file/


