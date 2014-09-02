package com.historyarchive.archivedocuments.test.tests;

import static org.junit.Assert.*;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.historyarchive.archivedocuments.model.User;
import com.historyarchive.archivedocuments.model.daos.UsersDao;

public class UsersDaoTests {
	private static ApplicationContext xml;
	
	private static BasicDataSource dataSource;
	
	/* 
	 * usersDao for testing the basic logic of implemented dao-methods
	 * Note: not a production dataSource will be used, so update-methods 
	 * could be invoked without modifying the native tables content
	 */
	private static UsersDao usersDao;
	
	private static User user;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		xml = new ClassPathXmlApplicationContext(
				"com/historyarchive/archivedocuments/test/config/datasource-for-tests.xml");
		
		dataSource = (BasicDataSource)xml.getBean("dataSource");
		usersDao = new UsersDao(dataSource);
		
		user = new User("passport10", "password10", "name10", "surname10");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		((ClassPathXmlApplicationContext)xml).close();
	}
	
	@Before
	public void tearDown() throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.execute("delete from users");
		jdbcTemplate.execute("delete from authorities");
	}
	
	@Test
	public void testGet_checkTheUserInTheTables_methodReturnsTheValueAccordingToThatCheck() {
		usersDao.create(user);
		User _user = usersDao.get(user.getPassport());
		
		assertEquals("passports do not match;", user.getPassport(), _user.getPassport());
		assertEquals("passwords do not match;", user.getPassword(), _user.getPassword());
		assertEquals("names do not match;", user.getName(), _user.getName());
		assertEquals("surnames do not match", user.getSurname(), _user.getSurname());
	}
	
	@Test
	public void testGetAll_checkAllUsersInTheTables_methodReturnsTheValueAccordingToThatCheck() {
		int list_size = 8;
		List<User> users = new ArrayList<User>();
		try {
			for (int i = 0; i < list_size; i++) {
				User _user = user.clone();
				_user.setPassport("passport1" + i);
				usersDao.create(_user);
				users.add(_user);
			}
			List<User> results = usersDao.getAll();
			assertEquals(users.size(), results.size());
			
			for (int i = 0; i < list_size; i++) {
				User curUser = results.get(i); 
				assertEquals("list doesn't containt a user from database;", true, 
						results.contains(curUser));
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddUser_methodReturnsTheValue_usersTableWasModifiedAccordingToThatValue() {
		boolean result = usersDao.addUser(user);
		List<User> results = new ArrayList<User>();
		
		try (Statement stmt = dataSource.getConnection().createStatement()) {
			
			ResultSet rs = stmt.executeQuery("select * from users where passport='" 
					+ user.getPassport() + "'");
			
			while (rs.next()) {
				User _user = new User(rs.getString("passport"), rs.getString("password"),
						rs.getString("name"), rs.getString("surname"));
				_user.setEnabled(!("0".equals(rs.getString("enabled"))));
				results.add(_user);
			}
		
			if (result) {
				assertFalse("user was not created;", results.size() == 0);
				assertFalse("some user duplicates were created;", 
						results.size() != 0 && results.size() != 1);
				assertEquals("passports do not match;", 
						user.getPassport(), results.get(0).getPassport());
				assertEquals("passwords do not match;", 
						user.getPassword(), results.get(0).getPassword());
				assertEquals("names do not match;", 
						user.getName(), results.get(0).getName());
				assertEquals("surnames do not match;", 
						user.getSurname(), results.get(0).getSurname());
				assertEquals("enablings do not match;", 
						true, results.get(0).isEnabled());
			} else {
				assertTrue("user was created while it wasn't supposed to;", results.size() == 0);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	@Test
	public void testAddAuthority_methodReturnsTheValue_authoritiesTableWasModifiedAccordingToThatValue() {
		user.setAuthority("ROLE_USER");
		
		boolean result = usersDao.addAuthority(user);
		List<User> results = new ArrayList<User>();
		
		try (Statement stmt = dataSource.getConnection().createStatement()) {
			
			ResultSet rs = stmt.executeQuery("select * from authorities where passport='" 
					+ user.getPassport() + "'");
			
			while (rs.next()) {
				User _user = new User();
				_user.setPassport(rs.getString("passport"));
				_user.setAuthority(rs.getString("authority"));
				results.add(_user);
			}
		
			if (result) {
				assertFalse("authority was not created;", results.size() == 0);
				assertFalse("some authority duplicates were created;", 
						results.size() != 0 && results.size() != 1);
				assertEquals("passports do not match;", 
						user.getPassport(), results.get(0).getPassport());
				assertEquals("authorities do not match;", 
						user.getAuthority(), results.get(0).getAuthority());
			} else {
				assertTrue("authority was created while it wasn't supposed to;", results.size() == 0);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	@Ignore("Require Transaction Manager implemented")
	@Test(expected=DuplicateKeyException.class)
	public void testCreate_userAlreadyExists_tablesWereNotModified() {
		try {
			usersDao.addUser(user);
			usersDao.create(user);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}
		
		try (Statement stmt = dataSource.getConnection().createStatement()) {
			
			assertEquals("user should exist;", true, 
					stmt.executeQuery("select * from users where passport='"
					+ user.getPassport() + "'").next());
			assertEquals("there must be no authorities;", false,
					stmt.executeQuery("select * from authorities where passport='"
					+ user.getPassport() + "'").next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Ignore("Require Transaction Manager implemented")
	@Test(expected=DuplicateKeyException.class)
	public void testCreate_authorityAlreadyExists_tablesWereNotModified() {
		try {
			usersDao.addAuthority(user);
			usersDao.create(user);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}
		
		try (Statement stmt = dataSource.getConnection().createStatement()) {
			
			assertEquals("authority should exist;", true, 
					stmt.executeQuery("select * from authorities where passport='"
					+ user.getPassport() + "'").next());
			assertEquals("there must be no users;", false, 
					stmt.executeQuery("select * from users where passport='"
					+ user.getPassport() + "'").next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}