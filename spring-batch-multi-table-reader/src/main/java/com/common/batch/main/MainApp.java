package com.common.batch.main;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// for CSV - This works fine, no need to override equals method
		//ApplicationContext context = new ClassPathXmlApplicationContext("forCSV/spring-batch-context.xml");
		
		// for XML
		ApplicationContext context = new ClassPathXmlApplicationContext("forXML/spring-batch-context.xml");
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("examResultJob");
		
		JobExecution execution;
		try {
			execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Exit Status : "+ execution.getStatus());

		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Done !!");
	}
}
