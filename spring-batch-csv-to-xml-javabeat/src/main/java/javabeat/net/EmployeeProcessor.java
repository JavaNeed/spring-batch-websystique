package javabeat.net;

import org.springframework.batch.item.ItemProcessor;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{

	@Override
	public Employee process(Employee item) throws Exception {
		System.out.println("Processing result :"+item.toString());
		
		// Process data for any condition
		
		return item;
	}
}
