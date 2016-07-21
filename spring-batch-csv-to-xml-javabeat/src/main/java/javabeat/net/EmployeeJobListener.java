package javabeat.net;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class EmployeeJobListener implements JobExecutionListener{

	private DateTime startTime, endTime;
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		startTime = new DateTime();
		System.out.println("---------------------------");
		System.out.println("Employee Job Starts at : "+startTime);
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		endTime = new DateTime();
		System.out.println("Employee Job Ends at : "+endTime);
		System.out.println("----------------------------------");
		System.out.println("Total time take in milliseconds :"+getTimeInMillis(startTime, endTime));
		System.out.println("----------------------------------");
		
		if(jobExecution.getStatus() == BatchStatus.COMPLETED)
			System.out.println("Employee job completed successfully");
		else if(jobExecution.getStatus() == BatchStatus.FAILED){
			System.out.println("Employee job failed with following exceptions");
			List<Throwable> throwables = jobExecution.getAllFailureExceptions();
			for (Throwable throwable : throwables) {
				System.err.println("Exception : "+throwable.getLocalizedMessage());
			}
		}
	}

	
	private long getTimeInMillis(DateTime start, DateTime stop){
		return stop.getMillis() - start.getMillis();
	}
}
