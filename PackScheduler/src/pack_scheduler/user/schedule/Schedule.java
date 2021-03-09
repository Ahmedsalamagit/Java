/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Creates a schedule for a student
 * a schedule holds all of the courses a student is registered for
 * @author Ahmed Salama
 * @author Joel McKinney
 */
public class Schedule {
    /** provides the title of a schedule */
    private String title;
    
    /** gives the student's schedule */
    public ArrayList<Course> schedule;
    
    /**
     * Initializes a schedule object
     * sets the title of a schedule to "My Schedule"
     */
    public Schedule()
    {
        this.title = "My Schedule";
        schedule = new ArrayList<Course>();
    }
    
    /**
     * Attempts to add a course to the schedule
     * if the course is a duplicate, an IAE is thrown from the course class and the course is not added
     * if the course conflicts with another course, a conflict exception is thrown from course and the course is not added
     * if the course is null or empty, a null pointer exception is thrown from the arraylist class and the course is not added.
     * @param c the course to attempt to add to schedule
     * @return true if the course is added and false if it is not
     */
    public boolean addCourseToSchedule(Course c)
    {
        int idx = schedule.size();
        for (int i = 0; i < schedule.size(); i++)
        {
            try {
                schedule.get(i).checkConflict(c);
            } catch (ConflictException e) {
                throw new IllegalArgumentException("Cannot add course with conflicting time");
            }
        }
        
        for (int i = 0; i < schedule.size(); i++)
        {
            try {
                if (schedule.get(i).isDuplicate(c) || schedule.get(i).equals(c))
                {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Cannot add duplicate course to schedule");
            }
            try {
                if (schedule.get(i).getTitle().equals(c.getTitle()))
                {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("You are already enrolled in " + c.getName());
            }
        }
        schedule.add(idx, c);
        return schedule.size() > idx;
    }
    
    /**
     * If a course is found on the schedule, it is removed
     * if the course is not found, this method returns false
     * @param c the course to attempt to remove from schedule
     * @return true if the course is removed, false if it is not
     */
    public boolean removeCourseFromSchedule(Course c)
    {
        return schedule.remove(c);
    }
    
    /**
     * Creates a new empty schedule
     * resets the title to the default value of "My schedule"
     */
    public void resetSchedule()
    {
        schedule = new ArrayList<Course>();
        this.title = "My Schedule";
    }
    
    /**
     * Creates a 3D array that holds information about a course
     * Contains the name, section, and title.
     * Used in the UI to display schedule information correctly.
     * @return a 3D array that gives information about courses in a schedule
     */
    public String[][] getScheduledCourses()
    {
        String [][] scheduledCourses = new String[schedule.size()][5];
        for (int i = 0; i < schedule.size(); i++)
        {
            scheduledCourses[i][0] = schedule.get(i).getName();
            scheduledCourses[i][1] = schedule.get(i).getSection();
            scheduledCourses[i][2] = schedule.get(i).getTitle();
            scheduledCourses[i][3] = schedule.get(i).getMeetingString();
            scheduledCourses[i][4] = Integer.toString(schedule.get(i).getCourseRoll().getOpenSeats());
        }
        return scheduledCourses;
    }
    
    /**
     * sets the title to the given value
     * @param title the value to change the title to
     */
    public void setTitle(String title)
    {
        if (title == null)
        {
            throw new IllegalArgumentException("Title cannot be null");
        }
        this.title = title;
    }
    
    /**
     * Gives the value of the title of the schedule
     * @return the title of the schedule
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Gives the number of credits on a students schedule
     * @return number of credits on a students schedule
     */
    public int getScheduleCredits() {
        int credits = 0;
        for (int i = 0; i < schedule.size(); i++) {
            credits += schedule.get(i).getCredits();
        }
        return credits;
    }
    
    /**
     * checks to see if a course can be added to a schedule.
     * @param add the course to attempt to add
     * @return true if course can be added, false otherwise
     */
    public boolean canAdd(Course add) {
        if (add == null)
            return false;
        for (int i = 0; i < schedule.size(); i++) {
            try {
                schedule.get(i).checkConflict(add);
            } catch (ConflictException e) {
                return false;
            }
            if (schedule.get(i).getName().equals(add.getName()))
                throw new IllegalArgumentException("You are already enrolled in " + add.getName());
        }
        return true;
    }
}
