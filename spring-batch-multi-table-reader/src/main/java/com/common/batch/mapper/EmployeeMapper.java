package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Employee;

public class EmployeeMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployeeNumber(rs.getInt("employeeNumber"));
		employee.setLastName(rs.getString("lastName"));
		employee.setFirstName(rs.getString("firstName"));
		employee.setExtension(rs.getString("extension"));
		employee.setEmail(rs.getString("email"));
		employee.setOfficeCode(rs.getString("officeCode"));
		employee.setReportsTo(rs.getInt("reportsTo"));
		employee.setJobTitle(rs.getString("jobTitle"));
		
		return employee;
	}
}
