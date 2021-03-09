package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the implementation of the schedule class
 * @author Ahmed
 * @author Joel McKinney
 */
public class ScheduleTest {

    /**
     * tests the constructor of the Schedule class to ensure that fields are initialized properly
     */
    @Test
    public void testSchedule() {
        Schedule test = new Schedule();
        assertEquals(test.getTitle(), "My Schedule");
    }

    /**
     * Tests to ensure that courses are properly added to a schedule
     */
    @Test
    public void testAddCourseToSchedule() {
        Schedule s = new Schedule();
        Course c = new Course("CSC216", "title", "001", 3, "id", 100, "TH", 1330, 1445);
        assertTrue(s.addCourseToSchedule(c));
        String[][] test = s.getScheduledCourses();
        assertEquals(test[0][0], "CSC216");
        assertEquals(test[0][1], "001");
        assertEquals(test[0][2], "title");
        assertEquals(test[0][3], c.getMeetingString());
        
        Course c2 = new Course("CSC217", "titles", "002", 3, "id", 100, "M", 1330, 1445);
        assertTrue(s.addCourseToSchedule(c2));
        test = s.getScheduledCourses();
        assertEquals(test[0][0], "CSC216");
        assertEquals(test[0][1], "001");
        assertEquals(test[0][2], "title");
        assertEquals(test[0][3], c.getMeetingString());
        assertEquals(test[1][0], "CSC217");
        assertEquals(test[1][1], "002");
        assertEquals(test[1][2], "titles");
        assertEquals(test[1][3], c2.getMeetingString());
        
        try {
            Course c3 = new Course("CSC226", "titled", "003", 3, "id", 100, "M", 1330, 1445);
            s.addCourseToSchedule(c3);
            fail();
        } catch (IllegalArgumentException e)
        {
            assertEquals(e.getMessage(), "Cannot add course with conflicting time");
        }
        
        try {
            Course c4 = new Course("CSC217", "titles", "002", 3, "id", 100, "W", 1330, 1445);
            s.addCourseToSchedule(c4);
            fail();
        } catch (IllegalArgumentException e)
        {
            assertEquals(e.getMessage(), "Cannot add duplicate course to schedule");
        }
    }

    /**
     * Tests to ensure that courses are properly removed from a schedule 
     */
    @Test
    public void testRemoveCourseFromSchedule() {
        Schedule s = new Schedule();
        Course c = new Course("CSC216", "title", "001", 3, "id", 100, "TH", 1330, 1445);
        assertTrue(s.addCourseToSchedule(c));
        assertTrue(s.removeCourseFromSchedule(c));
    }

    /**
     * Tests to ensure that a schedule is properly cleared
     */
    @Test
    public void testResetSchedule() {
        Schedule s = new Schedule();
        Course c = new Course("CSC216", "title", "001", 3, "id", 100, "TH", 1330, 1445);
        s.addCourseToSchedule(c);
        s.resetSchedule();
        String [][] t = s.getScheduledCourses();
        assertEquals(t.length, 0);
    }

    /**
     * Tests to ensure that the proper schedule is returned
     */
    @Test
    public void testGetScheduledCourses() {
        Schedule s = new Schedule();
        Course c = new Course("CSC216", "title", "001", 3, "id", 100, "TH", 1330, 1445);
        Course c2 = new Course("CSC217", "title1", "002", 3, "id", 100, "M", 1330, 1445);
        assertTrue(s.addCourseToSchedule(c));
        assertTrue(s.addCourseToSchedule(c2));
        String[][] test = s.getScheduledCourses();
        assertEquals(test[0][0], "CSC216");
        assertEquals(test[0][1], "001");
        assertEquals(test[0][2], "title");
        assertEquals(test[1][0], "CSC217");
        assertEquals(test[1][1], "002");
        assertEquals(test[1][2], "title1");
    }

    /**
     * tests to ensure that the title is changed properly
     */
    @Test
    public void testSetTitle() {
        Schedule test = new Schedule();
        test.setTitle("title");
        assertEquals(test.getTitle(), "title");
    }

    /**
     * Ensures that the correct title is returned from the get method
     */
    @Test
    public void testGetTitle() {
        Schedule test = new Schedule();
        assertEquals(test.getTitle(), "My Schedule");
        test.setTitle("test");
        assertEquals(test.getTitle(), "test");
    }

}
