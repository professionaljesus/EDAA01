package bst;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TestBST {
	private BinarySearchTree<Integer> a;
	
	@Before
	public void setUp() throws Exception {
		a = new BinarySearchTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		a = null;
	}

	@Test
	public final void test() {
		
		assertTrue("Not true",a.add(7));
		assertFalse("Dubbleadd", a.add(7));

		a.add(3);
		a.add(11);
		a.add(1);
		a.add(5);
		a.add(9);
		a.add(13);
		assertEquals("wrong height",a.height(),3);

		assertEquals("wrong size",a.size(),7);

	}

}
