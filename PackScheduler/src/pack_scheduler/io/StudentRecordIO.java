package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * StudentRecordIO class reads and creates files with student info 
 * Allows user to create and import student schedules
 * Creates SortedList of students to to export and import data about students
 * @author Joel McKinney
 * @author Jmeah Clarke
 */
public class StudentRecordIO {

    /**
     * Reads a file containing student info and creates an SortedList with new Student objects
     * Used to import student records
     * @param fileName the name of the input file
     * @return students, an SortedList with student info gathered from file
     * @throws FileNotFoundException throws exception if input file not found
     */
    public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new FileInputStream(fileName));
        SortedList<Student> students = new SortedList<Student>();
        while (fileReader.hasNextLine())
        {
            try
            {
                Student newStudent = processStudent(fileReader.nextLine());

                boolean dupe = false;

                for (int i = 0; i < students.size(); i++)
                {
                    Student current = students.get(i); 
                    if (newStudent.getFirstName().equals(current.getFirstName()) && 
                            newStudent.getLastName().equals(current.getLastName()))
                    {
                        dupe = true;
                        break;
                    }
                }

                if(!dupe)
                {
                    students.add(newStudent);
                }
            }
            catch(IllegalArgumentException e)
            {
                //line is invalid, could not create course. Move on to next line.
            }
        }
        fileReader.close();

        return students;
    }

    /**
     * Creates a new file with student info using an SortedList of student objects
     * Used to store new student records
     * @param fileName name of the output file
     * @param studentDirectory SortedList containing student objects
     * @throws IOException throws exception when unable to create new file
     */
    public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
        PrintStream fileWriter = new PrintStream(new File(fileName));

        for (int i = 0; i < studentDirectory.size(); i++)
        {            
            fileWriter.println(studentDirectory.get(i).toString());
        }

        fileWriter.close();
    }

    /**
     * Processes student info, creates a new student object, and returns it to readStudentRecords
     * @param line the line in the file readStudentRecords is currently reading
     * @return a new Student object to add the the SortedList in readStudentRecords
     * @throws EndOfFileException if the file does not follow the correct format. 
     * Exception handled by caller
     */
    private static Student processStudent(String line)
    {
        Scanner lineReader = new Scanner(line);
        lineReader.useDelimiter(",");

        String firstName = "";
        String lastName = "";
        String id = "";
        String email = "";
        String password = "";
        int maxCredits = 0;

        while(lineReader.hasNext())
        {
            try {
                firstName = lineReader.next();
                lastName = lineReader.next();
                id = lineReader.next();
                email = lineReader.next();
                password = lineReader.next();
                maxCredits = lineReader.nextInt();
            }
            catch(NoSuchElementException e)
            {
                lineReader.close();
                throw new IllegalArgumentException();
            }
        }

        Student newStudent = new Student(firstName, lastName, id, email, password, maxCredits);

        lineReader.close();
        return newStudent;
    }
}
