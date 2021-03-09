/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * @author alajdi
 *
 */
public class LinkedQueueTest {



	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<Student> list = new LinkedQueue<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		list.enqueue(s);
		list.enqueue(s1);
		assertEquals(s.getEmail(), list.dequeue().getEmail());
	}



	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		LinkedQueue<Student> list = new LinkedQueue<Student>(10);
		assertTrue(list.isEmpty());
		try {
			list.dequeue();
			fail();
		} catch (Exception e) {
			//do nothing
		}
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		list.enqueue(s1);
		assertFalse(list.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#size()}.
	 */
	@Test
	public void testSize() {
		LinkedQueue<Student> list = new LinkedQueue<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		list.enqueue(s1);
		list.enqueue(s);
		assertEquals(2, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedQueue#setCapacity(int)}.
	 */
	@Test
	public void testSetCapacity() {
		LinkedQueue<Student> list = new LinkedQueue<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		list.enqueue(s1);
		list.enqueue(s);
		list.setCapacity(2);
		try {
		list.setCapacity(1);
		fail();
		} catch (Exception e) {
			//do nothing
		}
	}

}
