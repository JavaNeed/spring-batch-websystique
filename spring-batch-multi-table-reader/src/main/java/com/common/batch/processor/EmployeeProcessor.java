package com.common.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.common.batch.model.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{

	@Override
	public Employee process(Employee result) throws Exception {
		System.out.println("Processing result :"+result);
		return result;
	}

}
