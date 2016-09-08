package com.doj.batch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

public class EmployeeTest {
	Job job = null;
	JobLauncher jobLauncher = null;
	private static String[] springConfig = { "applicationContext.xml", "simple-job.xml" };
			
	@SuppressWarnings("resource")
	@Before
	public void beforeTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		job = (Job) context.getBean("simpleDojJob");
	}
	
	
	@Test
	public void testEmplyeeBatch() {
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("----------------------------------------------");
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());
			System.out.println("-----------------------------------------------");
			
			Assert.assertEquals("COMPLETED", execution.getStatus().toString());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			System.out.println(e.getMessage());
		}
	}

}
