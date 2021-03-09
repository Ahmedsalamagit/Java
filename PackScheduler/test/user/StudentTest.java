package edu.ncsu.csc216.user;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
/**
 * This class is responsible to running tests on the Student class
 * @author Jmeah Clarke
 * @author Joel McKinney
 *
 */
public class StudentTest {

    /** Test first name */
    private static final String FIRST_NAME = "Stu";
    /** Test last name */
    private static final String LAST_NAME = "Dent";
    /** Test id */
    private static final String ID = "sdent";
    /** Test email */
    private static final String EMAIL = "sdent@ncsu.edu";
    /** Test password */
    private static final String PASSWORD = "pw";
    /** Test max credits */
    private static final int MAX_CREDITS = 15;

    /**
     * Tests the constructor with a default for max credits
     */
    @Test
    public void testConstructorWithoutCredits() {
        Student testStudent = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
        assertEquals(FIRST_NAME, testStudent.getFirstName());
        assertEquals(LAST_NAME, testStudent.getLastName());
        assertEquals(ID, testStudent.getId());
        assertEquals(EMAIL, testStudent.getEmail());
        assertEquals(PASSWORD, testStudent.getPassword());
    }
    
    /**
     * Tests the constructor without a default max credit amount
     */
    @Test
    public void testConstructorWithCredits() {
        Student testStudent = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        assertEquals(FIRST_NAME, testStudent.getFirstName());
        assertEquals(LAST_NAME, testStudent.getLastName());
        assertEquals(ID, testStudent.getId());
        assertEquals(EMAIL, testStudent.getEmail());
        assertEquals(PASSWORD, testStudent.getPassword());
        assertEquals(MAX_CREDITS, testStudent.getMaxCredits());
    }
    /**
     * Creates a Student object using the constructor without max credits as a parameter
     */
    @Test
    public void testFirstNameGettersAndSetters() { 
        User invalidStudent = null;

        try {
            invalidStudent = new Student("", LAST_NAME, ID, EMAIL, PASSWORD);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid first name", e.getMessage());
        }

        try
        {
            invalidStudent = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
        } catch (IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid first name", e.getMessage());
        }
    }
    /**
     * Tests the getter and setter methods of the Last name field
     */
    @Test
    public void testLastNameGettersAndSetters() { 
        User invalidStudent = null;

        try {
            invalidStudent = new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD);
            fail();
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid last name", e.getMessage());
        }

