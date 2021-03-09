package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Outer class to the registrar class holds global variables to be used by inner
 * classes
 * 
 * @author Joel McKinney
 * @author Ahmed Salama
 * 
 */
public class RegistrationManager {

    /** Creates an instance of the registration manager class */
    private static RegistrationManager instance;
    /** Creates a course catalog to hold all courses */
    private CourseCatalog courseCatalog;
    /** Creates a student directory to hold all the students */
    private StudentDirectory studentDirectory;
    /** Creates a user object to take advantage of polymorphism */
    private User registrar;
    /** The current user that is being evaluated by the registrar */
    private User currentUser;
    /** Hashing algorithm */
    private static final String HASH_ALGORITHM = "SHA-256";
    /** file where the registration properites are held private static final */
    private static final String PROP_FILE = "registrar.properties";

    private RegistrationManager() {
        createRegistrar();
        studentDirectory = new StudentDirectory();
        courseCatalog = new CourseCatalog();
    }
    /**
    * Returns true if the logged in student can enroll in the given course
    * @param c Course to enroll in
    * @return true if enrolled
    */
   public boolean enrollStudentInCourse(Course c) {
       if (!(currentUser instanceof Student)) {
           throw new IllegalArgumentException("Illegal Action");
       }
       try {
           Student s = (Student)currentUser;
           Schedule schedule = s.getSchedule();
           CourseRoll roll = c.getCourseRoll();
           
           if (s.canAdd(c) && roll.canEnroll(s)) {
               schedule.addCourseToSchedule(c);
               roll.enroll(s);
               return true;
           }
           
       } catch (IllegalArgumentException e) {
           return false;
       }
       return false;
   }

   /**
    * Returns true if the logged in student can drop the given course.
    * @param c Course to drop
    * @return true if dropped
    */
   public boolean dropStudentFromCourse(Course c) {
       if (!(currentUser instanceof Student)) {
           throw new IllegalArgumentException("Illegal Action");
       }
       try {
           Student s = (Student)currentUser;
           c.getCourseRoll().drop(s);
           return s.getSchedule().removeCourseFromSchedule(c);
       } catch (IllegalArgumentException e) {
           return false; 
       }
   }

   /**
    * Resets the logged in student's schedule by dropping them
    * from every course and then resetting the schedule.
    */
   public void resetSchedule() {
       if (!(currentUser instanceof Student)) {
           throw new IllegalArgumentException("Illegal Action");
       }
       try {
           Student s = (Student)currentUser;
           Schedule schedule = s.getSchedule();
           String [][] scheduleArray = schedule.getScheduledCourses();
           for (int i = 0; i < scheduleArray.length; i++) {
               Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
               c.getCourseRoll().drop(s);
           }
           schedule.resetSchedule();
       } catch (IllegalArgumentException e) {
           //do nothing 
       }
   }
    private void createRegistrar() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(PROP_FILE)) {
            prop.load(input);

            String hashPW = hashPW(prop.getProperty("pw"));

            registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
                    prop.getProperty("email"), hashPW);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot create registrar.");
        }
    }

    private String hashPW(String pw) {
        try {
            MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
            digest1.update(pw.getBytes());
            return new String(digest1.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }

    /**
     * Sets the current instance of the registrar manager
     * 
     * @return current instance of the registrar manager
     */
    public static RegistrationManager getInstance() {
        if (instance == null) {
            instance = new RegistrationManager();
        }
        return instance;
    }

    /**
     * Provides the course catalog a course catalog contains all courses a user
     * could register for
     * 
     * @return the course catalog a user could select courses from
     */
    public CourseCatalog getCourseCatalog() {
        return courseCatalog;
    }

    /**
     * Provides the student directory A student directory contains a list of
     * students on file
     * 
     * @return list of students that are on file
     */
    public StudentDirectory getStudentDirectory() {
        return studentDirectory;
    }

    /**
     * Determines if a login attempt is successful
     * 
     * @throws IllegalArgumentException if an algorithm cannot be found
     * @param id       the id of a user attempting to login
     * @param password the password the user entered during their login attempt
     * @return true if login attempt is successful, false otherwise
     */
    public boolean login(String id, String password) {
        if (currentUser != null) {
            return false;
        }
        try {
            if (registrar.getId().equals(id)) {
                MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
                digest.update(password.getBytes());
                String localHashPW = new String(digest.digest());
                if (registrar.getPassword().equals(localHashPW)) {
                    currentUser = registrar;
                    return true;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        }

        Student s = studentDirectory.getStudentById(id);
        if (s != null)
            try {
                MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
                digest.update(password.getBytes());
                String localHashPW = new String(digest.digest());
                if (s != null && s.getPassword().equals(localHashPW)) {
                    currentUser = s;
                    return true;
                } 
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException();
            }

        if(s == null && !registrar.getId().equals(id))
        {
            throw new IllegalArgumentException("User doesn't exist.");
        }
        return false;
    }

    /**
     * logs the current users out of the system sets the currentUser to null
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * gives the value of the current user the current user is whoever is logged in
     * 
     * @return the user who is currently logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Clears the course catalog and student directory makes the course catalog and
     * student directory empty
     */
    public void clearData() {
        courseCatalog.newCourseCatalog();
        studentDirectory.newStudentDirectory();
    }

    /**
     * inherits from the User abstract superclass creates a registrar for the user
     * 
     * @author Joel McKinney
     */
    private static class Registrar extends User {
        /**
         * Create a registrar user.
         * 
         * @param firstName the first name of a user
         * @param lastName  the last name of a user
         * @param id        the unity id of a user
         * @param email     the email of a user
         * @param hashPW    the encoded password of a user
         */
        public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
            super(firstName, lastName, id, email, hashPW);
        }
    }
}