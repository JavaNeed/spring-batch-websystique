Spring Batch ==> Multi-resource example by Dinesh !!
-------------------------------------------------
simple-job.xml
-------------
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p" 
	xmlns:batch="http://www.springframework.org/schema/batch"
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans  http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
	http://www.springframework.org/schema/batch  http://www.springframework.org/schema/batch/spring-batch.xsd">

	<import resource="applicationContext.xml"/>
	
	
	<!-- Item reader for reading XML input based on StAX. -->
	<bean id="xmlItemReader" class="org.springframework.batch.item.xml.StaxEventItemReader">
		<property name="unmarshaller" ref="empUnMarshaller" />
		<property name="fragmentRootElementName" value="employee" />
 	 </bean>
 	 
 	
 	<!-- he typical usage will be to set either the "contextPath" or the "classesToBeBound"
		property on this bean, possibly customize the marshaller and unmarshaller by setting
		properties, schemas, adapters, and listeners, and to refer to it. -->
	<bean id="empUnMarshaller" class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
		<property name="classesToBeBound">
			<value>com.doj.batch.bean.Employee</value>
		</property>
 	 </bean>
 	 
 	 <bean id="multiResourceReader" class=" org.springframework.batch.item.file.MultiResourceItemReader">
		<property name="resources" value="classpath:xml/inputs/emp-*.xml" />
		<property name="delegate" ref="xmlItemReader" />
	 </bean>
	 
	<bean id="xmlItemWriter" class="org.springframework.batch.item.xml.StaxEventItemWriter">
		<property name="resource" value="file:xml/outputs/employees.xml" />
		<property name="marshaller" ref="empMarshaller" />
		<property name="rootTagName" value="employees" />
	 </bean> 
	 
	 <!-- Marshaller -->
	 <bean id="empMarshaller" class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
		<property name="classesToBeBound">
			<value>com.doj.batch.bean.Employee</value>
		</property>
 	 </bean>	
    
    <batch:job id="simpleDojJob" parent="simpleJob">
    	<batch:step id="step1">
    		<batch:tasklet>
    			<batch:chunk reader="multiResourceReader" writer="xmlItemWriter" commit-interval="1"/>
    		</batch:tasklet>
    	</batch:step>
    </batch:job>   
</beans>
```


applicationContext.xml
----------------------
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p" 
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans  http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

	<bean id="transactionManager" class="org.springframework.batch.support.transaction.ResourcelessTransactionManager"/>
 
    <bean id="jobLauncher" class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
        <property name="jobRepository" ref="jobRepository"/>
    </bean>
 
    <bean id="jobRepository" class="org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean">
        <property name="transactionManager" ref="transactionManager"/>
    </bean>
 
    <bean id="simpleJob" class="org.springframework.batch.core.job.SimpleJob" abstract="true">
        <property name="jobRepository" ref="jobRepository" />
    </bean>
</beans>
```