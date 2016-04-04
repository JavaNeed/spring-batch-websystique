Spring Batch Example XML to MongoDB
-------
Spring Batch and MongoDB-
In Spring Batch 2.2.0 version introduce NoSQL database support with introducing org.springframework.batch.item.data package which contain following classes.
AbstractPaginatedDataItemReader.class
GemfireItemWriter.class
MongoItemReader.class
MongoItemWriter.class
Neo4jItemReader.class
Neo4jItemWriter.class
RepositoryItemReader.class
RepositoryItemWriter.class
SpELMappingGemfireItemWriter.class

Spring Batch 2.2.0
Spring Batch is a Spring-based framework for enterprise Java batch processing. An important aspect of Spring Batch is the separation between reading from and writing to resources and the processing of a single record, called item in the Spring Batch lingo. There are a lot of existing item readers and writers for a wide range of resources like JDBC databases, JMS messaging systems, flat file etc. If the resource of your choice is not supported of of the box, it is easy to implement your own reader and writer as we will see in a minute.

MongoDB
MongoDB is a popular NoSQL datastore. It stores so called documents (basically an ordered set of key/value pairs where a value can be a simple data type like String or integer but also an array of values or a sub document). MongoDB is optimized for heavy write throughput and horizontal scaling.

Since I am a big fan of MongoDB on the one hand and introducing the Spring Batch framework at one of my customers on the other hand, why not implement a Spring Batch item reader(xml reader) and writer for MongoDB(MongoItemWriter).

MongoDB Item Writer-MongoItemWriter
My first approach to the item writer was very naive. I just took the DBObject item list and inserted them into the target collection. This can be done with the following configuration:


```sh
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p" 
	xmlns:batch="http://www.springframework.org/schema/batch"
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
	http://www.springframework.org/schema/beans  http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
    http://www.springframework.org/schema/batch  http://www.springframework.org/schema/batch/spring-batch.xsd">


	<import resource="applicationContext.xml"/>
	<import resource="mongodbConfig.xml"/>


	<!-- XML Item Reader -->	
	<bean id="xmlItemReader" class="org.springframework.batch.item.xml.StaxEventItemReader">
		<property name="resource" value="classpath:xml/employees.xml" />
		<property name="unmarshaller" ref="empUnMarshaller" />
		<property name="fragmentRootElementName" value="employee" />
 	 </bean>


	<!-- Employee Unmarshaller -->
	<bean id="empUnMarshaller" class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
		<property name="classesToBeBound">
			<value>com.doj.batch.bean.Employee</value>
		</property>
 	 </bean>
 	 
 	 
	<!-- write it to MongoDB, 'employee' collection (table) -->
    <bean id="mongodbItemWriter" class="org.springframework.batch.item.data.MongoItemWriter">
		<property name="template" ref="mongoTemplate" />
		<property name="collection" value="employee" />
    </bean>
    
    <!-- JOB -->
    <batch:job id="simpleDojJob" parent="simpleJob">
    	<batch:step id="step1">
    		<batch:tasklet>
    			<batch:chunk reader="xmlItemReader" writer="mongodbItemWriter" commit-interval="2"/>
    		</batch:tasklet>
    	</batch:step>
    </batch:job>   
</beans>
```


Reference URL:
----
http://www.dineshonjava.com/2014/03/spring-batch-xml-to-mongodb-database.html#.VwLGiPl961t