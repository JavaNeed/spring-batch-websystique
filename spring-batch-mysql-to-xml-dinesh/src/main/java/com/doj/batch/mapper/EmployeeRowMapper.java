package com.doj.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.doj.batch.bean.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmpid(resultSet.getInt("empid"));
		employee.setName(resultSet.getString("empname"));
		employee.setSalary(resultSet.getLong("salary"));
		employee.setAddress(resultSet.getString("empaddress"));
		employee.setAge(resultSet.getInt("empAge"));
		return employee;
	}

}
