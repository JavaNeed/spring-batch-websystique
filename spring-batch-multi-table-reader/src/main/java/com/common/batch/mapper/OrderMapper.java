package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Order;

public class OrderMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderNumber(rs.getInt("orderNumber"));
		order.setOrderDate(rs.getDate("orderDate"));
		order.setRequiredDate(rs.getDate("requiredDate"));
		order.setShippedDate(rs.getDate("shippedDate"));
		order.setStatus(rs.getString("status"));
		order.setComments(rs.getString("comments"));
		order.setCustomerNumber(rs.getInt("customerNumber"));

		return order;
	}
}
