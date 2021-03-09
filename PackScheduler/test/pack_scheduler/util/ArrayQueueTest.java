/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * @author alajd
 *
 */
public class ArrayQueueTest {

	

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<Student> array = new ArrayQueue<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.enqueue(s);
		array.enqueue(s1);
		assertEquals(s.getEmail(), array.dequeue().getEmail());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<Student> array = new ArrayQueue<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.enqueue(s1);
		array.enqueue(s);
		assertEquals(s1.getEmail(), array.dequeue().getEmail());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		ArrayQueue<Student> array = new ArrayQueue<Student>(10);
		assertTrue(array.isEmpty());
		try {
			array.dequeue();
			fail();
		} catch (Exception e) {
			//do nothing
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#size()}.
	 */
	@Test
	public void testSize() {
		ArrayQueue<Student> array = new ArrayQueue<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.enqueue(s1);
		array.enqueue(s);
		assertEquals(2, array.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayQueue#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<Student> array = new ArrayQueue<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.enqueue(s1);
		array.enqueue(s);
		array.setCapacity(2);
		try {
		array.setCapacity(1);
		fail();
		} catch (Exception e) {
			//do nothing
		}
		assertEquals(2, array.size());
	}

}
