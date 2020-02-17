package testqueue;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {

	private FifoQueue<Integer> a;
	private FifoQueue<Integer> b;

	@Before
	public void setUp() throws Exception {
		a = new FifoQueue<Integer>();
		b = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		a = null;
		b = null;
	}
	
	@Test
	public final void testEmptyQueues() {
			a.append(b);
			
			assertEquals("Wrong size", a.size(), 0);
			assertEquals("b not empty", b.size(), 0);


		
		
	}
	
	@Test
	public final void test1Empty2Not() {
		b.offer(1);
		b.offer(2);
		b.offer(3);
		b.offer(4);
		b.offer(5);
		
		a.append(b);
		
		assertEquals("Wrong size", a.size(), 5);
		for (int i = 1; i <= 5; i++) {
			int k = a.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertEquals("b not empty", b.size(), 0);

	}
	
	@Test
	public final void test1Not2Emptyt() {
		a.offer(1);
		a.offer(2);
		a.offer(3);
		a.offer(4);
		a.offer(5);
		a.append(b);
		
		assertEquals("Wrong size", a.size(), 5);

		for (int i = 1; i <= 5; i++) {
			int k = a.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertEquals("b not empty", b.size(), 0);

	}
	
	@Test
	public final void test1Not2Not() {
		a.offer(1);
		a.offer(2);
		a.offer(3);
		a.offer(4);
		a.offer(5);
		b.offer(6);
		b.offer(7);
		b.offer(8);
		b.offer(9);
		b.offer(10);
		a.append(b);
		
		assertEquals("Wrong size", a.size(), 10);

		for (int i = 1; i <= 10; i++) {
			int k = a.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertEquals("b not empty", b.size(), 0);

	}
	
	@Test
	public final void testSameQueue() {
		try {
			a.append(a);
			fail("no exceptions");
		}catch(IllegalArgumentException e) {
			assertEquals("Wrong size", a.size(), 0);
			assertEquals("b not empty", b.size(), 0);


		}
	}
}	
