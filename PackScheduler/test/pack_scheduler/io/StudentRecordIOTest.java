package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;




/**
 * Tests the functionality of the StudentRecordIO class
 * @author Joel McKinney
 * @author Jmeah Clarke
 */
public class StudentRecordIOTest {

    /** creates a valid student to be held in the validStudent array */
    private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
    /** creates a valid student to be held in the validStudent array */
    private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

    /** Array of valid students for testing */
    private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
            validStudent6, validStudent7, validStudent8, validStudent9};

    /** String to hold encoded version of pw */
    private String hashPW;
    /** algorithm to decode hashPW */
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Sets up the StudentRecordIOTest for execution
     */
    @Before
    public void setUp() {
        try {
            String password = "pw";
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            hashPW = new String(digest.digest());

            for (int i = 0; i < validStudents.length; i++) {
                validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
            }
        } catch (NoSuchAlgorithmException e) {
            fail("Unable to create hash during setup");
        }
    }
    
    /**
     * Tests the validity of the ReadStudentRecords method
     */
    @Test
    public void testReadStudentRecords() {
        String filename = "test-files/student_records.txt";
        SortedList<Student> studentList = new SortedList<Student>();
        try {
            studentList = StudentRecordIO.readStudentRecords(filename);
            int numStudents = studentList.size();
            int length = validStudents.length;
            assertEquals(numStudents, length);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found");
        }
    }
    
    /**
     * Tests the invalid case of the ReadStudentRecords method
     */
    @Test
    public void testInvalidReadStudentRecords() {
        String filename = "test-files/invalid_student_records.txt";
        SortedList<Student> studentList = new SortedList<Student>();
        try {
            studentList = StudentRecordIO.readStudentRecords(filename);
            int numStudents = studentList.size();
            assertEquals(numStudents, 0);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found");
        }
    }

    /**
     * Tests the duplicate case of the ReadStudentRecords method
     */
    @Test
    public void testDupeReadStudentRecords() {
        String filename = "test-files/repeat_students.txt";
        SortedList<Student> studentList = new SortedList<Student>();
        try {
            studentList = StudentRecordIO.readStudentRecords(filename);
            int numStudents = studentList.size();
            assertEquals(numStudents, 1);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found");
        }
    }
    
    /**
     * Tests the validity of the WriteStudentRecords method
     */
    @Test
    public void testWriteStudentRecords() {
    	SortedList<Student> students = new SortedList<Student>();
        students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
        //Assumption that you are using a hash of "pw" stored in hashPW
        
        try {
            StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
            fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
        } catch (IOException e) {
            assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
            //The actual error message on Jenkins!
        }
    }

}
