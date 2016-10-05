package org.krams.batch;

import java.util.List;

import org.krams.domain.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserItemWriter implements ItemWriter<User> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static final String INSERT_QUERY = "INSERT INTO user(firstName,lastName, password, username) VALUES (?,?,?,?)";

	@Override
	public void write(List<? extends User> users) throws Exception {
		for (User user : users) {
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String passwprd = user.getPassword();
			String username = user.getUsername();
			jdbcTemplate.update(INSERT_QUERY, firstName, lastName, passwprd, username);
		}
	}

}