        try
        {
            invalidStudent = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
        } catch (IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid last name", e.getMessage());
        }

    }

    /**
     * Tests the getter and setter methods of the id field
     */
    @Test
    public void testIdGettersAndSetters() { 
        User invalidStudent = null;

        try {
            invalidStudent = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD);
            fail();
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid id", e.getMessage());
        }

    }

    /**
     * Tests the getter and setter methods of the email field
     * Tests invalid inputs for missing '@', '.', and . before @
     */
    @Test
    public void testInvalidEmail() { 
        User invalidStudent = null;
        User invalidStudent2 = null;
        User invalidStudent3 = null;
        User invalidStudent4 = null;
        
        //no @
        try {
            invalidStudent = new Student(FIRST_NAME, LAST_NAME, ID, "jjclarkencsu.edu", PASSWORD);
            fail();
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid email", e.getMessage());
        }

        //no period
        try {
            invalidStudent2 = new Student(FIRST_NAME, LAST_NAME, ID, "jjclarke@ncsuedu", PASSWORD);
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent2);
            assertEquals("Invalid email", e.getMessage());
        }

        //empty string for email
        try {
            invalidStudent3 = new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD);
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent3);
            assertEquals("Invalid email", e.getMessage());
        }

        //. before @
        try {
            invalidStudent4 = new Student(FIRST_NAME, LAST_NAME, ID, "jj.clarke@ncsuedu", PASSWORD);
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent4);
            assertEquals("Invalid email", e.getMessage());
        }

    }

    /**
     * Test invalid input in the password getters and setters
     */
    @Test
    public void testPasswordGettersAndSetters() {
        User invalidStudent = null;

        try {
            invalidStudent = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "");
            fail();
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid password", e.getMessage());
        }

        try {
            invalidStudent = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null);
            fail();
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid password", e.getMessage());
        }
    }

    /**
     * Tests invalid input in the Max Credit getters and setters
     */
    @Test
    public void testMaxCreditsGettersAndSetters() {
        User invalidStudent = null;

        try {
            invalidStudent = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);
            fail();
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid max credits", e.getMessage());
        }

        try {
            invalidStudent = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 19);
            fail();
        } catch(IllegalArgumentException e) {
            assertNull(invalidStudent);
            assertEquals("Invalid max credits", e.getMessage());
        }
    }

    /**
     * Test a valid email input to the setter method
     */
    @Test
    public void testValidEmail() { 
        Student validStudent = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
        assertEquals(EMAIL, validStudent.getEmail());
    }

    /**
     * Tests the hash code method of the student class
     */
    @Test
    public void testHashCode() {
        User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s3 = new Student(FIRST_NAME, "Unique", ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s4 = new Student(FIRST_NAME, LAST_NAME, "Example", EMAIL, PASSWORD, MAX_CREDITS);
        User s5 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 5);
        User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "Test@test.com", PASSWORD, MAX_CREDITS);
        User s7 = new Student("Name", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);

        assertEquals(s1.hashCode(), s2.hashCode());

        assertNotEquals(s1.hashCode(), s3.hashCode());
        assertNotEquals(s1.hashCode(), s4.hashCode());
        assertNotEquals(s1.hashCode(), s5.hashCode());
        assertNotEquals(s1.hashCode(), s6.hashCode());
        assertNotEquals(s1.hashCode(), s7.hashCode());
    }

    /**
     * Tests the equals method of the student class
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals() {
        User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s3 = new Student(FIRST_NAME, "Unique", ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s4 = new Student(FIRST_NAME, LAST_NAME, "Example", EMAIL, PASSWORD, MAX_CREDITS);
        User s5 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 5);
        User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "Test@test.com", PASSWORD, MAX_CREDITS);
        User s7 = new Student("Name", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        User s8 = null;
        StudentRecordIO s9 = new StudentRecordIO();
        User s10 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "Different", MAX_CREDITS);

        assertTrue(s1.equals(s2));
        assertTrue(s2.equals(s1));
        assertTrue(s1.equals(s1));

        assertFalse(s1.equals(s3));
        assertFalse(s1.equals(s4));
        assertFalse(s1.equals(s5));
        assertFalse(s1.equals(s6));
        assertFalse(s1.equals(s7));
        assertFalse(s1.equals(s8));
        assertFalse(s1.equals(s9));
        assertFalse(s1.equals(s10));
    }
    
    /**
     * Tests the toString method of the student class
     */
    @Test
    public void testToString() {
        User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        String test = s1.toString();
        String actual = FIRST_NAME + "," + LAST_NAME + "," + ID + "," + EMAIL + "," + PASSWORD + "," + MAX_CREDITS;
        assertEquals(test, actual);
    }
    
    /**
     * Tests the implementation of the compareTo method
     */
    @Test
    public void testCompareTo() {
        Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s3 = new Student(FIRST_NAME, "Cent", ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s4 = new Student("Trent", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s5 = new Student("Trent", "Denz", ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s6 = new Student("Kevin", "Denz", ID, EMAIL, PASSWORD, MAX_CREDITS);
        Student s7 = new Student(FIRST_NAME, LAST_NAME, "kdent", EMAIL, PASSWORD, MAX_CREDITS);
        Student s8 = new Student(FIRST_NAME, LAST_NAME, "zdent", EMAIL, PASSWORD, MAX_CREDITS);
        
        assertEquals(s1.compareTo(s3), 1);
        assertEquals(s1.compareTo(s4), -1);
        assertEquals(s1.compareTo(s2), 0);
        assertEquals(s4.compareTo(s5), -1);
        assertEquals(s5.compareTo(s6), 1); 
        assertEquals(s1.compareTo(s7), 1);
        assertEquals(s1.compareTo(s8), -1);
    }
}
