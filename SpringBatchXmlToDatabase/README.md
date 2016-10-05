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


spring-batch-context.xml
------------------------
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:batch="http://www.springframework.org/schema/batch" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/batch
		http://www.springframework.org/schema/batch/spring-batch.xsd
		http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans.xsd">

	<import resource="classpath:context-datasource.xml"/>

	<!-- ============= ItemReader which reads data from XML file ============= -->
	<bean id="xmlItemReader" class="org.springframework.batch.item.xml.StaxEventItemReader">

		<property name="resource" value="classpath:examResult.xml" />

		<property name="fragmentRootElementName" value="ExamResult" />

		<property name="unmarshaller">
			<bean class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
				<property name="classesToBeBound">
					<list>
						<value>com.websystique.springbatch.model.ExamResult</value>
					</list>
				</property>
			</bean>
		</property>
	</bean>


	<!-- ================ ItemWriter which writes data to database ================= -->
  	<bean id="databaseItemWriter" class="org.springframework.batch.item.database.JdbcBatchItemWriter">
		<property name="dataSource" ref="dataSource" />
		<property name="sql">
		  	<value>
	            <![CDATA[        
	            	insert into EXAM_RESULT(STUDENT_NAME, DOB, PERCENTAGE) values (?, ?, ?)
	            ]]>
		  	</value>
		</property>

		<!-- We need a custom setter to handle the conversion between Jodatime LocalDate and MySQL DATE -->
		<property name="ItemPreparedStatementSetter">
			<bean class="com.websystique.springbatch.ExamResultItemPreparedStatementSetter" />
		</property>
  </bean>

	<!-- Optional ItemProcessor to perform business logic/filtering on the input records -->
	<bean id="itemProcessor" class="com.websystique.springbatch.processor.ExamResultItemProcessor" />

	<!-- Optional JobExecutionListener to perform business logic before and after the job -->
	<bean id="jobListener" class="com.websystique.springbatch.utils.ExamResultJobListener" />


	<!-- ==================== Actual Job ========================= -->
	<batch:job id="examResultJob">
		<batch:step id="step1">
			<batch:tasklet transaction-manager="transactionManager">
				<batch:chunk reader="xmlItemReader" writer="databaseItemWriter" processor="itemProcessor" commit-interval="10" />
			</batch:tasklet>
		</batch:step>
		<batch:listeners>
			<batch:listener ref="jobListener" />
		</batch:listeners>
	</batch:job>

</beans>			
```


context-datasource.xml
-----------------

```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:batch="http://www.springframework.org/schema/batch"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/batch http://www.springframework.org/schema/batch/spring-batch.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

	<context:property-placeholder location="classpath:database.properties" />
	
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="${driver.class.name}" />
		<property name="url" value="${jdbc.url}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
  	</bean>
  	
  	<!-- JobRepository and JobLauncher are configuration/setup classes -->
	<bean id="jobRepository" class="org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean" />

	<bean id="jobLauncher" 	class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
		<property name="jobRepository" ref="jobRepository" />
	</bean>
	
	<!-- Step will need a transaction manager -->
	<bean id="transactionManager" class="org.springframework.batch.support.transaction.ResourcelessTransactionManager" />

</beans>
```
