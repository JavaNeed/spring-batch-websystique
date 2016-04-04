package com.doj.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.doj.batch.bean.Employee;

public class EmployeeFilterProcessor implements ItemProcessor<Employee, Employee>{

	@Override
	public Employee process(Employee emp) throws Exception {
		System.out.println("Process Employees :"+emp);
		
		if(emp.getSalary() > 70000.0){  
			return emp;  
		}else{  
			return null;  
		}  
	}

}
