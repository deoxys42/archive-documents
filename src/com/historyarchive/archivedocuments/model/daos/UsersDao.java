package com.historyarchive.archivedocuments.model.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.historyarchive.archivedocuments.model.User;

@Component
public class UsersDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public UsersDao() {}
	
	public UsersDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public User get(String passport) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("passport", passport);
		
		try {
			return jdbcTemplate.queryForObject("select * from users where passport=:passport", params, new RowMapper<User>() {
				
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setPassport(rs.getString("passport"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setSurname(rs.getString("surname"));
					return user;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAll() {
		return jdbcTemplate.query("select * from users", new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setPassport(rs.getString("passport"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				return user;
			}
		});
	}
	
	@Transactional
	public boolean create(User user) {
		return addUser(user) && addAuthority(user);
	}
	
	public boolean addUser(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("passport", user.getPassport());
		params.addValue("password", user.getPassword());
		params.addValue("name", user.getName());
		params.addValue("surname", user.getSurname());
		
		return (jdbcTemplate.update("insert into users (passport, password, name, surname) "
				+ "values (:passport, :password, :name, :surname)", params) == 1);
	}
	
	public boolean addAuthority(User user) {
		user.setAuthority("ROLE_USER");
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return (jdbcTemplate.update("insert into authorities (passport, authority) "
				+ "values (:passport, :authority)", params) == 1);
	}
	
	public boolean existsUserWithPassport(String passport) {
		return jdbcTemplate.queryForObject("select * from users where passport=:passport", 
				new MapSqlParameterSource("passport", passport), Integer.class) > 0;
	}
}