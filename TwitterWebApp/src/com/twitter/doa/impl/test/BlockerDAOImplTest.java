package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.twitter.dao.BlockerDAO;
import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.BlockersDAOImpl;
import com.twitter.dao.impl.FollowersDAOImpl;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseTestHelper;
import com.twitter.utils.DatabaseUtils;

public class BlockerDAOImplTest {

	private static BlockerDAO blockerDAO;
	private static DatabaseTestHelper databaseTestHelper;
	private static UserDAO  userDAO;
	private static User user;
	

	@BeforeClass
	public static void setUpBeforeClass() {
		userDAO = new UserDAOImpl();
		databaseTestHelper = new DatabaseTestHelper();
		blockerDAO = new  BlockersDAOImpl();
		user =databaseTestHelper.defineUser();
		databaseTestHelper.addUser(user);
	}
	
	
	@Before
	public void setUp() throws Exception {
		user.setUserId(2);
		databaseTestHelper.addUser(user);
	}

	@After
	public void tearDown() throws Exception {
		blockerDAO.deleteBlocker(2, 1);
		userDAO.deleteUser(2);
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.deleteUser(1);
	}

	@Test
	public void testAddBlocker() {
		int actual = blockerDAO.addBlocker(2, 1);
				assertEquals(1, actual);
	}

	@Test
	public void testDeleteBlocker() {
		blockerDAO.addBlocker(2, 1);
		int actual = blockerDAO.deleteBlocker(2, 1);
				assertEquals(1, actual);
	}

}
