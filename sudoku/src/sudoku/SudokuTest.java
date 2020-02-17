package sudoku;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTest {

	
	Sudoku s;
	
	@Before
	public void setUp() throws Exception {
		s = new Sudoku();
	}

	@After
	public void tearDown() throws Exception {
		s = null;
	}
	
	@Test
	public void testGet() {
		s.set(0, 0, 3);
		assertEquals("Not correct get", s.get(0, 0), 3);
	}
	
	
	@Test
	public void testClear() {
		s.set(0, 0, 3);
		assertEquals("Not correct get", s.get(0, 0), 3);
		s.clear();
		assertEquals("Not correct clear", s.get(0, 0), 0);
	}
	

	@Test
	public void testUnsolvabe() {
	 s.set(0, 0, 5);
	 s.set(0, 1, 5);
	 assertFalse("Should be unsolvable", s.solveSudoku());
	}
	
	@Test
	public void testNothing() {
		assertTrue("Not solved", s.solveSudoku());
	}
	
	@Test
	public void test() {
		s.set(2, 0, 8);
	  s.set(5, 0, 9);
	  s.set(7, 0, 6);
	  s.set(8, 0, 2);
	  
	  s.set(8, 1, 5);
	  
	  s.set(0, 2, 1);
	  s.set(2, 2, 2);
	  s.set(3, 2, 5);
	  
	  s.set(3, 3, 2);
	  s.set(4, 3, 1);
	  s.set(7, 3, 9);
	  
	  s.set(1, 4, 5);
	  s.set(6, 4, 6);
	  
	  s.set(0, 5, 6);
	  s.set(7, 5, 2);
	  s.set(8, 5, 8);
	  
	  s.set(0, 6, 4);
	  s.set(1, 6, 1);
	  s.set(3, 6, 6);
	  
	  s.set(5, 6, 8);
	  
	  s.set(0, 7, 8);
	  s.set(1, 7, 6);
	  s.set(4, 7, 3);
	  s.set(6, 7, 1);
	  
	  s.set(6, 8, 4);
	  
	  assertTrue("Not solved", s.solveSudoku());
	}

}
