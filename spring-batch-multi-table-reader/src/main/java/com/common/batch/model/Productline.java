package com.common.batch.model;

import java.io.Serializable;
import java.sql.Blob;


public class Productline implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String productLine;
	private String textDescription;
	private Blob htmlDescription;
	private Blob image;
	
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getTextDescription() {
		return textDescription;
	}
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	public Blob getHtmlDescription() {
		return htmlDescription;
	}
	public void setHtmlDescription(Blob htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return productLine + "|"+ textDescription + "|" + htmlDescription+ "|" + image;
	}
}
