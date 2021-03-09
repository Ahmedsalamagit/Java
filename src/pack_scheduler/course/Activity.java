package edu.ncsu.csc216.pack_scheduler.course;
/**
 * Provides a skeleton class for the Course and Event classes
 * Provides functionality to check for conflicts, duplicates, and 
 * getting/setting a variety of variables
 * @author Joel McKinney
 */
public abstract class Activity implements Conflict {

    /** Course's title. */ 
    private String title;
    /** Course's meeting days */
    private String meetingDays;
    /** Course's starting time */
    private int startTime;
    /** Course's ending time */
    private int endTime;
    /** highest value an hour can be in military time */
    private static final int UPPER_HOUR = 23;
    /** highest value a minute can be in military time */
    private static final int UPPER_MIN = 59;
    
    /**
     * Checks to see if two activity object conflict
     * Activity objects conflict when they have an overlapping time on the same day
     * @param possibleConflictingActivity an activity object that may conflict with another activity
     * @throws ConflictException when two activities conflict.
     */
    @Override
    public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
        boolean conflict = false;
        if (this.getStartTime() >= possibleConflictingActivity.getStartTime() && this.getStartTime() <= possibleConflictingActivity.getEndTime() || 
                this.getEndTime() >= possibleConflictingActivity.getStartTime() && this.getEndTime() <= possibleConflictingActivity.getEndTime() || 
                this.getStartTime() <= possibleConflictingActivity.getStartTime() && this.getEndTime() >= possibleConflictingActivity.getEndTime())
        {
            if(this.meetingDays.contains("M") && 
                    possibleConflictingActivity.meetingDays.contains("M"))
            {
                conflict = true;
            }
            else if (this.meetingDays.contains("T") && 
                    possibleConflictingActivity.meetingDays.contains("T"))
            {
                conflict = true;
            }
            else if (this.meetingDays.contains("W") && 
                    possibleConflictingActivity.meetingDays.contains("W"))
            {
                conflict = true;
            } 
            else if (this.meetingDays.contains("H") && 
                    possibleConflictingActivity.meetingDays.contains("H"))
            {
                conflict = true;
            }
            else if (this.meetingDays.contains("F") && 
                    possibleConflictingActivity.meetingDays.contains("F"))
            {
                conflict = true;
            }
            
            if(conflict)
            {
                throw new ConflictException();
            }
        }
    }


    /**
     * Constructs an activity object to be called and used in subclasses
     * @param title title of the event/course
     * @param meetingDays days of an event/course
     * @param startTime time an event/course begins
     * @param endTime time an event/course ends
     */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        super(); 
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

    /**
     * Gives a short display for the GUI
     * Displays only a few of the variables held in an activity object
     * @return short array for display
     */
    public abstract String[] getShortDisplayArray();

    /**
     * Gives a longer display for the GUI
     * Displays all of the variables held in an activity object
     * @return long array for display
     */
    public abstract String[] getLongDisplayArray();

    /**
     * Check for duplicates in subclasses
     * Abstract, expanded in each subclass
     * @param activity the activity to check if it is a dupe
     * @return true if dupe, false otherwise
     */
    public abstract boolean isDuplicate(Activity activity);
    
    /**
     * returns the title of a course
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the course
     * throws exception if title is null or empty
     * @throws IllegalArgumentException if the title is null or empty
     * @param title the title of the course to set
     */
    public void setTitle(String title) {
        if (title == null || title.length() == 0)
        {
            throw new IllegalArgumentException("Invalid title.");
        }
        this.title = title;
    }

    /**
     * returns the days a class meets
     * Is in the form of MWF
     * @return the days a class meets
     */
    public String getMeetingDays() {
        return meetingDays;
    }

    /**
     * returns the start time of a class
     * In military time
     * @return the start time of a class
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * returns the end time of a class
     * In military Time
     * @return the end time of a class
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * sets the meeting day, start, and end times
     * @throws IllegalArgumentException if the number of hours is greater than 24 or less than 0
     * @throws IllegalArgumentException if the number of minutes in greater than 59 or less than 0
     * @param meetingDays the days a class meets
     * @param startTime the time a class starts 
     * @param endTime the time a class ends
     */
    public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

        int startHours = startTime / 100;
        int startMin = startTime % 100;

        if(startHours > UPPER_HOUR || startHours < 0)
        {
            throw new IllegalArgumentException("Invalid start time.");
        }

        if(startMin > UPPER_MIN || startMin < 0)
        {
            throw new IllegalArgumentException("Invalid start time.");
        }

        int endHours = endTime / 100;
        int endMin = endTime % 100;


        if(endHours > UPPER_HOUR || endHours < 0)
        {
            throw new IllegalArgumentException("Invalid end time.");
        }

        if(endMin > UPPER_MIN || endMin < 0)
        {
            throw new IllegalArgumentException("Invalid end time.");
        }

        if(endHours < startHours)
        {
            throw new IllegalArgumentException("End time cannot be before start time.");
        }

        this.meetingDays = meetingDays;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a time string in format days time
     * returned in format 1:30 PM-2:30 PM
     * @param time time an event/course begins
     * @return a time string in format days time
     */
    private String getTimeString(int time)
    {
        int hours = time / 100;
        int min = time % 100;
        boolean pm = false;
        String finalTime = "";

        if (hours >= 12)
        {
            hours = hours - 12;
            pm = true;
        }

        if (hours == 0)
        {
            hours = 12;
        }

        if(pm)
        {
            if(min == 0)
            {
                finalTime = hours + ":" + min + "0PM";
            }
            else
            {
                finalTime = hours + ":" + min + "PM";
            }
        }
        else
        {
            if(min == 0)
            {
                finalTime = hours + ":" + min + "0AM";
            }
            else
            {
                finalTime = hours + ":" + min + "AM";
            }
        }

        return finalTime;
    }

    /**
     * returns the meeting days and times of a class 
     * Combines the meeting days and time into one string (MWF 1:30 PM-2:30 PM)
     * @return the meeting days and times of a class
     */
    public String getMeetingString() {
        String start = getTimeString(this.startTime);
        String end = getTimeString(this.endTime);
        String meetingString = "";

        if ("A".equals(this.meetingDays))
        {
            return "Arranged";
        }
        else
        {
            meetingString = this.meetingDays + " " + start + "-" + end;
            return meetingString;
        }
    }

    /**
     * Creates the hashCode for the Activity class
     * Checks hashCode equivalence of two objects
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endTime;
        result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
        result = prime * result + startTime;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    /**
     * Tests the equivalence of Activity objects
     * Tests the equivalence of two objects
     * @param obj object to be compared to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Activity other = (Activity) obj;
        if (endTime != other.endTime)
            return false;
        if (meetingDays == null) {
            if (other.meetingDays != null)
                return false;
        } else if (!meetingDays.equals(other.meetingDays))
            return false;
        if (startTime != other.startTime)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }    
}