package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of the Activity class
 * @author Joel McKinney
 */
public class ActivityTest {

    /** 
     * tests the viability of the checkConflit method
     */
    @Test
    public void testCheckConflict()
    {
        Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
        Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330, 1445);
        try {
            a1.checkConflict(a2);
            assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
            assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
        } catch(ConflictException e) {
            fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
        }
        try {
            a2.checkConflict(a1);
            assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
            assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
        } catch(ConflictException e) {
            fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
        }
    }
    
    /**
     * Testing an exception case that will cause an ConflictException exception
     */
    @Test
    public void testCheckConflictWithConflict() {
        Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
        Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "M", 1330, 1445);
        Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "WH", 1445, 1600);
        Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MWF", 1330, 1445);
        Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "F", 1200, 1330);
        Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1400, 1430);
        try {
            a1.checkConflict(a2);
            fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
        } catch (ConflictException e) {
            assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
            assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
        }
        
        try {
            a1.checkConflict(a3);
            fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
        } catch (ConflictException e) {
            assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
            assertEquals("Incorrect meeting string for possibleConflictingActivity.", "WH 2:45PM-4:00PM", a3.getMeetingString());
        }
        
        try {
            a3.checkConflict(a4);
            fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
        } catch (ConflictException e) {
            assertEquals("Incorrect meeting string for this Activity.", "WH 2:45PM-4:00PM", a3.getMeetingString());
            assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MWF 1:30PM-2:45PM", a4.getMeetingString());
        }
        
        try {
            a4.checkConflict(a5);
            fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
        } catch (ConflictException e) {
            assertEquals("Incorrect meeting string for this Activity.", "MWF 1:30PM-2:45PM", a4.getMeetingString());
            assertEquals("Incorrect meeting string for possibleConflictingActivity.", "F 12:00PM-1:30PM", a5.getMeetingString());
        }
        
        try {
            a6.checkConflict(a1);
            fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
        } catch (ConflictException e) {
            assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
            assertEquals("Incorrect meeting string for this Activity.", "MW 2:00PM-2:30PM", a6.getMeetingString());
        }
    }
}
