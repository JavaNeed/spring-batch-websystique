package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Stock;

public class StockMapper implements RowMapper<Stock>{

	@Override
	public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stock stock = new Stock();
		stock.setStockId(rs.getInt("stockId"));
		stock.setStockAddress(rs.getString("stockAddress"));
		stock.setStockCode(rs.getString("stockCode"));
		stock.setStockName(rs.getString("stockName"));
		
		return stock;
	}

}
