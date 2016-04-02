package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Payment;

public class PaymentMapper implements RowMapper<Payment>{

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment();
		payment.setCustomerNumber(rs.getInt("customerNumber"));
		payment.setCheckNumber(rs.getString("checkNumber"));
		payment.setPaymentDate(rs.getDate("paymentDate"));
		payment.setAmount(rs.getDouble("amount"));

		return payment;
	}
}
