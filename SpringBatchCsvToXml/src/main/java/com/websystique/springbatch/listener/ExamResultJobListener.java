package com.websystique.springbatch.listener;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class ExamResultJobListener implements JobExecutionListener{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExamResultJobListener.class);
	private DateTime startTime, stopTime;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		startTime = new DateTime();
		LOGGER.debug("-----------------------------------------");
		LOGGER.debug("ExamResult Job starts at :"+startTime);
	}
	

	@Override
	public void afterJob(JobExecution jobExecution) {
		stopTime = new DateTime();
		LOGGER.debug("ExamResult Job stops at :"+stopTime);
		LOGGER.debug("Total time take in millis :"+getTimeInMillis(startTime , stopTime));
		LOGGER.debug("------------------------------------------------");
		
		if(jobExecution.getStatus() == BatchStatus.COMPLETED){
			LOGGER.debug("ExamResult job completed successfully");
			//Here you can perform some other business logic like cleanup
		}else if(jobExecution.getStatus() == BatchStatus.FAILED){
			LOGGER.debug("ExamResult job failed with following exceptions ");
			List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
			for(Throwable th : exceptionList){
				System.err.println("exception :" +th.getLocalizedMessage());
			}
		}
	}
	
	private long getTimeInMillis(DateTime start, DateTime stop){
		return stop.getMillis() - start.getMillis();
	}
	
}
