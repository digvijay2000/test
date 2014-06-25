package mypack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DemoTest {

	@Before
	public void setUp() throws Exception {
		int a=2;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(2, a);
	}

}
