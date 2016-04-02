package com.common.batch.model;

import java.io.Serializable;
import java.sql.Date;

public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer customerNumber;
	private String checkNumber;
	private Date paymentDate;
	private Double amount;
	
	
	public Integer getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return customerNumber + "|"+ checkNumber + "|" + paymentDate + "|"+ amount ;
	}
}
