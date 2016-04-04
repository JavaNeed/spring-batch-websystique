Spring Batch Example MySQL Database To XML
-----------
A typical batch program generally reads a large number of records from a database, file, or queue, processes the data in some fashion, and then writes back data in a modified form to database,file-system, mailer etc.

Spring Batch automates this basic batch iteration, providing the capability to process similar transactions as a set, typically in an offline environment without any user interaction.

In this tutorial, we will show you how to read data from a MySQL database, with JdbcCursorItemReader and JdbcPagingItemReader, and write it into an XML file.

Work flow of this Example-How it works?
-----------------
Spring Batch works like read data in some chunk size[configurable] from data source, and write that chunk to some resource. Here data source for reader could be flat files[text file, xml file, csv file etc], relational database[e.g. mysql], mongodb. Similarly writer could write data read by reader to flat files, relation database, mongodb, mailer etc.

Reading, processing, writing all together is termed as Job.

-----------------------
```sh
<!-- ItemReader -->	
	<bean id="itemReader" class="org.springframework.batch.item.database.JdbcCursorItemReader" scope="step">
		<property name="dataSource" ref="dataSource"/>
		<property name="sql" value="select * from employeet" />
		<property name="rowMapper">
			<bean class="com.doj.batch.mapper.EmployeeRowMapper" />
		</property>
 	 </bean>
 
 
 	<!-- ItemWritter -->
    <bean id="itemWriter" class="org.springframework.batch.item.xml.StaxEventItemWriter">
		<property name="resource" value="file:xml/outputs/employees.xml" />
		<property name="marshaller" ref="empMarshaller" />
		<property name="rootTagName" value="employees" />
	 </bean> 
    
    
    <!--  Jaxb2 Marshaller -->
    <bean id="empMarshaller" class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
		<property name="classesToBeBound">
			<value>com.doj.batch.bean.Employee</value>
		</property>
 	 </bean>	
  
  
  	<!-- JOB -->
    <batch:job id="simpleDojJob" job-repository="jobRepository" parent="simpleJob">
    	<batch:step id="step1">
    		<batch:tasklet transaction-manager="transactionManager">
    			<batch:chunk reader="itemReader" writer="itemWriter" commit-interval="1"/>
    		</batch:tasklet>
    	</batch:step>
    </batch:job>  
    ```