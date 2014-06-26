package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twitter.dao.ReplyDAO;
import com.twitter.dao.impl.ReplyDAOImpl;
import com.twitter.pojo.Reply;

public class ReplyDAOImplTest {
	
	private ReplyDAO replyDAO;

	@Before
	public void setUp() throws Exception {
		replyDAO = new ReplyDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllReplies() {
		List<Integer> list = new ArrayList<Integer>();
		list = replyDAO.getAllReplies(2);
		assertNotNull(list);
	}

	@Test
	public void testFindById() {
		Reply reply = replyDAO.findById(1);
		assertNotNull(reply);
	}

	@Test
	public void testAddReply() {
		int actual = replyDAO.addReply(4, "test reply", 2);
		assertEquals(1, actual);
	}

	@Test
	public void testDeleteReply() {
		int actual = replyDAO.deleteReply(2);
		assertEquals(1, actual);
	}

}
