package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.twitter.dao.FollowerDAO;
import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.FollowersDAOImpl;
import com.twitter.dao.impl.TweetDAOImpl;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.User;
import com.twitter.pojo.UserFollower;
import com.twitter.utils.DatabaseTestHelper;
import com.twitter.utils.DatabaseUtils;

public class FollowerDAOImplTest {
	
	private static FollowerDAO followerDAO;
	private List<Integer> list;
	private static UserDAO userDAO;
	private static DatabaseTestHelper databaseTestHelper;
	private static User user;

	@BeforeClass
	public static void setUpBeforeClass() {
		userDAO = new UserDAOImpl();
		databaseTestHelper = new DatabaseTestHelper();
		user =databaseTestHelper.defineUser();
		databaseTestHelper.addUser(user);
	}
	
	@Before
	public void setUp() throws Exception {
		user.setUserId(2);
		databaseTestHelper.addUser(user);	
		followerDAO.addFollower(2, 1);
	}

	@After
	public void tearDown() throws Exception {
		followerDAO.deleteFollower(2, 1);
		userDAO.deleteUser(2);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.deleteUser(1);
		
	}


	@Test
	public void testGetAllFollowers() {
		list =new ArrayList<Integer>();
		list = followerDAO.getAllFollowers(1);
		assertNotNull(list);
	}

	@Test
	public void testAddFollower() {
		followerDAO.deleteFollower(2, 1);
		int actual =followerDAO.addFollower(2, 1);
		assertEquals(1, actual);
	}

	@Test
	public void testDeleteFollower() {
		int actual = followerDAO.deleteFollower(2, 1);
		assertEquals(1, actual);
	}

}
