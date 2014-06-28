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

import com.twitter.dao.ReplyDAO;
import com.twitter.dao.TweetDAO;
import com.twitter.dao.UserDAO;
import com.twitter.dao.impl.ReplyDAOImpl;
import com.twitter.dao.impl.TweetDAOImpl;
import com.twitter.dao.impl.UserDAOImpl;
import com.twitter.pojo.Reply;
import com.twitter.pojo.User;
import com.twitter.utils.DatabaseTestHelper;
import com.twitter.utils.DatabaseUtils;

public class ReplyDAOImplTest {
	
	private static ReplyDAO replyDAO;
	private static UserDAO userDAO;
	private static DatabaseTestHelper databaseTestHelper;
	private static User user;
	private static TweetDAO tweetDAO; 

	@BeforeClass
	public static void setUpBeforeClass()  {
		tweetDAO = new TweetDAOImpl();
		replyDAO = new ReplyDAOImpl();
		userDAO = new UserDAOImpl();
		databaseTestHelper = new DatabaseTestHelper();
		user =databaseTestHelper.defineUser();
		databaseTestHelper.addUser(user);
//		databaseTestHelper.addTweet(1, "tweeting for testing", 1);
	}
	
	@Before
	public void setUp() throws Exception {
		databaseTestHelper.addReply(2, "replying for testing", 1, 1);
	}

	@After
	public void tearDown() throws Exception {
//		replyDAO.deleteReply(1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.deleteUser(1);
//		tweetDAO.deleteTweet(1, 1);
	}
	
	@Test
	public void testGetAllReplies() {
		List<Integer> list = new ArrayList<Integer>();
		list = replyDAO.getAllReplies(1);
		assertNotNull(list);
	}

	@Test
	public void testFindById() {
		Reply reply = replyDAO.findById(1);
		assertNotNull(reply);
	}

	@Test
	public void testAddReply() {
		replyDAO.deleteReply(1);
		int actual= databaseTestHelper.addReply(1, "lets reply for testig", 1, 1);
		assertEquals(1, actual);
	}

	@Test
	public void testDeleteReply() {
		int actual = replyDAO.deleteReply(1);
		assertEquals(1, actual);
	}

}
