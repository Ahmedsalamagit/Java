
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * A state machine that ensures that course names are correct
 * A valid course name contains 1-4 letters followed by 1-3 digits 
 * suffixes are optional
 * @author ahmed
 * @author Joel McKinney
 */
public class CourseNameValidator {

    /** check to see if the state is an end state (suffix or digit) */
    private boolean validEndState;
    /** keeps track of the number of letters in a course name */
    private int letterCount;
    /** keeps track of the number of digits in a course name */
    private int digitCount;
    /** the number state is the state a coure name is put in when a number is found in the course name */
    private final NumberState stateNumber = new NumberState();
    /** the letter state is the staet a course is put when a letter is found in the course name */
    private final LetterState stateLetter = new LetterState();
    /** the inital state is the state a course is put during the first iteration of the state pattern loop */
    private final InitialState stateInitial = new InitialState();
    /** the suffix state is the state a course is put when a letter is found after digits */
    private final SuffixState stateSuffix = new SuffixState();
    /** keeps track of the current state the system is in */
    private State currentState; 

    /**
     * Initializes the currentState field to initial state
     */
    public CourseNameValidator() {
        currentState = new InitialState();
    }

    /**
     * Checks to see if a courseName is valid or invalid. 
     * A valid course name has 1-3 letters in front, 3 numbers following that, and an optional 1 letter suffix
     * updates the state of the system depending on the character at an index
     * @param courseName the name of the course to be tested
     * @return true if the courseName is valid
     * @throws InvalidTransitionException When a courseName is invalid, this execption is thrown
     */
    public boolean isValid(String courseName) throws InvalidTransitionException {

        currentState = stateInitial;
        this.digitCount = 0;
        this.letterCount = 0;
        for(int i = 0; i < courseName.length(); i++ ) {
            if (Character.isDigit(courseName.charAt(i))) {

                currentState.onDigit();
            }
            else if(Character.isLetter(courseName.charAt(i))) {
                currentState.onLetter();
            }
            else {
                currentState.onOther();
            }
        } 
        return validEndState;
    }

    /**
     * Inner class State is private
     * provides abstract methods onLetter and onState to be used in inner methods.
     * @author Ahmed
     * @author Joel McKinney
     */
    private abstract class State {

        public abstract void onLetter() throws InvalidTransitionException;

        public abstract void onDigit() throws InvalidTransitionException;

        /**
         * When a non digit or non letter character is found, an exception is thrown 
         * @throws InvalidTransitionException when the character found in the state machine is not a letter or digit
         */
        public void onOther() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name can only contain letters and digits.");
        }
    }

    // inner class
    /**
     * Inner class SuffixState is private and inherits State
     * implements the abstract methods onLetter and onDigit
     * throws an exception if a digit is found or another letter is found
     */
    private class SuffixState extends State {

        /**
         * Overrides the onLetter method from the state class
         * throws an exception if a letter is found beyond the currently exisiting suffix
         * @throws InvalidTransitionException if a letter is found beyond the suffix
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
        }

        /**
         * Overrides the onDigit method from the state class
         * throws an exception if a digit is found behind the suffix
         * @throws InvalidTransitionException if a digit is found behind the suffix
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
        }

    }

    // inner class
    /**
     * Inner class InitialState is private and inherits State
     * throws an exception if a digit is found before any letters
     * if a letter is found, state is set to letter state, and the letter count is incremented
     * @author Ahmed
     * @author Joel McKinney
     */
    private class InitialState extends State {

        /**
         * Overrides the onLetter method from the state class
         * if a letter is found, state is set to letter state, and the letter count is incremented
         */
        @Override
        public void onLetter() {
            letterCount++;
            currentState = stateLetter;
        }

        /**
         * Overrides the onDigit method from the state class
         * throws an exception if a digit is found before any letters
         * @throws InvalidTransitionException if courseName does not start with a letter
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name must start with a letter.");
        }

    }

    // inner class
    /**
     * Inner class NumberState is private and inherits State
     * If a digit is found, increment the digits counter. 
     * If a letter is found, send to suffix state
     * @author Ahmed
     * @author Joel McKinney
     */
    private class NumberState extends State {
        /** gives the value of the number of digits that can be found in a course name */
        private static final int COURSE_NUMBER_LENGTH = 3;

        /**
         *  Overrides the onLetter method from the state class
         *  if the digit count has not reached the required amount of digits, throw an exception
         *  send if suffix state otherwise
         *  @throws InvalidTransitionException if the digit count has not reached the required amount of digits
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            if (digitCount < COURSE_NUMBER_LENGTH)
            {
                throw new InvalidTransitionException("Course name must have 3 digits.");
            }
            validEndState = true;
            currentState = stateSuffix;
        }

        /**
         * Overrides the onDigit method from the state class
         * updates the digit counter
         * if the digit counter is more than the expected amount for a course name, throw an exception
         * @throws InvalidTransitionException if the digit counter is more than the expected amount for a course name
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            digitCount++;
            if (digitCount > COURSE_NUMBER_LENGTH)
            {
                throw new InvalidTransitionException("Course name can only have 3 digits.");
            }
            else if (digitCount == 3)
            {
                validEndState = true;
            }
        }

    }

    // inner class
    /**
     * Inner class LetterState is private and inherits State
     * Implements the onLetter and onDigit methods from the state class
     * updates the digit counter and sends to number state on digit
     * updates the letter counter and throws exception if there are too many letters
     * @author Ahmed
     * @author Joel McKinney
     */
    private class LetterState extends State {
        /** gives the maximum number of consecutive letters in a course name */
        private static final int MAX_PREFIX_LETTERS = 4;

        /**
         * Updates the letter counter when a letter is encountered
         * @throws InvalidTransitionException if there are too many letters in the course name
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            letterCount++;
            validEndState = false;
            if (letterCount > MAX_PREFIX_LETTERS)
            {
                throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
            }
        }

        /**
         * updates the digit counter
         * sends to number state
         */
        @Override
        public void onDigit() {
            currentState = stateNumber;
            digitCount++;
        }

    }

}
