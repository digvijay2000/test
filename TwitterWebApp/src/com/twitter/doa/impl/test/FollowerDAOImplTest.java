package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twitter.dao.FollowerDAO;
import com.twitter.dao.impl.FollowersDAOImpl;
import com.twitter.pojo.UserFollower;

public class FollowerDAOImplTest {
	
	private FollowerDAO followerDAO;
	private List<Integer> list;

	@Before
	public void setUp() throws Exception {
		followerDAO = new FollowersDAOImpl();
		list= null;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllFollowers() {
		list = followerDAO.getAllFollowers(4);
		assertNotNull(list);
		
	}

	@Test
	public void testAddFollower() {
		int actual =followerDAO.addFollower(4, 5);
		assertEquals(1, actual);
	}

	@Test
	public void testDeleteFollower() {
		int actual = followerDAO.deleteFollower(4, 5);
		assertEquals(1, actual);
	}

}
