/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * check exception that is thrown when a Course name does not follow the correct template
 * @author ahmed
 * @author Joel McKinney
 */
public class InvalidTransitionException extends Exception {
	/**
     * An encoding device used in exception handling
     */
    private static final long serialVersionUID = 1L;
    /**
     * Passes execption message along to the exception superclass
	 * @param x message to be passed to super class
	 */
	public InvalidTransitionException(String x) {
		super(x);

	}

	/**
	 * Default exception message of the InvalidTransitionException class
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
