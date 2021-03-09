/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Implements the conflict interface to identify schedule conflicts
 * @author Joel McKinney
 */
public interface Conflict {
    /**
     * Checks to see if an activity is conflicting with another
     * @param possibleConflictingActivity an activity object that is compared to another 
     * Test to see if activity objects conflict
     * @throws ConflictException an exception that is thrown when activity objects conflict
     */
    void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
