/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the implementation of the CourseRoll class
 * @author Joel McKinney
 */
public class CourseRollTest {

    /**
     * Ensures that the constructor for course roll is properly integrated
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#CourseRoll(int)}.
     */
    @Test
    public void testCourseRoll() {
        Student s = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        CourseRoll cr = new CourseRoll(100, c);
        assertEquals(cr.getEnrollmentCap(), 100);
        assertEquals(cr.getOpenSeats(), 100);

        cr.enroll(s);
        assertEquals(cr.getEnrollmentCap(), 100);
        assertEquals(cr.getOpenSeats(), 99);
    }

    /**
     * Tests to ensure the correct value is returned for the enrollment cap in CourseRoll
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#getEnrollmentCap()}.
     */
    @Test
    public void testGetEnrollmentCap() {
    	Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        CourseRoll cr = new CourseRoll(100, c);
        assertEquals(cr.getEnrollmentCap(), 100);

        CourseRoll cr2 = new CourseRoll(249, c);
        assertEquals(cr2.getEnrollmentCap(), 249);
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#setEnrollmentCap(int)}.
     */
    @Test
    public void testSetEnrollmentCap() {
    	Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        CourseRoll cr = new CourseRoll(10, c);
        assertEquals(cr.getEnrollmentCap(), 10);

        cr.setEnrollmentCap(100);
        assertEquals(cr.getEnrollmentCap(), 100);
        Student s = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Student s2 = new Student("Don", "McKinney", "djmckin2", "djmckin2@ncsu.edu", "password");
        Student s3 = new Student("Sarah", "McKinney", "samckin2", "samckin2@ncsu.edu", "password");
        Student s6 = new Student("Rachel", "McKinney", "remckin2", "remckin2@ncsu.edu", "password");
        Student s7 = new Student("Lindsey", "McKinney", "lcmckin2", "lcmckin2@ncsu.edu", "password");
        Student s8 = new Student("William", "McKinney", "wbmckin2", "wbmckin2@ncsu.edu", "password");
        Student s9 = new Student("Martha", "McKinney", "mamckin2", "mamckin2@ncsu.edu", "password");
        Student s10 = new Student("James", "McKinney", "jdmckin2", "jdmckin2@ncsu.edu", "password");
        Student s11 = new Student("Patrick", "McKinney", "pbmckin2", "pbmckin2@ncsu.edu", "password");
        Student s12 = new Student("Micheal", "McKinney", "mjmckin2", "mjmckin2@ncsu.edu", "password");
        
        cr.enroll(s);
        cr.enroll(s2);
        cr.enroll(s3);
        cr.enroll(s6);
        cr.enroll(s7);
        cr.enroll(s8);
        cr.enroll(s9);
        cr.enroll(s10);
        cr.enroll(s11);
        cr.enroll(s12);

        try {
            cr.setEnrollmentCap(251);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(cr.getEnrollmentCap(), 100);
            assertEquals(e.getMessage(), "A course of that size cannot exisit");
        }
        
        try {
            cr.setEnrollmentCap(9);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(cr.getEnrollmentCap(), 100);
            assertEquals(e.getMessage(), "A course of that size cannot exisit");
        }
    }

    /**
     * Tests to ensure that seats are lowered, and that the cap is correctly implemented as students are added
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#enroll(edu.ncsu.csc216.pack_scheduler.user.Student)}.
     */
    @Test
    public void testEnroll() {
    	Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        Student s = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Student s2 = new Student("Don", "McKinney", "djmckin2", "djmckin2@ncsu.edu", "password");
        Student s3 = new Student("Sarah", "McKinney", "samckin2", "samckin2@ncsu.edu", "password");
        Student s4 = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Student s5 = null;
        Student s6 = new Student("Rachel", "McKinney", "remckin2", "remckin2@ncsu.edu", "password");
        Student s7 = new Student("Lindsey", "McKinney", "lcmckin2", "lcmckin2@ncsu.edu", "password");
        Student s8 = new Student("William", "McKinney", "wbmckin2", "wbmckin2@ncsu.edu", "password");
        Student s9 = new Student("Martha", "McKinney", "mamckin2", "mamckin2@ncsu.edu", "password");
        Student s10 = new Student("James", "McKinney", "jdmckin2", "jdmckin2@ncsu.edu", "password");
        Student s11 = new Student("Patrick", "McKinney", "pbmckin2", "pbmckin2@ncsu.edu", "password");
        Student s12 = new Student("Micheal", "McKinney", "mjmckin2", "mjmckin2@ncsu.edu", "password");
        Student s13 = new Student("Rob", "McKinney", "rdmckin2", "rdmckin2@ncsu.edu", "password");
        CourseRoll cr = new CourseRoll(10, c);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 10);

        cr.enroll(s);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 9);
        
