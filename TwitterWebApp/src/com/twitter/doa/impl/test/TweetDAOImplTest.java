package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twitter.dao.TweetDAO;
import com.twitter.dao.impl.TweetDAOImpl;
import com.twitter.pojo.Tweet;

public class TweetDAOImplTest {

	private TweetDAO tweetDAO;

	@Before
	public void setUp() throws Exception {
		tweetDAO = new TweetDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTweetDAOImpl() {

	}

	@Test
	public void testGetAllTweets() {
		List<Integer> list =new ArrayList<Integer>();
		list = tweetDAO.getAllTweets(2);
		assertNotNull(list);

	}

	@Test
	public void testFindById() {
		Tweet tweet = tweetDAO.findById(2);
		assertNotNull(tweet);

	}

	@Test
	public void testAddTweet() {
		int actual = tweetDAO.addTweet(4, "lets tweet one more time");
		assertEquals(1, actual);
	}

	@Test
	public void testDeleteTweet() {
		int actual = tweetDAO.deleteTweet(4, 2);
		assertEquals(1, actual);

	}

}
