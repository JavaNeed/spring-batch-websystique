package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Orderdetails;

public class OrderdetailMapper implements RowMapper<Orderdetails>{

	@Override
	public Orderdetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		Orderdetails orderdetails = new Orderdetails();
		orderdetails.setOrderNumber(rs.getInt("orderNumber"));
		orderdetails.setProductCode(rs.getString("productCode"));
		orderdetails.setQuantityOrdered(rs.getInt("quantityOrdered"));
		orderdetails.setPriceEach(rs.getDouble("priceEach"));
		orderdetails.setOrderLineNumber(rs.getInt("orderLineNumber"));
		
		return orderdetails;
	}
	
}