        cr.enroll(s2);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 8);
        
        cr.enroll(s3);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 7);
        
        try {
            cr.enroll(s4);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Student is already enrolled");
            assertEquals(cr.getOpenSeats(), 7);
        }
        
        try {
            cr.enroll(s5);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Cannot add a null student to a course");
            assertEquals(cr.getOpenSeats(), 7);
        }
        
        cr.enroll(s6);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 6);
        
        cr.enroll(s7);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 5);
        
        cr.enroll(s8);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 4);
        
        cr.enroll(s9);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 3);
        
        cr.enroll(s10);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 2);
        
        cr.enroll(s11);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 1);
        
        cr.enroll(s12);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 0);
        
        try {
            cr.enroll(s13);
        } catch (IllegalArgumentException e) {
            assertEquals(cr.getOpenSeats(), 0);
            assertEquals(e.getMessage(), "Cannot add full course to schedule");
        }
    }

    /**
     * Tests to ensure that canEnroll returns true and false correctly
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#canEnroll(edu.ncsu.csc216.pack_scheduler.user.Student)}.
     */
    @Test
    public void testCanEnroll() {
        Student s = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Student s2 = new Student("Don", "McKinney", "djmckin2", "djmckin2@ncsu.edu", "password");
        Student s3 = new Student("Sarah", "McKinney", "samckin2", "samckin2@ncsu.edu", "password");
        Student s4 = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Student s6 = new Student("Rachel", "McKinney", "remckin2", "remckin2@ncsu.edu", "password");
        Student s7 = new Student("Lindsey", "McKinney", "lcmckin2", "lcmckin2@ncsu.edu", "password");
        Student s8 = new Student("William", "McKinney", "wbmckin2", "wbmckin2@ncsu.edu", "password");
        Student s9 = new Student("Martha", "McKinney", "mamckin2", "mamckin2@ncsu.edu", "password");
        Student s10 = new Student("James", "McKinney", "jdmckin2", "jdmckin2@ncsu.edu", "password");
        Student s11 = new Student("Patrick", "McKinney", "pbmckin2", "pbmckin2@ncsu.edu", "password");
        Student s12 = new Student("Micheal", "McKinney", "mjmckin2", "mjmckin2@ncsu.edu", "password");
        Student s13 = new Student("Rob", "McKinney", "rdmckin2", "rdmckin2@ncsu.edu", "password");
        Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        CourseRoll cr = new CourseRoll(10, c);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 10);

        cr.enroll(s);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 9);
        
        cr.enroll(s2);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 8);
        
        cr.enroll(s3);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 7);
        
        cr.enroll(s6);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 6);
        
        cr.enroll(s7);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 5);
        
        cr.enroll(s8);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 4);
        
        cr.enroll(s9);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 3);
        
        cr.enroll(s10);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 2);
        
        cr.enroll(s11);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 1);
        
        cr.enroll(s12);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 0);
        cr.enroll(s4);
        assertEquals(1, cr.getNumberOnWaitlist());
        assertTrue(cr.canEnroll(s13));
        assertFalse(cr.canEnroll(s4));
        
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#drop(edu.ncsu.csc216.pack_scheduler.user.Student)}.
     */
    @Test
    public void testDrop() {
        Student s = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Student s2 = null;
        Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        CourseRoll cr = new CourseRoll(10, c);
        assertEquals(cr.getEnrollmentCap(), 10);
        assertEquals(cr.getOpenSeats(), 10); 

        cr.enroll(s);
        assertEquals(cr.getEnrollmentCap(), 10); 
        assertEquals(cr.getOpenSeats(), 9);
        
        try {
            cr.drop(s2);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(cr.getOpenSeats(), 9);
            assertEquals(e.getMessage(), "Cannot remove a course from a null student");
        }
        
        cr.drop(s);
        assertEquals(cr.getOpenSeats(), 10);
    }

    /**
     * Tests to ensure that adding a student decreases the number of seats available
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#getOpenSeats()}.
     */
    @Test
    public void testgetOpenSeats() {
        Student s = new Student("Joel", "McKinney", "jpmckin2", "jpmckin2@ncsu.edu", "password");
        Student s2 = new Student("Don", "McKinney", "djmckin2", "djmckin2@ncsu.edu", "password");
        Student s3 = new Student("Sarah", "McKinney", "samckin2", "samckin2@ncsu.edu", "password");
        Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
        CourseRoll cr = new CourseRoll(100, c);
        assertEquals(cr.getEnrollmentCap(), 100);
        assertEquals(cr.getOpenSeats(), 100);

        cr.enroll(s);
        assertEquals(cr.getEnrollmentCap(), 100);
        assertEquals(cr.getOpenSeats(), 99);
        
        cr.enroll(s2);
        assertEquals(cr.getEnrollmentCap(), 100);
        assertEquals(cr.getOpenSeats(), 98);
        
        cr.enroll(s3);
        assertEquals(cr.getEnrollmentCap(), 100);
        assertEquals(cr.getOpenSeats(), 97);
    }

}
