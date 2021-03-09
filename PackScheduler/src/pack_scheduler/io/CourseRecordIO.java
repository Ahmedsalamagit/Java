package edu.ncsu.csc216.pack_scheduler.io;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc217.collections.list.SortedList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new FileInputStream(fileName));
        SortedList<Course> courses = new SortedList<Course>(); 
        while(fileReader.hasNextLine())
        { 
            try {
                Course course = readCourse(fileReader.nextLine());

                boolean duplicate = false;

                for (int i = 0; i < courses.size(); i++) {
                    Course current = courses.get(i);
                    if (course.getName().equals(current.getName()) &&
                            course.getSection().equals(current.getSection())) {
                        duplicate = true;
                        break;
                    }
                }

                if (!duplicate) {
                    courses.add(course); 
                }
            }
            catch(IllegalArgumentException e) 
            {
                //Do nothing
            }
        }

        fileReader.close();
        return courses;
    }

    /**
     * Writes the given list of Courses to 
     * @param fileName file to write schedule of Courses to
     * @param activities list of Courses to write
     * @throws IOException if cannot write to file
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> activities) throws IOException {
        PrintStream fileWriter = new PrintStream(new File(fileName));
    
        for(int i = 0; i < activities.size(); i++)
        {
            fileWriter.println(activities.get(i).toString());
        }
    
        fileWriter.close();
    }

    
    /**
     * Takes one line at a time from the file and converts it into a course object
     * @param nextLine one line from the file which we are drawing from
     * @return a course object that is constructed from the line imported from the file
     * @throws IllegalArgumentException if no element is read from the file line
     */
    private static Course readCourse(String nextLine) {
        Scanner lineReader = new Scanner(nextLine);
        lineReader.useDelimiter(",");

        String name = "";
        String title = "";
        String section = "";
        int credits = 0;
        String instructorID = "";
        String meetingDays = "";
        int startTime = 0;
        int endTime = 0;
        Course newCourse = null;
        int enrollmentCap = 0;

        while(lineReader.hasNext())
        {
            try {
                name = lineReader.next();
                title = lineReader.next();
                section = lineReader.next();
                credits = lineReader.nextInt();
                instructorID = lineReader.next();
                enrollmentCap = lineReader.nextInt();
                meetingDays = lineReader.next();
                if("A".equals(meetingDays) && !lineReader.hasNextInt())
                {
                    newCourse = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays);
                    break;
                }

                if(lineReader.hasNextInt() && !"A".equals(meetingDays))
                {
                    startTime = lineReader.nextInt();
                    endTime = lineReader.nextInt();
                    newCourse = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays, startTime, endTime);
                }
            }
            catch(NoSuchElementException e)
            {
                lineReader.close();
                throw new IllegalArgumentException();
            }
        }
        lineReader.close();
        return newCourse;
    }

}    
