package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The student class creates a student object containing information about a student
 * Allows user to obtain and set information about students including first name, email,
 * unity id, last name, password, and the max credits a student can take.
 * @author Ahmed Salama
 * @author Joel McKinney 
 * @author James Williamson
 * @author Jmeah Clarke
 */

public class Student extends User implements Comparable<Student> {
    /** the maximum number of credits for a student */
    private int maxCredits;

    /** the maximum number of credits without advisor approval */
    public static final int MAX_CREDITS = 18;

    /** the minimum number of credits a student can take */
    public static final int MIN_CREDITS = 3;
    
    /** keeps track of the classes a student has registered for */
    public Schedule schedule;

    /** 
     * initializes a Student object with values 
     * Includes maxCredits
     * @param firstName the first name of the student 
     * @param lastName the last name of the student
     * @param id the id of the student
     * @param email the email of the student
     * @param hashPW the encoded password of the student
     * @param maxCredits the maximum number of credits the student can take
     */
    public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
        super(firstName, lastName, id, email, hashPW);
        setMaxCredits(maxCredits);
        schedule = new Schedule();
    }

    /** 
     * Initializes the values of firstName, lastName, id, email, and hashPW for a Student object 
     * Excludes maxCredits, which is defaulted to 18
     * @param firstName the first name of the student 
     * @param lastName the last name of the student
     * @param id the id of the student
     * @param email the email of the student
     * @param hashPW the encoded password of the student
     */
    public Student(String firstName, String lastName, String id, String email, String hashPW) {
        this (firstName, lastName, id, email, hashPW, 18);
        setFirstName(firstName);
        setLastName(lastName);
        setId(id);
        setEmail(email);
        setPassword(hashPW);
        schedule = new Schedule();
    }

    /**
     * gets the max number of credits
     * @return the max number of credits
     */
    public int getMaxCredits() {
        return maxCredits;
    }

    /**
     * sets the max number of credits
     * @throws IllegalArgumentException if the integer isn't between 3 and 18
     * @param maxCredits the max number of credits
     */
    public void setMaxCredits(int maxCredits) {
        if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
            throw new IllegalArgumentException("Invalid max credits");
        }
        this.maxCredits = maxCredits;
    }

    /**
     * overrides toString
     * returns a string with all class information
     * in the format of: "firstaName, lastName, id, email, password, maxCredits"
     */
    @Override
    public String toString() {
        return this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail() + "," + this.getPassword() + "," + this.maxCredits;
    }

    /**
     * compares two student objects to see which order they should be sorted
     * items are sorted by last name, first name, then id
     * @param o the student object used to compare to this. 
     */
    @Override
    public int compareTo(Student o) {
        for (int i = 0; i < this.getLastName().length(); i++)
        {
            if(this.getLastName().charAt(i) > o.getLastName().charAt(i))
            {
                return 1;
            }
            else if(this.getLastName().charAt(i) < o.getLastName().charAt(i))
            {
                return -1;
            }
            else if(this.getLastName().equals(o.getLastName()))
            {
                break;
            }
        }

        for (int i = 0; i < this.getFirstName().length(); i++)
        {
            if(this.getFirstName().charAt(i) > o.getFirstName().charAt(i))
            {
                return 1;
            }
            else if(this.getFirstName().charAt(i) < o.getFirstName().charAt(i))
            {
                return -1;
            }
            else if(this.getFirstName().equals(o.getFirstName())) 
            {
                break;
            }
        }

        for (int i = 0; i < this.getId().length(); i++)
        {
            if(this.getId().charAt(i) > o.getId().charAt(i) || Character.isDigit(o.getId().charAt(i)))
            {
                return 1;
            }
            else if(this.getId().charAt(i) < o.getId().charAt(i))
            {
                return -1;
            }
            else if(this.getId().equals(o.getId()))
            {
                break;
            }
        }
        return 0;
    }

    /**
     * Overrides hashCode 
     * Ensures that the hashCodes are equal
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + maxCredits;
        return result;
    }

    /**
     * Overrides equals
     * Tests to see if two Student objects are equivalent
     * @param obj the object to compare to a student object for equivalence. 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return maxCredits == other.maxCredits;
    }
    
    /**
     * gives the courses a student is currently enrolled in
     * @return a list of courses a student is enrolled in
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Checks to see if a student can be added to a course
     * @param c the course to attempt to add a student to
     * @return true if the student can be added, false otherwise
     */
	public boolean canAdd(Course c) {
		if (c == null)
		    throw new IllegalArgumentException("Course cannot be null");
		int credits = schedule.getScheduleCredits();
		int max = getMaxCredits();
		int newCredits = credits + c.getCredits();
		if (newCredits > max)
		    throw new IllegalArgumentException("Max number of credits reached");
		
		return schedule.canAdd(c);
	}
}


