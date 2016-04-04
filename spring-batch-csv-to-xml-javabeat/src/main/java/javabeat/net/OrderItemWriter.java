package javabeat.net;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.batch.item.ItemWriter;


public class OrderItemWriter implements ItemWriter<Employee> {
	//private static final String INSERT_EMPLOYEE = "insert into employee(empid,city,country) values(?,?,?)";
	private static final String INSERT_EMPLOYEE = "insert into employee(EMP_ID,CITY,COUNTRY) values(?,?,?)";
	private JdbcTemplate jdbcTemplate;

	public OrderItemWriter(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public void write(List<? extends Employee> employees) throws Exception {
		for (Employee order : employees) {
			jdbcTemplate.update(INSERT_EMPLOYEE, order.getEmpId(),order.getCity(), order.getCountry());
		}
	}
}
