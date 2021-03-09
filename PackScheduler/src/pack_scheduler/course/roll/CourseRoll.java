/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Determines whether or not a student is capable of enrolling in a class
 * A student can enroll in a class if it does not exceed the max number of credits,
 * if does not conflict with another class on their schedule, and there are seats available in a course
 * @author ahmed
 * @author Joel McKinney
 */
public class CourseRoll {
    
    /** max number of students that can be enrolled in a course */
    private int enrollmentCap;
    /** List of students in a course */
    private LinkedAbstractList<Student> roll;
    /** largest possible class size */
    private static final int MAX_ENROLLMENT = 250;
    /** largest possible wait list size */
    private static final int WAITLIST_SIZE = 10;
    /** smallest possible class size */
    private static final int MIN_ENROLLMENT = 10;
    /** an instance of Course object */
    private Course course;
    /** a linkedQueue list to hold the students in the waitlist */
    private LinkedQueue<Student> waitList;
	/**
	 * Initializes a courseRoll object
	 * sets the initial enrollmentCapacity of a course
	 * @param enrollmentCap number of students that can be enrolled in a course
	 * @param course a field of type object
	 */
	public CourseRoll(int enrollmentCap, Course course) {
	    roll = new LinkedAbstractList<Student>(enrollmentCap);
	    waitList = new LinkedQueue<Student>(WAITLIST_SIZE);
	    setEnrollmentCap(enrollmentCap);
	    setCourse(course);
	}

	/**
	 * Gives the number of students that can be enrolled in a course
	 * @return the enrollmentCap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	private void setCourse(Course course) {
		if (course == null) {
			throw new IllegalArgumentException();
		}
		this.course = course;
	}

	/**
	 * sets the max number of students that can be enrolled in a particular course
	 * @param enrollmentCap the enrollmentCap to set
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap > MAX_ENROLLMENT || enrollmentCap < MIN_ENROLLMENT)
		    throw new IllegalArgumentException("A course of that size cannot exisit");
	roll.setCapacity(enrollmentCap);
	    this.enrollmentCap = enrollmentCap;
	}

	/**
	 * Enrolls a student in a class
	 * enroll takes up a seat and adds the credits associated with a course to the students schedule
	 * @param student the student attempting to enroll in a class
	 */
	//change
	public void enroll(Student student) {
	    if (student == null) {
	        throw new IllegalArgumentException("Cannot add a null student to a course");
	    }
	    if (roll.size() == enrollmentCap) { 
	    	if(waitList.size() == 10) {
	    		throw new IllegalArgumentException("Cannot add full course to schedule");	
	    	}
	    	if(waitList.isEmpty()) {
	    		waitList.enqueue(student);
	    	} else {
	    		waitList.enqueue(student);
	    	}

	    } else {
	    for (int i = 0; i < roll.size(); i++) {
	        if (roll.get(i).equals(student)) {
	            throw new IllegalArgumentException("Student is already enrolled");
	        }
	    }
	    roll.add(student);
	    }
	}

	/**
	 * Determines whether a student can enroll in a class or not
	 * A student can enroll in a class if there are seats and they have room on their schedule
	 * @param student the student that is attempting to enroll in a class
	 * @return true if a student can enroll in the class, false otherwise
	 */
	public boolean canEnroll(Student student) {
	    if (roll.size() == enrollmentCap) {
	    	if(waitList.contains(student))
	        return false;
	    }
	    
	    for (int i = 0; i < roll.size(); i++) {
	        if (roll.get(i).equals(student)) {
	            return false;
	        }
	    }
		return true;
	}

	/**
	 * removes a course from a student's schedule
	 * removing a course takes away credits from a student's schedule
	 * @param student the student to remove a course from
	 */
	public void drop(Student student) {
	
		if (student == null) {
		    throw new IllegalArgumentException("Cannot remove a course from a null student");
		} 
		for(int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(student)) {
				roll.remove(student);
				if(!waitList.isEmpty()) {
					Student c = waitList.dequeue();
					c.schedule.addCourseToSchedule(course);
				roll.add(c);
				
				}
				break;
			}
		}
		
			LinkedQueue<Student> temp = new LinkedQueue<Student>(WAITLIST_SIZE);
			for(int j = 0; j < waitList.size(); j++) {
				Student tempStudent = waitList.dequeue();
				if(!tempStudent.equals(student)) {
					temp.enqueue(student);
				}
			}
			for(int z = 0; z < temp.size(); z++) {
				waitList.enqueue(temp.dequeue());
			}
		
	}

	/**
	 * Gives the number of open seats available in the course
	 * given by enrollmentCap - roll.size
	 * @return number of open seats available in the course
	 */
	public int getOpenSeats() {
		int openSeats = enrollmentCap - roll.size();
	    return openSeats;
	}
	/**
	 * Gives the number of students in the wait list 
	 * 
	 * @return number of students in waitList
	 */
	public int getNumberOnWaitlist() {
		return waitList.size();
		
	}
}
