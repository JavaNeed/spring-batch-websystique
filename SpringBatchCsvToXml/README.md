SpringBatchCsvToXml
----------------------

In this post we will learn about how to use Spring Batch to read a flat CSV file using FlatFileItemReader and write to an XML file using StaxEventItemWriter. We will also witness the usage of JobExecutionListener and itemProcessor.

Following technologies being used:
---------------------------------
- Spring Batch 3.0.6.RELEASE
- Spring core 4.2.4.RELEASE
- Spring oxm 4.2.4.RELEASE
- Joda Time 2.8
- JDK 1.8
- Eclipse JUNO Service Release 2 or STS


```sh
<!-- JobRepository and JobLauncher are configuration/setup classes -->
	<bean id="jobRepository" class="org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean" />

	<bean id="jobLauncher" 	class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
		<property name="jobRepository" ref="jobRepository" />
	</bean>


	<!-- ============= ItemReader reads a complete line one by one from input file ============ -->
	<bean id="flatFileItemReader" class="org.springframework.batch.item.file.FlatFileItemReader"  scope="step">
	
		<!-- Get the Resource file -->
		<property name="resource" value="classpath:ExamResult.txt" />

		<property name="lineMapper">
			<bean class="org.springframework.batch.item.file.mapping.DefaultLineMapper">

				<property name="fieldSetMapper">
					<!-- Mapper which maps each individual items in a record to properties in POJO -->
					<bean class="com.websystique.springbatch.ExamResultFieldSetMapper" />
				</property>

				<property name="lineTokenizer">
					<!-- A tokenizer class to be used when items in input record are separated by specific characters -->
					<bean class="org.springframework.batch.item.file.transform.DelimitedLineTokenizer">
						<property name="delimiter" value="|" />
					</bean>
				</property>
			</bean>
		</property>
	</bean>


	<!-- ======== XML ItemWriter which writes the data in XML format =========== -->
	<bean id="xmlItemWriter" class="org.springframework.batch.item.xml.StaxEventItemWriter">

		<property name="resource" value="file:xml/ExamResult.xml" />

		<property name="rootTagName" value="UniversityExamResultList" />

		<property name="marshaller">
			<bean class="org.springframework.oxm.jaxb.Jaxb2Marshaller">
				<property name="classesToBeBound">
					<list>
						<value>com.websystique.springbatch.model.ExamResult</value>
					</list>
				</property>
			</bean>
		</property>
	</bean>

	<!-- Optional ItemProcessor to perform business logic/filtering on the input records -->
	<bean id="itemProcessor" class="com.websystique.springbatch.ExamResultItemProcessor" />

	<!-- Optional JobExecutionListener to perform business logic before and after the job -->
	<bean id="jobListener" class="com.websystique.springbatch.ExamResultJobListener" />

	<!-- Step will need a transaction manager -->
	<bean id="transactionManager" class="org.springframework.batch.support.transaction.ResourcelessTransactionManager" />

	<!-- Actual Job -->
	<batch:job id="examResultJob">
		<batch:step id="step1">
			<batch:tasklet transaction-manager="transactionManager">
				<batch:chunk reader="flatFileItemReader" writer="xmlItemWriter"	processor="itemProcessor" commit-interval="10" />
			</batch:tasklet>
		</batch:step>
		<batch:listeners>
			<batch:listener ref="jobListener" />
		</batch:listeners>
	</batch:job>
```

Reference URL:
-------------
http://websystique.com/springbatch/spring-batch-read-a-csv-file-and-write-to-an-xml-file/



USEFULINFO
----------
git add -A is equivalent to  git add .; git add -u.

The important point about git add . is that it looks at the working tree and adds all those paths to the staged changes if they are either changed or are new and not ignored, it does not stage any 'rm' actions.

git add -u looks at all the already tracked files and stages the changes to those files if they are different or if they have been removed. It does not add any new files, it only stages changes to already tracked files.

git add -A is a handy shortcut for doing both.

You can test the differences out with something like this (not that for Git version 2.x your output for git add . git status will be different):

git init
echo Change me > change-me
echo Delete me > delete-me
git add change-me delete-me
git commit -m initial

echo OK >> change-me
rm delete-me
echo Add me > add-me

git status
# Changed but not updated:
#   modified:   change-me
#   deleted:    delete-me
# Untracked files:
#   add-me

git add .
git status

# Changes to be committed:
#   new file:   add-me
#   modified:   change-me
# Changed but not updated:
#   deleted:    delete-me

git reset

git add -u
git status

# Changes to be committed:
#   modified:   change-me
#   deleted:    delete-me
# Untracked files:
#   add-me

git reset

git add -A
git status

# Changes to be committed:
#   new file:   add-me
#   modified:   change-me
#   deleted:    delete-me
Summary:

git add -A stages All
git add . stages new and modified, without deleted
git add -u stages modified and deleted, without new
