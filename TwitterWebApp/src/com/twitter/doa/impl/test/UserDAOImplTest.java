package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseUtils;

public class UserDAOImplTest {

	private User user;
	private UserDAO userDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userDAO = new UserDAOImpl();
		user = new User();
		user.setAlternateNo(1234567890);
		user.setEmailAddress("abc@gmail.com");
		user.setGender("M");
		user.setLastName("ronaldoa");
		user.setFirstName("Cristiano");
		user.setPassword("password");
		Date dob = DatabaseUtils.stringToDate("23-02-1991", "dd-mm-yyyy");
		user.setDob(dob);
		user.setPhoneNo(1234567890);
			
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addUserTest() {
		int actual = userDAO.addUser(user);
		assertEquals(1, actual);
		
	}
	
	@Test
	public void deleteUserTest(){
		int actual = userDAO.deleteUser(4);
		assertEquals(1, actual);
	}
	
	@Test
	public void findByIdTest(){
		User object = userDAO.findById(4);
		assertNotNull(object);
	}
	
	@Test 
		
	public void findByNameTest(){	
		User object = userDAO.findByName("abc@gmail.com");
		assertNotNull(object);
	}
	
	@Test
	public void updateUserTest(){
		User object = userDAO.findById(4);
		object.setStatus("hey guys i am on twitter");
		object.setPassword("change the password");
		
		int actual =userDAO.updateUser(object);
		System.out.println(actual);
		assertEquals(1, actual);
	}
	
	
	

}
