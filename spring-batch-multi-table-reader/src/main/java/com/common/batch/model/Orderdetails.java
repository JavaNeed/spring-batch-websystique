package com.common.batch.model;

import java.io.Serializable;

public class Orderdetails implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer orderNumber;
	private String productCode;
	private Integer quantityOrdered;
	private Double priceEach;
	private Integer orderLineNumber;

	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	public Double getPriceEach() {
		return priceEach;
	}
	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}
	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}
	public void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
	@Override
	public String toString() {
		return orderNumber +"|"+ productCode + "|" + quantityOrdered
				+ "|" + priceEach + "|"+ orderLineNumber;
	}
	
	
}
