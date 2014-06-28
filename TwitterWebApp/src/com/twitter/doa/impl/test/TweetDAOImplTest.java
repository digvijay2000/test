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

import com.twitter.dao.TweetDAO;
import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.TweetDAOImpl;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.Tweet;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseTestHelper;
import com.twitter.utils.DatabaseUtils;

public class TweetDAOImplTest {

	private static TweetDAO tweetDAO;
	private static DatabaseTestHelper databaseTestHelper;
	private static User user;
	private static UserDAO userDAO;
	@BeforeClass
	public static void setUpBeforeClass() {
		tweetDAO = new TweetDAOImpl();
		userDAO = new UserDAOImpl();
		databaseTestHelper = new DatabaseTestHelper();
		user =databaseTestHelper.defineUser();
		databaseTestHelper.addUser(user);
	}

	@Before
	public void setUp() throws Exception {
		databaseTestHelper.addTweet(1, "lets tweet for testing", 1);

	}

	@After
	public void tearDown() throws Exception {
		tweetDAO.deleteTweet(1, 1);
	}

	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.deleteUser(1);
		
	}

	@Test
	public void testGetAllTweets() {
		List<Integer> list = new ArrayList<Integer>();
		list = tweetDAO.getAllTweets(1);
		assertNotNull(list);

	}

	@Test
	public void testFindById() {
		Tweet tweet = tweetDAO.findById(1);
		assertNotNull(tweet);

	}

	@Test
	public void testAddTweet() {
		tweetDAO.deleteTweet(1, 1);
		int actual = databaseTestHelper
				.addTweet(1, "lets tweet for testing", 1);
		assertEquals(1, actual);
	}

	@Test
	public void testDeleteTweet() {
		int actual = tweetDAO.deleteTweet(1, 1);
		assertEquals(1, actual);
	}

}
