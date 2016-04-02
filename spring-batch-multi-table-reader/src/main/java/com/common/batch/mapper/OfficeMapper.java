package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Office;

public class OfficeMapper implements RowMapper<Office>{

	@Override
	public Office mapRow(ResultSet rs, int rowNum) throws SQLException {
		Office office = new Office();
		office.setOfficeCode(rs.getString("officeCode"));
		office.setCity(rs.getString("city"));
		office.setPhone(rs.getString("phone"));
		office.setAddressLine1(rs.getString("addressLine1"));
		office.setAddressLine2(rs.getString("addressLine2"));
		office.setState(rs.getString("state"));
		office.setCountry(rs.getString("country"));
		office.setPostalCode(rs.getString("postalCode"));
		office.setTerritory(rs.getString("territory"));
		
		return office;
	}
}
