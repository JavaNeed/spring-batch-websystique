package javabeat.net;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	private String empId;
	private String city;
	private String country;

	@XmlElement(name="EMP_ID")
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@XmlElement(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlElement(name="COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", city=" + city + ", country="	+ country + "]";
	}
	
	
}
