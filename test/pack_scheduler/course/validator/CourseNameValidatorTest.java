/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the implementation of the CourseNameValidator class
 * Ensures that all states transition properly when given each type of input
 * @author Joel McKinney
 *
 */
public class CourseNameValidatorTest {
    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM#isValid(java.lang.String)}.
     */
    @Test
    public void testIsValid() {
        CourseNameValidator c = new CourseNameValidator();
        try {
            assertTrue(c.isValid("CSC116"));

            assertTrue(c.isValid("E115"));

            assertTrue(c.isValid("PY208"));

            assertTrue(c.isValid("HESF101"));
            
            assertTrue(c.isValid("PY205A"));

            c.isValid("116CSC");
            fail();
        } catch (InvalidTransitionException e) {
            assertEquals(e.getMessage(), "Course name must start with a letter.");
        }

        try {
            c.isValid("&SDF123");
            fail();
        } catch (InvalidTransitionException e)
        {
            assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
        }

        try {
            c.isValid("ANNOYING");
            fail();
        } catch (InvalidTransitionException e)
        {
            assertEquals(e.getMessage(), "Course name cannot start with more than 4 letters.");
        }

        try {
            c.isValid("E1O1");
            fail();
        } catch (InvalidTransitionException e)
        {
            assertEquals(e.getMessage(), "Course name must have 3 digits.");
        }
        
        try {
            c.isValid("E11S");
            fail();
        } catch (InvalidTransitionException e)
        {
            assertEquals(e.getMessage(), "Course name must have 3 digits.");
        }
        
        try {
            c.isValid("E1115");
            fail();
        } catch (InvalidTransitionException e)
        {
            assertEquals(e.getMessage(), "Course name can only have 3 digits.");
        }
        
        try {
            c.isValid("E115A5");
            fail();
        } catch (InvalidTransitionException e)
        {
            assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
        }
        
        try {
            c.isValid("E115AA");
            fail();
        } catch (InvalidTransitionException e)
        {
            assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");
        }
    }

}

