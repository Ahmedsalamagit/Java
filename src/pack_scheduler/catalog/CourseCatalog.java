/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import edu.ncsu.csc217.collections.list.SortedList;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Constructs and creates the course catalog
 * A course catalog holds all possible courses that a student could register for
 * @author Joel McKinney
 * @author James Williamson
 * @author Jmeah Clarke
 */
public class CourseCatalog {
    /** a catalog of courses that a student could add to their schedule */
    private SortedList<Course> catalog;

    /**
     * Creates a new empty catalog at the creation of an object
     */
    public CourseCatalog() {
        this.catalog = new SortedList<Course>();
    }

    /**
     * clears the catalog and makes a new empty catalog
     */
    public void newCourseCatalog() {
        this.catalog = new SortedList<Course>();
    }

    /**
     * populates a catalog with data stored in the file
     * uses Course record io functionality to read the file
     * @param filename the file which the data is pulled from
     */
    public void loadCoursesFromFile(String filename) {
        try {
            this.catalog = CourseRecordIO.readCourseRecords(filename);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find file.");
        }
    }

    /**
     * returns true if a event can be added to a schedule, otherwise false
     * @param title title of the event
     * @param meetingDays days that an event meets
     * @param startTime time an event starts 
     * @param endTime time an event ends
     * @param instructorId the university id number of the professor of a course
     * @param credits the number of credit hours a course is worth
     * @param section the section number of a given Course
     * @param name the name of a given course object
     * @param enrollmentCap gives the number of students that can be enrolled in a course
     * @return true if can add event to schedule, false if it is a dupe
     */
    public boolean addCourseToCatalog(String name, String title, String section, int credits, 
            String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
        Course newEvent = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
 
        int i = 0;
        while (i < catalog.size())
        {
            if(catalog.get(i).isDuplicate(newEvent))
            {
                return false;
            }
            i++;
        }
        catalog.add(newEvent);
        return true;
    }
    
    /**
     * returns true if a course can be removed from a schedule, otherwise false
     * @param name the name of the desired course to be removed
     * @param section the section number of the course to be removed
     * @return true if a course can be removed from a schedule, otherwise false
     */
    public boolean removeCourseFromCatalog(String name, String section) {
        int idx = 0;
        for (int i = 0; i < catalog.size(); i++)
        {
            if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section))
            {
                idx = i;
                break;
            }
        }
        try {
            catalog.remove(idx);
            return true;
        } catch (IndexOutOfBoundsException e)
        {
            return false;
        }
    }

    /**
     * Retrieve a course from the course catalog
     * @param name the name of the course
     * @param section the section number of the course
     * @return the course that matches both parameters
     */
    public Course getCourseFromCatalog(String name, String section) {
        int i = 0;
        while (i < catalog.size())
        {
            if(name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection()))
            {
                return catalog.get(i);
            }
            i++;
        }
        return null;
    }
    
    /**
     * Formats the schedule into a 2D String for the GUI
     * @return a 2D string representation of the schedule
     */
    public String[][] getCourseCatalog() {
        int i = 0;
        int length = catalog.size();
        String [][] gui = new String[length][3];
        while (i < catalog.size())
        {
            gui[i] = catalog.get(i).getShortDisplayArray();
            i++;
        }
        return gui;
    }
    
    /**
     * Exports the schedule to a file. Throws an exception if the file cannot be saved
     * @param filename the name of the created file
     */
    public void saveCourseCatalog(String filename) {
        try {
            CourseRecordIO.writeCourseRecords(filename, catalog);
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("The file cannot be saved.");
        }
    }
}
