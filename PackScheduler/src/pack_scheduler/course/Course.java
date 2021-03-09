/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * The course class constructs a course objectS
 * A course object consists of information about a course
 * information includes the name, title, credits, section, and instructor id of a course
 * @author Joel McKinney
 * Creates a course object 
 */
public class Course extends Activity implements Comparable<Course> {

    /** Course's name. */
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    /** number of digits in a section number */
    private static final int SECTION_LENGTH = 3;
    /** minimum number of credits */
    private static final int MIN_CREDITS = 1;
    /** maximum number of credits */
    private static final int MAX_CREDITS = 5;
    /**Course name validator*/
    private CourseNameValidator validator;
    /** Gives all of the students enrolled in a particular course */
    public CourseRoll roll;
    
    /**
     * Returns the courses name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the courses name
     * @throws IllegalArgumentException if the name is null or empty
     * @throws IllegalArgumentException if the name is not in the correct format (CSC 216)
     * @param name the name to set
     */
    private void setName(String name)  {
        //Throw exception if the name is null
        boolean valid = false;
        if(name == null)
            throw new IllegalArgumentException("Name cannot be null.");
        if(name.length() < 4 || name.length() > 8)
        {
            throw new IllegalArgumentException("Invalid name");
        }
        
        try {
            valid = validator.isValid(name);
        } catch (InvalidTransitionException e) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (valid)
            this.name = name;
        else
            throw new IllegalArgumentException("Invalid name");
    }

    /**
     * returns the section number of a course
     * @return the section number
     */
    public String getSection() {
        return section;
    }

    /**
     * sets the section number
     * @throws IllegalArgumentException if the section is null or empty
     * @throws IllegalArgumentException if the section is not a number
     * @param section the section number to set
     */
    public void setSection(String section) {
        if(section == null || section.length() != SECTION_LENGTH)
        {
            throw new IllegalArgumentException("Invalid section.");
        }
        for(int i = 0; i < section.length(); i++)
        {
            if(!Character.isDigit(section.charAt(i)))
            {
                throw new IllegalArgumentException("Section should be three digits.");
            }
        }
        this.section = section;
    }

    /**
     * returns the number of credits
     * number of credits is the number of credit hours a class is worth
     * @return the number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * sets the number of credits
     * @throws IllegalArgumentException if the credits are not between 1 and 5
     * @param credits the number of credits to set
     */
    public void setCredits(int credits) {
        if(credits < MIN_CREDITS || credits > MAX_CREDITS)
        {
            throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
        }
        this.credits = credits;
    }

    /**
     * returns the instructor's id number
     * @return the instructor's id number
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * sets the instructor's id number
     * @throws IllegalArgumentException if the instructor id is null or empty
     * @param instructorId the instructor's id number to set
     */
    public void setInstructorId(String instructorId) {
        if (instructorId == null || instructorId.length() == 0)
        {
            throw new IllegalArgumentException("Invalid instructor id.");
        }
        this.instructorId = instructorId;
    }

