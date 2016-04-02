package com.common.batch.model;

import java.io.Serializable;

public class Stock implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer stockId;
	private String stockAddress;
	private String stockCode;
	private String stockName;
	public Integer getStockId() {
		return stockId;
	}
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	public String getStockAddress() {
		return stockAddress;
	}
	public void setStockAddress(String stockAddress) {
		this.stockAddress = stockAddress;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Override
	public String toString() {
		return stockId + "|" +stockAddress+ "|" + stockCode + "|" + stockName;
	}
}
