package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class LinkedStackTest {



	@Test
	public void testPop() {
		LinkedStack<Student> array = new LinkedStack<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.push(s1);
		array.push(s);
		assertEquals(s.getEmail(), array.pop().getEmail());
		
	}

	@Test
	public void testIsEmpty() {
		LinkedStack<Student> array = new LinkedStack<Student>(10);
		assertTrue(array.isEmpty());
		try {
			array.pop();
			fail();
		} catch (Exception e) {
			//do nothing
		}
	}

	@Test
	public void testSize() {
		LinkedStack<Student> array = new LinkedStack<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.push(s1);
		array.push(s);
		assertEquals(2, array.size());
		
	}

	@Test
	public void testSetCapacity() {
		LinkedStack<Student> array = new LinkedStack<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.push(s1);
		array.push(s);
		array.setCapacity(2);
		try {
		array.setCapacity(1);
		fail();
		} catch (Exception e) {
			//do nothing
		}
	}

}
