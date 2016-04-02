package com.common.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.batch.model.Productline;

public class ProductlineMapper implements RowMapper<Productline>{

	@Override
	public Productline mapRow(ResultSet rs, int rowNum) throws SQLException {
		Productline productline = new Productline();
		productline.setProductLine(rs.getString("productLine"));
		productline.setTextDescription(rs.getString("textDescription"));
		productline.setHtmlDescription(rs.getBlob("htmlDescription"));
		productline.setImage(rs.getBlob("image"));

		return productline;
	}
	
}
