package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Customer;

public class CustomerMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerNumber(rs.getInt("customerNumber"));
		customer.setCustomerName(rs.getString("customerName"));
		customer.setContactLastName(rs.getString("contactLastName"));
		customer.setContactFirstName(rs.getString("contactFirstName"));
		customer.setPhone(rs.getString("phone"));
		customer.setAddressLine1(rs.getString("addressLine1"));
		customer.setAddressLine2(rs.getString("addressLine2"));
		customer.setCity(rs.getString("city"));
		customer.setState(rs.getString("state"));
		customer.setPostalCode(rs.getString("postalCode"));
		customer.setCountry(rs.getString("country"));
		customer.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
		customer.setCreditLimit(rs.getDouble("creditLimit"));
		
		return customer;
	}
}
