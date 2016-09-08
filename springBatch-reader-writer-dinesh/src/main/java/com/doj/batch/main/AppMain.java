package com.doj.batch.main;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {

	public static void main(String[] args) {
		String[] springConfig = { "applicationContext.xml", "simple-job.xml" };
		
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("simpleDojJob");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("----------------------------------------------");
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());
			System.out.println("-----------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done !!");

	}

}
