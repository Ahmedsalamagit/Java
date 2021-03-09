package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class ArrayStackTest {

	@Test
	public void testPush() {
		ArrayStack<Student> array = new ArrayStack<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.push(s1);
		array.push(s);
		assertEquals(s.getEmail(), array.pop().getEmail());
		
	}

	@Test
	public void testPop() {
		ArrayStack<Student> array = new ArrayStack<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.push(s1);
		array.push(s);
		assertEquals(s.getEmail(), array.pop().getEmail());
		
	}

	@Test
	public void testIsEmpty() {
		ArrayStack<Student> array = new ArrayStack<Student>(10);
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
		ArrayStack<Student> array = new ArrayStack<Student>(10);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1"); 
		array.push(s1);
		array.push(s);
		assertEquals(2, array.size());
		
	}

	@Test
	public void testSetCapacity() {
		ArrayStack<Student> array = new ArrayStack<Student>(2);
		Student s = new Student("firstname", "lastname", "ID", "email@email.com", "PW"); 
		Student s1 = new Student("firstname1", "lastname1", "ID1", "email1@email.com", "PW1");
		Student s2 = new Student("firstname2", "lastname2", "ID2", "email2@email.com", "PW2");
		Student s3 = new Student("firstname3", "lastname3", "ID3", "email3@email.com", "PW3");
		array.push(s1);
		array.push(s);

		try {
		array.setCapacity(1);
		fail();
		} catch (Exception e) {
			//do nothing
		}
		assertEquals(2, array.size());
	}

}
