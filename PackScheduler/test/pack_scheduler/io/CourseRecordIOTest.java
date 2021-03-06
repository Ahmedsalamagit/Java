package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import edu.ncsu.csc217.collections.list.SortedList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CouresRecordIO.
 * @author SarahHeckman
 */ 
public class CourseRecordIOTest {

    /** Valid course records */
    private final String validTestFile = "test-files/course_records_CourseRoll.txt";
    /** Invalid course records */
    private final String invalidTestFile = "test-files/invalid_course_records_CourseRoll.txt";
    
    /** Expected results for valid courses in course_records.txt - line 1 */    
    private final String validCourse1 = "CSC116,Intro to Programming - Java,001,3,jdyoung2,10,MW,910,1100";
    /** Expected results for valid courses in course_records.txt - line 2 */
    private final String validCourse2 = "CSC116,Intro to Programming - Java,002,3,spbalik,10,MW,1120,1310";
    /** Expected results for valid courses in course_records.txt - line 3 */
    private final String validCourse3 = "CSC116,Intro to Programming - Java,003,3,tbdimitr,10,TH,1120,1310";
    /** Expected results for valid courses in course_records.txt - line 6 */
    private final String validCourse5 = "CSC216,Software Development Fundamentals,001,3,sesmith5,10,TH,1330,1445";
    /** Expected results for valid courses in course_records.txt - line 7 */
    private final String validCourse6 = "CSC216,Software Development Fundamentals,002,3,ixdoming,10,MW,1330,1445";
    /** Expected results for valid courses in course_records.txt - line 8 */
    private final String validCourse7 = "CSC216,Software Development Fundamentals,601,3,jctetter,10,A";
    /** Expected results for valid courses in course_records.txt - line 9 */
    private final String validCourse8 = "CSC217,Software Development Fundamentals Lab,202,1,sesmith5,10,M,1040,1230";
    /** Expected results for valid courses in course_records.txt - line 10 */
    private final String validCourse9 = "CSC217,Software Development Fundamentals Lab,211,1,sesmith5,10,T,830,1020";
    
    /** Array to hold expected results */
    private final String [] validCourses = {validCourse1, validCourse2, validCourse3,
            validCourse5, validCourse6, validCourse7, validCourse8, validCourse9};

    /**
     * Resets course_records.txt for use in other tests.
     */
    @Before
    public void setUp() throws Exception {
        //Reset course_records.txt so that it's fine for other needed tests
        Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
        Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
        try { 
            Files.deleteIfExists(destinationPath);
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            fail("Unable to reset files");
        }
    }   
    
    /**
     * Tests readValidCourseRecords().
     */
    @Test
    public void testReadValidCourseRecords() {
        try {
            SortedList<Course> courses = CourseRecordIO.readCourseRecords(validTestFile);
            assertEquals(13, courses.size());
            
            for (int i = 0; i < validCourses.length; i++) {
                assertEquals(validCourses[i], courses.get(i).toString());
            }
        } catch (FileNotFoundException e) {
            fail("Unexpected error reading " + validTestFile);
        }
    }

    /**
     * Tests readInvalidCourseRecords().
     */
    @Test
    public void testReadInvalidCourseRecords() {
        SortedList<Course> courses;
        try {
            courses = CourseRecordIO.readCourseRecords(invalidTestFile);
            assertEquals(0, courses.size());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        }  
    }
    
    /** 
     * Tests writeCourseRecords()
     */
    @Test
    public void testWriteCourseRecords() {
        SortedList<Course> activities = new SortedList<Course>();
        activities.add(new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440));
        activities.add(new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445));
        activities.add(new Course("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 10, "A", 0, 0));
        
        try {
            CourseRecordIO.writeCourseRecords("test-files/actual_course_records.txt", activities);
        } catch (IOException e) {
            fail("Cannot write to course records file");
        }
        
        checkFiles("test-files/expected_course_records_CourseRoll.txt", "test-files/actual_course_records.txt");
    }

    /**
     * Helper method to compare two files for the same contents
     * @param expFile expected output
     * @param actFile actual output
     */
    private void checkFiles(String expFile, String actFile) {
        try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
             Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
            
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