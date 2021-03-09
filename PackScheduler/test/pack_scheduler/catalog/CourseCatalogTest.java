/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class 
 * @author James Williamson
 *
 */
public class CourseCatalogTest {

    /** Valid course records */
    private final String validTestFile = "test-files/course_records_CourseRoll.txt";
    //private final String invalidTestFile = "test-files/invalid_course_records.txt";

    /** Course name */
    private static final String NAME = "CSC216";
    /** Course title */
    private static final String TITLE = "Software Development Fundamentals";
    /** Course section */
    private static final String SECTION = "001";
    /** Course credits */
    private static final int CREDITS = 3;
    /** Course instructor id */
    private static final String INSTRUCTOR_ID = "sesmith5";
    /** Course meeting days */
    private static final String MEETING_DAYS = "TH";
    /** Course start time */
    private static final int START_TIME = 1330;
    /** Course end time */
    private static final int END_TIME = 1445;
    
    /**
     * Tests CourseCatalog.loadCoursesFromFile()
     */
    @Test
    public void testLoadCoursesFromFile()
    {
        CourseCatalog cc = new CourseCatalog();
        cc.loadCoursesFromFile(validTestFile);
        //Test that all courses are loaded
        assertEquals(13, cc.getCourseCatalog().length);

        //Test invalid file
        try {
            cc.loadCoursesFromFile("Invalid.txt");
            fail();
        } catch (IllegalArgumentException e)
        {
            assertEquals("Cannot find file.", e.getMessage());
        }
    }
    
    /**
     * Test CourseCatalog.getCourseFromCatalog().
     */
    @Test
    public void testGetCourseFromCatalog() {
        CourseCatalog cc = new CourseCatalog();
        cc.loadCoursesFromFile(validTestFile);
        //Attempt to get a course that doesn't exist
        assertNull(cc.getCourseFromCatalog("CSC492", "001"));

        //Attempt to get a course that does exist
        Activity c = new Course(NAME, "Software Development Fundamentals", SECTION, 3, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals(c, cc.getCourseFromCatalog("CSC216", "001"));
    } 
    
    /**
     * Tests CourseCatalog.newCourseCatalog()
     */
    @Test
    public void testNewCourseCatalog() {
    	//Test that if there are courses in the catalog, they 
        //are removed after calling newCourseCatalog().
    	CourseCatalog cc = new CourseCatalog();
    	
    	cc.loadCoursesFromFile(validTestFile);
        assertEquals(13, cc.getCourseCatalog().length);

        cc.newCourseCatalog();
        assertEquals(0, cc.getCourseCatalog().length);
    }
    
    /**
     * Tests CourseCatalog.addCourseToCatalog()
     */
    @Test 
    public void testAddCourseToCatalog() {
    	CourseCatalog cc = new CourseCatalog();

        //Test valid Course
        cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 100, MEETING_DAYS, START_TIME, END_TIME);
        String [][] courseCatalog = cc.getCourseCatalog();
        assertEquals(1, courseCatalog.length);
        assertEquals(NAME, courseCatalog[0][0]);
        assertEquals(SECTION, courseCatalog[0][1]);
        assertEquals(TITLE, courseCatalog[0][2]);
        
        //Test adding second Course
        cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", CREDITS, INSTRUCTOR_ID, 100, "TH", 910, 1100);
        courseCatalog = cc.getCourseCatalog();
        assertEquals(2, courseCatalog.length);
        assertEquals("CSC216", courseCatalog[1][0]);
        assertEquals("001", courseCatalog[1][1]);
        assertEquals("Software Development Fundamentals", courseCatalog[1][2]);
        assertEquals("Intro to Programming - Java", courseCatalog[0][2]);
        
        //Test duplicate Course
        assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 100, MEETING_DAYS, START_TIME, END_TIME));
        
        try {
            cc.addCourseToCatalog("", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 100, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch(IllegalArgumentException e)
        {
            assertEquals("Invalid name", e.getMessage());
        }
    }
    
    /**
     * Tests CourseCatalog.removeCourseFromCatalog()
     */
    @Test
    public void testremoveCourseFromCatalog() {
    	CourseCatalog cc = new CourseCatalog();

        //Add students and remove
        cc.loadCoursesFromFile(validTestFile);
        assertEquals(13, cc.getCourseCatalog().length);
        assertTrue(cc.removeCourseFromCatalog("CSC116", "001"));
        String [][] courseCatalog = cc.getCourseCatalog(); 
        assertEquals(12, courseCatalog.length);
        assertEquals("CSC216", courseCatalog[3][0]);
        assertEquals("002", courseCatalog[3][1]);
        assertEquals("Software Development Fundamentals", courseCatalog[3][2]);
    }
    
    /**
     * Tests CourseCatalog.saveCatalog()
     */
    @Test
    public void testSaveCatalog() {
    	CourseCatalog cc = new CourseCatalog();

        //Add a course
        cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
        cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
        cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 10, "A", 0, 0);
        assertEquals(3, cc.getCourseCatalog().length);
        cc.saveCourseCatalog("test-files/actual_course_records.txt");
        checkFiles("test-files/expected_course_records_CourseRoll.txt", "test-files/actual_course_records.txt");
    }
    
    /**
     * Helper method to compare two files for the same contents
     * @param expFile expected output
     * @param actFile actual output
     */
    private void checkFiles(String expFile, String actFile) {
        try {
            Scanner expScanner = new Scanner(new FileInputStream(expFile));
            Scanner actScanner = new Scanner(new FileInputStream(actFile));

            while (expScanner.hasNextLine()) {
                assertEquals(expScanner.nextLine(), actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }
}