    /**
     * Constructs a course object with values for all fields
     * @param name name of a Course
     * @param title title of a Course
     * @param section section number of a Course
     * @param credits number of credits provided for a Course
     * @param instructorId unity id of instructor of Course
     * @param meetingDays days that a Course meets as chars
     * @param startTime start time of a Course
     * @param endTime end time of a Course
     * @param enrollmentCap Gives the number of students that can enroll in a certain course;
     */
    public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
            String meetingDays, int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        this.validator = new CourseNameValidator();
        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        roll = new CourseRoll(enrollmentCap, this);
    }

    /**
     * Constructs a Course with given name, title, section, credits, instructorID, and meetingDays for
     * courses that are arranged
     * maxCredits is set to its default of 18
     * @param name name of a Course
     * @param title title of a Course
     * @param section section number of a Course
     * @param credits number of credits provided for a Course
     * @param instructorId unity id of instructor of Course
     * @param meetingDays days that a Course meets as chars
     * @param enrollmentCap Gives the number of students that can enroll in a certain course;
     */
    public Course(String name, String title, String section, int credits, String instructorId, 
           int enrollmentCap, String meetingDays) {
        this(name, title, section, credits, instructorId,  enrollmentCap, meetingDays, 0, 0);
        this.validator = new CourseNameValidator();
        setName(name);
        setTitle(title);
        setSection(section);
        setCredits(credits); 
        setInstructorId(instructorId);
        setMeetingDaysAndTime(meetingDays, 0, 0);
    }

    /**
     * creates the meetingDaysAndTime string, which gives the meeting days and times 
     * of a class in one string (MWF 1:30 PM-2:30PM)
     * @throws IllegalArgumentException if the meetingDays string is null or empty
     * @param meetingDays a string that gives meeting days in format of MWF
     * @param startTime the start time of a class in military time
     * @param endTime the end tiem of a class in military time
     */
    @Override 
    public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
        if (meetingDays == null || meetingDays.length() == 0)
        {
            throw new IllegalArgumentException("Invalid meeting days.");
        }

        if ("A".equals(meetingDays))
        {
            super.setMeetingDaysAndTime(meetingDays, 0, 0);
        }

        else
        {
            int mon = 0;
            int tues = 0;
            int wends = 0;
            int thurs = 0;
            int fri = 0;

            for (int i = 0; i < meetingDays.length(); i++)
            {
                if(meetingDays.charAt(i) == 'M')
                {
                    mon++;
                }
                else if(meetingDays.charAt(i) == 'T')
                {
                    tues++;
                }
                else if(meetingDays.charAt(i) == 'W')
                {
                    wends++;
                }
                else if(meetingDays.charAt(i) == 'H')
                {
                    thurs++;
                }
                else if(meetingDays.charAt(i) == 'F')
                {
                    fri++;
                }
                else
                {
                    throw new IllegalArgumentException("Invalid meeting days.");
                }
            }

            if(mon > 1 || tues > 1 || wends > 1 || thurs > 1 || fri > 1)
            {
                throw new IllegalArgumentException("Invalid meeting days.");
            }
            super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
        }
    }

    /**
     * Returns a comma separated value String of all course fields.
     * gives a comma separated list of all variables in the course class
     * @return String representation of Course
     */
    @Override
    public String toString() {
        if ("A".equals(getMeetingDays())) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + 
                    roll.getEnrollmentCap() + "," + getMeetingDays();
        }
        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + 
        roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
    }

    /**
     * Generates the hashCode for a Course object
     * Checks the equivalence of the hashCode of two objects
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + credits;
        result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        return result;
    }

    /**
     * tests the equivalence of Course objects
     * checks each field of a Course obeject and compares it to this for equivalence.
     * @param obj the object to be compared 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (credits != other.credits)
            return false;
        if (instructorId == null) {
            if (other.instructorId != null)
                return false;
        } else if (!instructorId.equals(other.instructorId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (section == null) {
            if (other.section != null)
                return false;
        } else if (!section.equals(other.section))
            return false;
        return true;
    }

    /**
     * Gives a short display for the GUI
     * displays only the name, section, title, and meetingString
     * @return short array for display
     */
    @Override
    public String[] getShortDisplayArray() {
        String [] shortDisplay = new String[5];
        shortDisplay[0] = name;
        shortDisplay[1] = section;
        shortDisplay[2] = getTitle(); 
        shortDisplay[3] = getMeetingString();
        shortDisplay[4] = Integer.toString(roll.getOpenSeats());
        return shortDisplay;
    }

    /**
     * Gives a longer display for the GUI
     * Displays all information about a course object
     * @return long array for display
     */
    @Override
    public String[] getLongDisplayArray() {
        String [] longDisplay = new String[7];
        longDisplay[0] = name;
        longDisplay[1] = section;
        longDisplay[2] = getTitle();
        longDisplay[3] = Integer.toString(credits);
        longDisplay[4] = instructorId;
        longDisplay[5] = getMeetingString();
        longDisplay[6] = "";
        return longDisplay;
    }

    /**
     * Determines whether two course object are the same
     * inherited from activity
     * @return true if a course object is a duplicate, false otherwise
     */
    @Override
    public boolean isDuplicate(Activity activity) {
        if (activity.getClass() == this.getClass())
        {
            Course newCourse = (Course) activity;
            if (newCourse.getTitle().equals(this.getTitle()) && newCourse.getSection().equals(this.getSection())) 
            {
                return true;
            }
        }
        return false;
    }

    /**
     * sorts Course objects in the order of name, then section
     * @param o Course object to be compared to this
     * @return 1 if sorted before, 0 if they are the same, and -1 if after
     */
    @Override
    public int compareTo(Course o) {
        for (int i = 0; i < this.getName().length(); i++)
        {
            if(this.getName().charAt(i) > o.getName().charAt(i))
            {
                return 1;
            }
            else if(this.getName().charAt(i) < o.getName().charAt(i))
            {
                return -1;
            }
            else if(this.getName().equals(o.getName()))
            {
                break;
            }
        }

        for (int i = 0; i < this.getSection().length(); i++)
        {
            if(this.getSection().charAt(i) > o.getSection().charAt(i))
            {
                return 1;
            }
            else if(this.getSection().charAt(i) < o.getSection().charAt(i))
            {
                return -1;
            }
            else if(this.getSection().equals(o.getSection()))
            {
                break;
            }
        }
        return 0;
    }
    
    /**
     * Gives the course roll, which contains all students enrolled in a particular course
     * @return roll, the list of students enrolled in a course
     */
    public CourseRoll getCourseRoll() {
        return roll;
    }
}
