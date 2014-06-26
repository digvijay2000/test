package com.twitter.doa.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twitter.dao.BlockerDAO;
import com.twitter.dao.impl.BlockersDAOImpl;

public class BlockerDAOImplTest {

	private BlockerDAO blockerDAO;
	@Before
	public void setUp() throws Exception {
		blockerDAO = new BlockersDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddBlocker() {
		int actual = blockerDAO.addBlocker(5, 4);
				assertEquals(1, actual);
	}

	@Test
	public void testDeleteBlocker() {
		int actual = blockerDAO.deleteBlocker(5, 4);
				assertEquals(1, actual);
	}

}
