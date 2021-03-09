package edu.ncsu.csc216.pack_scheduler.course;

/**
 * ConflictException an exception that is thrown when activity objects conflict 
 * @author Joel McKinney
 */
public class ConflictException extends Exception {
    /** ID used for serialization  */
    private static final long serialVersionUID = 1L;
    
    /**
     * Calls the superclass (exception) to produce an error message
     * @param message string to send to the superclass
     */
    public ConflictException(String message) 
    {
        super(message);
    }
    
    /**
     * custom error message for the ConflictException exception
     */
    public ConflictException()
    {
        this("Schedule conflict.");
    }
}
