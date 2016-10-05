package org.krams.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.krams.domain.Role;
import org.krams.domain.User;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class RoleFieldSetMapper implements FieldSetMapper<Role> {
	public static final String SELECT_QUERY = "SELECT id, firstName, lastName, password, username FROM user WHERE username = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public Role mapFieldSet(FieldSet fs) {
		if (fs == null) {
			return null;
		}

		Object[] params = new Object[1];
		params[0] = fs.readString("username");

		User user = jdbcTemplate.queryForObject(SELECT_QUERY, params, this::mapUser);
		
		Role role = new Role();
		role.setUser(user);
		role.setRole(fs.readInt("role"));

		return role;
	}

	private User mapUser(ResultSet rs, int row) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));

		return user;
	}

}