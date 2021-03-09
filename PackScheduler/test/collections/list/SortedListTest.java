package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 *  Tests the implementation of the SortedList Class
 * @author James Williamson
 * @author Joel Mckinney
 * @author Jmeah Clarke
 */
public class SortedListTest {

    /**
     * Test the basic functionality of SortedList 
     * automatically increasing size, initialized with nothing in it
     */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("apple"));
		try {
			list.add("apple");
			list.add("banana");
			list.add("mango");
			list.add("watermelon");
			list.add("orange");
			list.add("kiwi");
			list.add("blueberry");
			list.add("raspberry");
			list.add("blackberry");
			list.add("tomatoe");
			list.add("avocado");
			list.add("peach");
		} catch (IndexOutOfBoundsException e) {
			fail();
		}			
	}

	/**
	 * Tests the add method of the sortedList class
	 * Tests illegal cases such as adding null and duplicate item
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		list.add("apple");
		assertEquals(list.size(), 2);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		list.add("watermelon");
		assertEquals(list.size(), 3);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("watermelon", list.get(2));
		list.add("mango");
		assertEquals(list.size(), 4);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("mango", list.get(2));
		assertEquals("watermelon", list.get(3));
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(list.size(), 4);
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("mango", list.get(2));
			assertEquals("watermelon", list.get(3));
		}
		try {
			list.add("watermelon");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(list.size(), 4);
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("mango", list.get(2));
			assertEquals("watermelon", list.get(3));
		}
	}
	
	/**
	 * Tests the get method of the sortedList class
	 * tests illegal cases such as getting from a null list, getting from a negative index
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
		}
		list.add("strawberry");
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 1);
		}	
	}
	
	/**
	 * tests the remove method of the sortedList class
	 * tests illegal cases such as removing from empty list and from a negative index
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
		}
		
		list.add("apple");
		list.add("banana");
		list.add("mango");
		list.add("strawberry");
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 4);
		}
		
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(list.size(), 4);
		}	
		
		list.remove(2);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("strawberry", list.get(2));
		assertEquals(list.size(), 3);
		
		list.remove(list.size() - 1);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals(list.size(), 2);
		
		list.remove(0);
		assertEquals("banana", list.get(0));
		assertEquals(list.size(), 1);
		
		list.remove(list.size() - 1);
		assertTrue(list.isEmpty());
	}
	
	/**
	 * tests the indexOf method of the sortedList class
	 * tests illegal cases such as finding the index of a null value
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(-1, list.indexOf("apple"));

		list.add("apple");
		list.add("banana");
		list.add("mango");
		list.add("strawberry");
		
		assertEquals(1, list.indexOf("banana"));
		assertEquals(-1, list.indexOf("watermelon"));
		
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(list.size(), 4);
		}	
	}
	
	/**
	 * Tests the clear method of the sortedList class
	 * populates a sorted list and then clears everything
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		list.add("apple");
		list.add("banana");
		list.add("mango");
		list.add("strawberry");
		
		list.clear();
		
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests the is empty functionality of the sortedList class
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		assertTrue(list.isEmpty());
		
		list.add("apple");
		
		assertFalse(list.isEmpty());
	}

	/**
	 * tests the contains functionality of the sortedList class
	 * checks for items that are present and absent
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		assertFalse(list.contains("apple"));
		
		list.add("apple");
		list.add("banana");
		list.add("mango");
		list.add("strawberry");
		
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("watermelon"));
	}
	
	/**
	 * tests the equals method of the sorted list class
	 * creates multiple lists, two are the Equals and the rest are different. These lists are then compared
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("apple");
		list1.add("banana");
		list1.add("mango");
		list1.add("strawberry");
		
		list2.add("apple");
		list2.add("banana");
		list2.add("mango");
		list2.add("strawberry");
		
		list3.add("apple");
		list3.add("banana");
		list3.add("mango");
		list3.add("watermelon");
		
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
	}
	
	/**
	 * tests the hashcode method of the sorted list class
	 * creates multiple lists, two are the same and the rest are different. These lists are then compared
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("apple");
		list1.add("banana");
		list1.add("mango");
		list1.add("strawberry");
		
		list2.add("apple");
		list2.add("banana");
		list2.add("mango");
		list2.add("strawberry");
		 
		list3.add("apple");
		list3.add("banana");
		list3.add("mango");
		list3.add("watermelon");

		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}
 