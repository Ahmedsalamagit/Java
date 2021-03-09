/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests the implementation of the arrayList class
 * @author Joel McKinney
 * @param <E> Represents every type of object. the ArrayList could be of any
 *            object type
 */
public class ArrayListTest<E> {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#size()}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSize() {
		ArrayList<E> test = new ArrayList<E>();
		assertEquals(test.size(), 0);

		test.add(0, (E) "test");
		assertEquals(test.size(), 1);
		assertEquals(test.get(0), "test");

		test.add(0, (E) "testing");
		assertEquals(test.size(), 2);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "test");

		test.add(1, (E) "tester");
		assertEquals(test.size(), 3);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "tester");
		assertEquals(test.get(2), "test");

		test.add(3, (E) "tests");
		assertEquals(test.size(), 4);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "tester");
		assertEquals(test.get(2), "test");
		assertEquals(test.get(3), "tests");

	}

	/**
	 * Tests to ensure that the constructor in the arrayList class is working
	 * properly
	 */
	@Test
	public void testArrayListConstructor() {
		ArrayList<E> test = new ArrayList<E>();
		assertEquals(test.size(), 0);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#add(int, java.lang.Object)}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testAddIntE() {
		ArrayList<E> test = new ArrayList<E>();
		test.add(0, (E) "test");
		assertEquals(test.size(), 1);
		assertEquals(test.get(0), "test");

		test.add(0, (E) "testing");
		assertEquals(test.size(), 2);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "test");

		test.add(1, (E) "tester");
		assertEquals(test.size(), 3);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "tester");
		assertEquals(test.get(2), "test");

		test.add(2, (E) "1");
		test.add(2, (E) "2");
		test.add(2, (E) "3");
		test.add(2, (E) "4");
		test.add(2, (E) "5");
		test.add(2, (E) "6");
		test.add(2, (E) "7");
		test.add(2, (E) "8");
		test.add(2, (E) "9");

		// Attempt to add duplicate item
		try {
			test.add(2, (E) "9");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Cannot add duplicate item to list");
		}

		test = new ArrayList<E>();
		test.add(0, (E) "apple");
		test.add(0, (E) "orange");
		test.add(1, (E) "banana");
		test.add(3, (E) "kiwi");

		try {
			test.add(1, (E) "apple");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Cannot add duplicate item to list");
			assertEquals(test.size(), 4);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#remove(int)}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testRemoveInt() {
		ArrayList<E> test = new ArrayList<E>();
		test.add(0, (E) "test");
		assertEquals(test.size(), 1);
		assertEquals(test.get(0), "test");

		test.add(0, (E) "testing");
		assertEquals(test.size(), 2);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "test");

		test.add(1, (E) "tester");
		assertEquals(test.size(), 3);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "tester");
		assertEquals(test.get(2), "test");

		assertEquals(test.remove(0), "testing");
		assertEquals(test.size(), 2);
		assertEquals(test.get(0), "tester");
		assertEquals(test.get(1), "test");
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#set(int, java.lang.Object)}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSetIntE() {
		ArrayList<E> test = new ArrayList<E>();
		test.add(0, (E) "test");
		assertEquals(test.size(), 1);
		assertEquals(test.get(0), "test");

		test.add(0, (E) "testing");
		assertEquals(test.size(), 2);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "test");

		test.add(1, (E) "tester");
		assertEquals(test.size(), 3);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "tester");
		assertEquals(test.get(2), "test");

		test.remove(0);
		assertEquals(test.size(), 2);
		assertEquals(test.get(0), "tester");
		assertEquals(test.get(1), "test");

		assertEquals(test.set(0, (E) "value"), "tester");
		assertEquals(test.get(0), "value");
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#get(int)}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetInt() {
		ArrayList<E> test = new ArrayList<E>();
		test.add(0, (E) "test");
		assertEquals(test.size(), 1);
		assertEquals(test.get(0), "test");

		test.add(0, (E) "testing");
		assertEquals(test.size(), 2);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "test");

		test.add(1, (E) "tester");
		assertEquals(test.size(), 3);
		assertEquals(test.get(0), "testing");
		assertEquals(test.get(1), "tester");
		assertEquals(test.get(2), "test");
	}

}