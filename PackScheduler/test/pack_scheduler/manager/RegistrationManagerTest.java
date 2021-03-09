package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Tests the implementation of the Registration manager class
 * @author Joel McKinney
 */
public class RegistrationManagerTest {
    
    /**
     * Creates an instance of registration manager to use in the tests
     */
    private RegistrationManager manager;
    /** file where the registration properites are held private static final */
    private static final String PROP_FILE = "registrar.properties";
	/** Registrar user name */
	private String registrarUsername;
	/** Registrar password */
	private String registrarPassword;
    /**
     * Sets up the RegistrationManager and clears the data.
     * @throws Exception if error
     */
    @Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.logout();
		manager.clearData();
		
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			registrarUsername = prop.getProperty("id");
			registrarPassword = prop.getProperty("pw");
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot process properties file.");
		}
	}

    /**
	 * Tests RegistrationManager.enrollStudentInCourse().
	 */
	@Test
	public void testEnrollStudentInCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records_CourseRoll.txt");
		
		manager.logout(); //In case not handled elsewhere
		
		//test if not logged in
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.enrollStudentInCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
		}
		
		//test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.enrollStudentInCourse() - currentUser is registrar, so cannot enroll in course.", registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();
		
		manager.login("efrost", "pw");
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		
		//Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC216-001\n", 3, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC216-001\n", 1, scheduleFrostArray.length);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "CSC216", scheduleFrostArray[0][0]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "001", scheduleFrostArray[0][1]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "Software Development Fundamentals", scheduleFrostArray[0][2]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "TH 1:30PM-2:45PM", scheduleFrostArray[0][3]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "9", scheduleFrostArray[0][4]);
				
		manager.logout();
		
		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
		
		//Check Student Schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 9, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 3, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Software Development Fundamentals", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "8", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC226", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[1][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC116", scheduleHicksArray[2][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "003", scheduleHicksArray[2][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Intro to Programming - Java", scheduleHicksArray[2][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[2][4]);
		
		manager.logout();
	}
	
	/**
	 * Tests RegistrationManager.dropStudentFromCourse()
	 */
	@Test
	public void testDropStudentFromCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records_CourseRoll.txt");
		
		manager.logout(); //In case not handled elsewhere
		
		//test if not logged in
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.dropStudentFromCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
		}
		
		//test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.dropStudentFromCourse() - currentUser is registrar, so cannot enroll in course.", registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();
		
		manager.login("efrost", "pw");
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		
		assertFalse("Action: drop\nUser: efrost\nCourse: CSC217-211\nResults: False - student not enrolled in the course", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		
		//Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC226-001, then removed\n", 0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC226-001, then removed\n", 0, scheduleFrostArray.length);
		
		manager.logout();
		
		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
		
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 9, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 3, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Software Development Fundamentals", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC226", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[1][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC116", scheduleHicksArray[2][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "003", scheduleHicksArray[2][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Intro to Programming - Java", scheduleHicksArray[2][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[2][4]);
		
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		
		//Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", 6, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", 2, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "Software Development Fundamentals", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "9", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "CSC116", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "003", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "Intro to Programming - Java", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "TH 11:20AM-1:10PM", scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "9", scheduleHicksArray[1][4]);
		
		assertFalse("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: False - already dropped", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		
		//Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", 3, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", 1, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "CSC116", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "003", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "Intro to Programming - Java", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "TH 11:20AM-1:10PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "9", scheduleHicksArray[0][4]);
		
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC116-003\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		
		//Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001, CSC116-003\n", 0, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001, CSC116-003\n", 0, scheduleHicksArray.length);
		
		manager.logout();
	}
	
	/**
	 * Tests RegistrationManager.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");
		
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records_CourseRoll.txt");
		
		manager.logout(); //In case not handled elsewhere
		
		//Test if not logged in
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.resetSchedule() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
		}
		
		//test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.resetSchedule() - currentUser is registrar, so cannot enroll in course.", registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();
		
		manager.login("efrost", "pw");
		assertTrue("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		
		manager.resetSchedule();
		//Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC226-001, then schedule reset\n", 0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC226-001, then schedule reset\n", 0, scheduleFrostArray.length);
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC226", "001").getCourseRoll().getOpenSeats());
		
		manager.logout();
		
		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
		
		manager.resetSchedule();
		//Check Student schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, then schedule reset\n", 0, scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, then schedule reset\n", 0, scheduleHicksArray.length);
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC226", "001").getCourseRoll().getOpenSeats());
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC216", "001").getCourseRoll().getOpenSeats());
		assertEquals("Course should have all seats available after reset.", 10, catalog.getCourseFromCatalog("CSC116", "003").getCourseRoll().getOpenSeats());
		
		manager.logout();
	}
    /**
     * Tests to insure getCourseCatalog is properly implemented
     */
    @Test
    public void testGetCourseCatalog() {
        assertEquals(manager.getCourseCatalog().getCourseCatalog().length, 0);
        CourseCatalog cc = manager.getCourseCatalog();

        cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1445, 1600);
        assertEquals(cc.getCourseCatalog().length, 1);
        Course c = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1445, 1600);
        assertEquals(cc.getCourseFromCatalog("CSC216", "001"), c);
    }

    /**
     * Tests to insure getStudentDirection is properly implemented
     */
    @Test
    public void testGetStudentDirectory() {
        assertEquals(manager.getStudentDirectory().getStudentDirectory().length, 0);
        StudentDirectory sd = manager.getStudentDirectory();

        sd.addStudent("Stu", "Dent", "sdent", "sdent@ncsu.edu", "wordPass", "wordPass", 16);
        assertEquals(sd.getStudentDirectory().length, 1);
        Student s = new Student("Stu", "Dent", "sdent", "sdent@ncsu.edu", "wordPass", 16);
        assertEquals(sd.getStudentById("sdent").getFirstName(), s.getFirstName());
        assertEquals(sd.getStudentById("sdent").getLastName(), s.getLastName());
        assertEquals(sd.getStudentById("sdent").getId(), s.getId());
    }

    /**
     * Tests to insure login method is properly implemented
     * tests valid and invalid cases
     */
    @Test
    public void testLogin() {
        
        assertEquals(manager.getStudentDirectory().getStudentDirectory().length, 0);
        StudentDirectory sd = manager.getStudentDirectory();

        sd.addStudent("Stu", "Dent", "sdent", "sdent@ncsu.edu", "wordPass", "wordPass", 16);
        manager.login( "sdent", "wordPass");
        assertEquals(manager.getCurrentUser().getFirstName(), "Stu");
        assertEquals(manager.getCurrentUser().getLastName(), "Dent");
        assertEquals(manager.getCurrentUser().getId(), "sdent");

        manager.logout();
        try { 
            assertFalse(manager.login("fake", "account"));
        } catch (IllegalArgumentException e) {
            assertEquals("User doesn't exist.", e.getMessage());
        }

        manager.logout();
        assertFalse(manager.login("sdent", "password"));
        assertEquals(manager.getCurrentUser(), null);

        manager.logout();
        assertFalse(manager.login(registrarUsername, "compu"));
        assertEquals(manager.getCurrentUser(), null);
        
        manager.logout();
        assertTrue(manager.login(registrarUsername, registrarPassword));

        try {
            manager.logout();
            assertFalse(manager.login("sden", "wordPass"));
        } catch (IllegalArgumentException e) {
            assertEquals("User doesn't exist.", e.getMessage());
        }
    }

    /**
     * Tests to insure logout method is properly implemented
     */
    @Test
    public void testLogout() {
        assertEquals(manager.getStudentDirectory().getStudentDirectory().length, 0);
        StudentDirectory sd = manager.getStudentDirectory();

        sd.addStudent("Stu", "Dent", "sdent", "sdent@ncsu.edu", "wordPass", "wordPass", 16);
        manager.login( "sdent", "wordPass");
        assertEquals(manager.getCurrentUser().getFirstName(), "Stu");
        assertEquals(manager.getCurrentUser().getLastName(), "Dent");
        assertEquals(manager.getCurrentUser().getId(), "sdent");

        manager.logout();
        assertEquals(manager.getCurrentUser(), null);
    }

    /**
     * Tests to insure getCurrentUser is properly implemented
     */
    @Test
    public void testGetCurrentUser() { 
        assertEquals(manager.getStudentDirectory().getStudentDirectory().length, 0);

        manager.login(registrarUsername, registrarPassword);
        assertEquals(manager.getCurrentUser().getFirstName(), "Reg");
        assertEquals(manager.getCurrentUser().getLastName(), "Istrar");
        assertEquals(manager.getCurrentUser().getId(), "registrar");

        manager.logout();
        assertEquals(manager.getCurrentUser(), null);
    }

}