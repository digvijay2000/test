package mypack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddTest {
	
	@Before
	public void setUp() throws Exception {
		int a=2,b=3,c=5,d;
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAdd1() {
		Add add = new Add();
		d=add.add1(a, b);
		assertEquals(c, d);
	}

}
