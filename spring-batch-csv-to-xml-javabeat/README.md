spring-batch-csv-to-xml-javabeat
------------------------------------

The following are the simple steps to write a Spring Batch job. Note that these steps are specific to this example and it may differ based on the batch process requirement.

This example takes the input data from a CSV file using FlatFileItemReader API and then without any further processing (Spring Batch provides ItemProcessor for processing the read data before writing to the database) it directly writes into the database table using OrderItemWriter which an implementation class for ItemWriter. Lets follow the below steps to understand this example.

- Step 1: Create a domain object with the required data structure.
- Step 2: Create a FieldSetMapper implementation class which is required for mapping the domain object properties to the CSV file used in this example.
- Step 3: Create a table in the database for storing the data
- Step 4: Create data source configuration file with database credentials.
- Step 5: Create Job context configuration file
- Step 6: Create ItemWriter implementation class which will be used for inserting the processed data to the database.
- Step 7: Create Job Launcher class for invoking the Job and running the batch process.
- Step 8: Run the Job launcher that is created in the step 7.


```sh
<!-- JOB -->
	<job id="employeeJob" xmlns="http://www.springframework.org/schema/batch">
		<step id="employeeprocessor">
			<tasklet>
				<chunk reader="reader" writer="writer" commit-interval="3" 
				processor="employeeProcessor" skip-limit="1">
					<skippable-exception-classes>
						<include class="org.springframework.batch.item.file.FlatFileParseException" />
					</skippable-exception-classes>
				</chunk>
			</tasklet>
		</step>
	</job>


	<!-- ItemReader -->
	<bean id="reader" class="org.springframework.batch.item.file.FlatFileItemReader" scope="step">
		<property name="resource" value="classpath:input/employees.csv" />
		
		<property name="linesToSkip" value="1" />
		
		<property name="lineMapper">
			<bean class="org.springframework.batch.item.file.mapping.DefaultLineMapper">
				<property name="lineTokenizer">
					<bean class="org.springframework.batch.item.file.transform.DelimitedLineTokenizer">
						<property name="names" value="EMP_ID,CITY,COUNTRY" />
						<property name="delimiter" value="," />
					</bean>
				</property>
				<property name="fieldSetMapper">
					<bean class="javabeat.net.EmployeeDataMapper" />
				</property>
			</bean>
		</property>
	</bean>
	
	<bean id="employeeProcessor" class="javabeat.net.EmployeeProcessor" />

	<bean id="writer" class="javabeat.net.OrderItemWriter">
		<constructor-arg ref="dataSource" />
	</bean>```