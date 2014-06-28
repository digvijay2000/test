package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseTestHelper;
import com.twitter.utils.DatabaseUtils;

public class UserDAOImplTest {

	private static User user;
	private static UserDAO userDAO;
	private static DatabaseTestHelper databaseTestHelper;

	@BeforeClass
	public static void setUpBeforeClass()  {
		userDAO = new UserDAOImpl();
		databaseTestHelper = new DatabaseTestHelper();
		user =databaseTestHelper.defineUser();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp()  {
		databaseTestHelper.addUser(user);
		
	}

	@After
	public void tearDown()  {
		userDAO.deleteUser(1);
	}

	

	@Test
	public void deleteUserTest() {
		int actual = userDAO.deleteUser(1);
		assertEquals(1, actual);
	}
	
	
	@Test
	public void addUserTest() {
		userDAO.deleteUser(1);
		int actual = databaseTestHelper.addUser(user);
		assertEquals(1, actual);
	}

	@Test
	public void findByIdTest() {
		User object = userDAO.findById(1);
		assertNotNull(object);
	}

	@Test
	public void findByNameTest() {
		User object = userDAO.findByName("abc@gmail.com");
		assertNotNull(object);
	}

	@Test
	public void updateUserTest() throws SQLException{
		user.setStatus("hey guys i am on twitter");
		user.setPassword("change the password");
		int actual = userDAO.updateUser(user);
		assertEquals(1, actual);
	}

}
