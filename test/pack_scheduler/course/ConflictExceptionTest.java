/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests each of the constructors in the ConflictException class
 * @author Joel McKinney
 */
public class ConflictExceptionTest {

    /**
     * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
     */
    @Test
    public void testConflictExceptionString() {
        ConflictException ce = new ConflictException("Custom exception message");
        assertEquals("Custom exception message", ce.getMessage());
    }

    /**
     * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
     */
    @Test
    public void testConflictException() {
        ConflictException e = new ConflictException();
        assertEquals("Schedule conflict.", e.getMessage());
    }

}
