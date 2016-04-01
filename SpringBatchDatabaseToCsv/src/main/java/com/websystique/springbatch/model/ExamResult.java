package com.websystique.springbatch.model;

import java.io.Serializable;

import org.joda.time.LocalDate;


public class ExamResult implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String studentName;
	private LocalDate dob;
	private double percentage;
	

	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public LocalDate getDob() {
		return dob;
	}
	
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "ExamResult [studentName=" + studentName + ", dob=" + dob + ", percentage=" + percentage + "]";
	}
	
	
}
