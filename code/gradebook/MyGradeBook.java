package gradebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

/**
 * GradeBook represents the assignments, students, and associated scores for a 
 * course.
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
public class MyGradeBook {
    /** List of the students in the gradebook, SORTED ALPHABETICALLY */
    ArrayList<Student> students;
    /** List of the assignments in the gradebook */
    ArrayList<Assignment> assignments;
    
    /**
     * Constructor for GradeBook
     * @param students List of all the students in the gradebook
     * @param assignments List of all the assignment in the gradebook
     */
    MyGradeBook(ArrayList<Student> students,
            ArrayList<Assignment> assignments) {
        this.students = students;
        this.assignments = assignments;
    }
    
    /**
     * Factory method to construct an empty MyGradebook
     * 
     * @return an empty MyGradeBook
     */
    public static MyGradeBook initialize() {
        // Start with empty GradeBook
        return new MyGradeBook(new ArrayList<Student>(),
            new ArrayList<Assignment>());
    }

    /**
     * Helper method to read a file into a string
     * 
     * @param filename
     *            the filename of the file to read
     * @return a string representation of the file's content
     */
    private static String stringFromFile(String filename) {
        File f = new File(filename);
        // Check if the file exists
        if (!f.exists() || !f.canRead()) {
            // TODO: Find out if we can do better than returning
            //  a null String
            //throw new FileNotFoundException(filename + " not found");
            return "";
        }
        // Check if the file is readable
        /*if ( !f.canRead() ) {
            // TODO: Find out if we can do better than returning
            //  a null String
            //throw new IOException(filename + " cannot be read");
            return null;
        }*/

        String ret = "";
        try {
            ret = (new Scanner(f)).useDelimiter("\\Z").next();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from filename
     * 
     * @param filename
     *            the filename for the file that contains the initial grade
     *            book, which is formatted like initial.txt
     * @return a MyGradebook that contains the grade book from filename
     */
    public static MyGradeBook initializeWithFile(String filename) {
        // Get the file string
        String fileString = "";
        //try {
        fileString = MyGradeBook.stringFromFile(filename);
        /*}
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }*/
        // I don't think it makes sense to catch the errors here. You still want
        // them to occur. I think the UI should be responsible for the catch

        // Return a new gradebook based on the string
        return MyGradeBook.initializeWithString(fileString);
    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from startingString
     * 
     * @param startingString
     *            String that contains the initial grade book, which is
     *            formatted like initial.txt
     * @return a MyGradebook that contains the grade book from startingString
     */
    public static MyGradeBook initializeWithString(String startingString) {
        // MyGradeBook to return
        MyGradeBook ret = MyGradeBook.initialize();

        // Get an ArrayList of lines in the file
        ArrayList<String> lines =
            new ArrayList<String>(Arrays.asList(startingString.split("\n")));
        // Delete any "\r" characters that may be presents
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).replace("\r",""));
        }

        // The first line should just contain the string "GRADEBOOK"
        if ( !lines.get(0).equals("GRADEBOOK") ) {
            throw new UnsupportedOperationException(
                "Contents are not valid GradeBook format");
        }
        
        // TODO : What if header is correct but contents are not properly
        // formatted?

        // The second line contains the assignment names
        ArrayList<String> assignmentNames =
            new ArrayList<String>(Arrays.asList(lines.get(1).split("\t")));
        // Remove any empty strings in the array
        //  (due to the particular file formatting)
        assignmentNames.removeAll(Arrays.asList(""));

        // The third line contains the total points for each assignment
        ArrayList<String> assignmentTotalPoints =
            new ArrayList<String>(Arrays.asList(lines.get(2).split("\t")));
        // Remove any empty strings in the array
        //  (due to the particular file formatting)
        assignmentTotalPoints.removeAll(Arrays.asList(""));

        // The fourth line contains the percent grade for each assignment
        ArrayList<String> assignmentPercentGrade =
            new ArrayList<String>(Arrays.asList(lines.get(3).split("\t")));
        // Remove any empty strings in the array
        //  (due to the particular file formatting)
        assignmentPercentGrade.removeAll(Arrays.asList(""));

        // Add the assignments to the gradebook
        int numAssignments = assignmentNames.size();
        for (int i = 0; i < numAssignments; i++) {
            ret.addAssignment(
                assignmentNames.get(i),
                new Double(assignmentTotalPoints.get(i)),
                new Double(assignmentPercentGrade.get(i))
            );
        }

        // Add the students and their grades
        for (int lineNumber = 4; lineNumber < lines.size(); lineNumber++) {
            // Parse the student information and add the student
            //  to the gradebook
            ArrayList<String> studentInfo =
                new ArrayList<String>(
                    Arrays.asList(lines.get(lineNumber).split("\t"))
                );
            String studentUsername = studentInfo.get(0);
            String studentFirstName = studentInfo.get(1);
            String studentLastName = studentInfo.get(2);
            String studentAdvisor = studentInfo.get(3);
            String studentGradYear = studentInfo.get(4);
            ret.addStudent(
                studentUsername,
                studentFirstName,
                studentLastName,
                studentAdvisor,
                Integer.parseInt(studentGradYear)
            );

            // Add the student's assignment grades to the gradebook
            // ID, Assignment Name, newGrade
            for (int a = 0; a < numAssignments; a++) {
                ret.changeGrade(
                    assignmentNames.get(a),
                    studentUsername,
                    Double.parseDouble(studentInfo.get(a + 5))
                );
            }
        }
        
        return ret;
    }

    /**
     * Add to the state of this grade book---new assignments, new students, new
     * grades---by processing filename
     * 
     * @param filename
     *            the filename for a file that contains information that will be
     *            added to the grade book. The file could contain several
     *            different types of information---new assignments, new
     *            students, new grades. The file will be formatted like
     *            addAssignments.txt, addStudents.txt, gradesForAssignment1.txt,
     *            and gradesForStudent.txt.
     */
    public void processFile(String filename) {
        // Get the file string
        String fileString = "";
        //try {
        fileString = MyGradeBook.stringFromFile(filename);
        /*}
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }*/
        // TODO : I don't think exceptions should be caught here? (JE)

        // Update the gradebook based on the string
        this.processString(fileString);
    }

    /**
     * Add to the state of this grade book---new assignments, new students, new
     * grades---by processing additionalString
     * 
     * @param additionalString
     *            String that contains information that will be added to the
     *            grade book. The String could contain several different types
     *            of information---new assignments, new students, new grades.
     *            The String will be formatted like addAssignments.txt,
     *            addStudents.txt, gradesForAssignment1.txt, and
     *            gradesForStudent.txt.
     */
    public void processString(String additionalString) {
        // Get an ArrayList of lines in the file
        ArrayList<String> lines =
            new ArrayList<String>(Arrays.asList(additionalString.split("\n")));
        // Delete any "\r" characters that may be presents
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).replace("\r",""));
        }

        // Find out which type of file it is
        //  and call the appropriate helper method
        String fileHeader = lines.get(0);
        // A file having a format like addAssignments.txt
        if ( fileHeader.equals("ASSIGNMENT") ) {
            this.processNewAssignmentsFileLines(lines);
        }
        // A file having a format like addStudents.txt
        else if ( fileHeader.equals("STUDENT") ) {
            this.processNewStudentsFileLines(lines);
        }
        // A file having a format like gradesForAssignment1.txt
        else if ( fileHeader.equals("GRADES_FOR_ASSIGNMENT") ) {
            this.processNewGradesForAssignmentFileLines(lines);
        }
        // A file having a format like gradesForStudent.txt
        else if ( fileHeader.equals("GRADES_FOR_STUDENT") ) {
            this.processNewGradesForStudentFileLines(lines);
        }
        // The header did not match any known file format
        else {
            throw new UnsupportedOperationException(
                "Invalid file header  " + fileHeader);
        }
    }

    /**
     * Helper method for processing lines from a file having a format
     * like addAssignments.txt
     * Modifies the list of assignments
     * @param fileLines the lines of the file
     */
    private void processNewAssignmentsFileLines(ArrayList<String> fileLines) {
        // Read in 4-line blocks, adding an assignment each time
        for (int i = 0; i < fileLines.size(); i += 4) {
            String assignmentName = fileLines.get(i + 1);
            String assignmentTotalPoints = fileLines.get(i + 2);
            String assignmentPercentGrade = fileLines.get(i + 3);
            this.addAssignment(
                assignmentName,
                new Double(assignmentTotalPoints),
                new Double(assignmentPercentGrade)
            );
        }
    }

    /**
     * Helper method for processing lines from a file having a format
     * like addStudents.txt
     * Modifies the list of students
     * @param fileLines the lines of the file
     */
    private void processNewStudentsFileLines(ArrayList<String> fileLines) {
        // Read in 6-line blocks, adding a student each time
        for (int i = 0; i < fileLines.size(); i += 6) {
            String studentUsername = fileLines.get(i + 1);
            String studentFirstName = fileLines.get(i + 2);
            String studentLastName = fileLines.get(i + 3);
            String studentAdvisor = fileLines.get(i + 4);
            String studentGradYear = fileLines.get(i + 5);
            this.addStudent(
                studentUsername,
                studentFirstName,
                studentLastName,
                studentAdvisor,
                Integer.parseInt(studentGradYear)
            );
        }
    }

    /**
     * Helper method for processing lines from a file having a format
     * like gradesForAssignment1.txt
     * Modifies the the grades fields of the gradebook's students
     * @param fileLines the lines of the file
     */
    private void processNewGradesForAssignmentFileLines(
            ArrayList<String> fileLines) {
        // Get the assignment name
        String assignmentName = fileLines.get(1);
        // Read in 2-line blocks, modifying a student's grade each time
        for (int i = 2; i < fileLines.size(); i += 2) {
            String studentUsername = fileLines.get(i + 0);
            String assignmentGrade = fileLines.get(i + 1);
            this.changeGrade(
                assignmentName,
                studentUsername,
                Double.parseDouble(assignmentGrade)
            );
        }
    }

    /**
     * Helper method for processing lines from a file having a format
     * like gradesForStudent.txt
     * Modifies the the grades fields of the gradebook's students
     * @param fileLines the lines of the file
     */
    private void processNewGradesForStudentFileLines(
            ArrayList<String> fileLines) {
        // Get the student username
        String studentUsername = fileLines.get(1);
        // Read in 2-line blocks, modifying the student's grades each time
        for (int i = 2; i < fileLines.size(); i += 2) {
            String assignmentName = fileLines.get(i + 0);
            String assignmentGrade = fileLines.get(i + 1);
            this.changeGrade(
                assignmentName,
                studentUsername,
                Double.parseDouble(assignmentGrade)
            );
        }
    }
    
    /**
     * Add a Student to the Gradebook according to the given information
     * Modifies the GradeBook's student field to add the Student, and sorts the
     * list of Students
     * @param username Username of the Student
     * @param firstName First name of the Student
     * @param lastName Last name of the Student
     * @param advisor Advisor/guidance counselor of the Student
     * @param gradYear Student's expected year of graduation
     */
    public void addStudent(String username, String firstName, String lastName,
            String advisor, int gradYear) {
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                throw new IllegalArgumentException("A student with the given "
                    + "username already exists");
            }
        }
        Student newStudent = Student.newStudent(username, firstName,
            lastName, advisor, gradYear);
        students.add(newStudent);
        Collections.sort(students, new ByUsername());
        HashMap<String, Double> newGrades = new HashMap<String, Double>();
        for (Assignment a : this.assignments) {
            newGrades.put(a.name, new Double(0));
        }
        newStudent.grades = newGrades;
    }
    
    /**
     * Add an assignment to the Gradebook according to the inputs
     * Modifies the Gradebook's assignments field and adds to the student's
     * list of grades (initializing with a score of 0)
     * @param name Name of the assignment
     * @param totalPoints Maximum points for the assignment
     * @param percentGrade Percentage of the semester grade that the assignment
     *     counts for
     */
    public void addAssignment(String name, Double totalPoints,
            Double percentGrade) {
        for (Assignment a : this.assignments) {
            if (a.name.equals(name)) {
                throw new IllegalArgumentException("An assignment with the " +
                    "given name already exists");
            }
        }
        Assignment newAssignment = Assignment.newAssignment(name, totalPoints,
            percentGrade);
        assignments.add(newAssignment);
        for (Student student : students) {
            student.grades.put(newAssignment.name, new Double(0));
            // TODO : Find some way to put this in the correct order?
            // Do hashMaps have explicit order?
        }
    }
    
    /**
     * Remove an assignment with the given name from the Gradebook
     * Modifies the Gradebook's assignments field and remove from the 
     * student's list of grades 
     * @param assignmentName the assignment to be removed
     */
    public void removeAssignment(String assignmentName) {
        assignmentFound(assignmentName);
        int assignmentIndex = -1;
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).name.equals(assignmentName)) {
                assignmentIndex = i;
                break;
            }
        }
        assignments.remove(assignmentIndex);
        for (Student student : students) {
            student.grades.remove(assignmentName);
        }
    }
        
    /**
     * Remove a student from the Gradebook
     * Modifies the Gradebook's students field
     * @param username Username of the student to be removed
     */
    public void removeStudent(String username) {
        studentFound(username);
        int studentIndex = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).username.equals(username)) {
                studentIndex = i;
                break;
            }
        }
        students.remove(students.get(studentIndex));
    }
    
    /**
     * Changes the assignment (named assignmentName) grade for student (whose
     * username is equal to username) to newGrade
     * 
     * @param assignmentName
     *            name of the assignment
     * @param username
     *            username for the student
     * @param newGrade
     *            the new grade for the given assignment and student
     * @return whether there was a grade to change. Returns true if the given
     *         assignment/student combination exists, returns false otherwise
     */
    public boolean changeGrade(String assignmentName,
            String username, double newGrade) {
        // Find the Student by username
        int studentIndex = -1;
        for (int i = 0; i < this.students.size(); i++) {
            if (students.get(i).username.equals(username)) {
                studentIndex = i;
                break;
            }
        }
        // Find the assignment and set the grade
        int assignmentIndex = -1;
        for (int j = 0; j < this.assignments.size(); j++) {
            if (assignments.get(j).name.equals(assignmentName)) {
                assignmentIndex = j;
                break;
            }
        }
        // Return the appropriate boolean if modifications were successful
        if (studentIndex != -1 && assignmentIndex != -1) {
            Student studentFound = students.get(studentIndex);
            studentFound.grades.put(assignmentName, newGrade);
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Check the list of assignments to see if the given assignment exists
     * @param assignmentName the name of the assignment
     */
    void assignmentFound(String assignmentName) {
        boolean assignmentFound = false;
        for (Assignment a : this.assignments) {
            if (a.name.equals(assignmentName)) {
                assignmentFound = true;
                break;
            }
        }
        if (!assignmentFound) {
            throw new NoSuchElementException("Assignment not found");
        }
    }
    
    /**
     * Check the list of students to see if the given student exists
     * @param studentUsername the username of the student
     */
    void studentFound(String studentUsername) {
        boolean studentFound = false;
        for (Student s : this.students) {
            if (s.username.equals(studentUsername)) {
                studentFound = true;
                break;
            }
        }
        if (!studentFound) {
            throw new NoSuchElementException("Student not found");
        }
    }

    /**
     * Calculates the average across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the average across all students for assignmentName
     */
    public double average(String assignmentName) {
        this.assignmentFound(assignmentName);
        // For each student sum the grade they received for the given assignment
        double pointSum = 0;
        double assignmentCount = 0;
        for (Student s : this.students) {
            pointSum = pointSum + s.assignmentGrade(assignmentName);
            assignmentCount = assignmentCount + 1;
        }
        // Get average grade
        return pointSum / assignmentCount;
    }

    /**
     * Calculates the median across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the median across all students for assignmentName
     */
    public double median(String assignmentName) {
        this.assignmentFound(assignmentName);
        // Get ArrayList of grades from assignmentGrades
        ArrayList<Double> grades = new ArrayList<Double>();
        for (Student s : this.students) {
            grades.add(s.assignmentGrade(assignmentName));
        }
        // Move sorting to adding student
        Collections.sort(grades);
        // Find the median of ArrayList
        int gradesCount = grades.size();
        if (gradesCount % 2 == 0) {
            return (grades.get((gradesCount - 1) / 2) +
                grades.get(gradesCount / 2)) / 2;
        }
        else {
            return grades.get(gradesCount / 2);
        }
    }

    /**
     * Calculates the min across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the min across all students for assignmentName
     */
    public double min(String assignmentName) {
        this.assignmentFound(assignmentName);
        // Get ArrayList of grades from assignmentGrades
        ArrayList<Double> grades = new ArrayList<Double>();
        for (Student s : this.students) {
            grades.add(s.assignmentGrade(assignmentName));
        }
        // Find minimum of ArrayList
        if (grades.size() == 0) {
            return 0;
        }
        else {
            double min = grades.get(0);
            for (Double d : grades) {
                if (d < min) {
                    min = d;
                }
            }
            return min;
        }
    }

    /**
     * Calculates the max across all students for a given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @return the max across all students for assignmentName
     */
    public double max(String assignmentName) {
        this.assignmentFound(assignmentName);
        // Get ArrayList of grades from assignmentGrades
        ArrayList<Double> grades = new ArrayList<Double>();
        for (Student s : this.students) {
            grades.add(s.assignmentGrade(assignmentName));
        }
        // Find maximum of ArrayList
        if (grades.size() == 0) {
            return 0;
        }
        else {
            double max = grades.get(0);
            for (Double d : grades) {
                if (d > max) {
                    max = d;
                }
            }
            return max;
        }
    }

    /**
     * Calculates the current grade for the given student
     * 
     * @param username
     *            username for the student
     * @return the current grade for student with username. The current grade is
     *         calculated based on the current assignment grades, assignment
     *         total points, assignment percent of semester. The current grade
     *         for a student is the sum of the relative assignment grades
     *         divided by the current percent of semester time 100. Since all
     *         grades may not currently be entered, we have to divide by the
     *         current percent. The relative assignment grade is the student's
     *         assignment grade divide by total point value for the assignment
     *         times the percent of semester.
     */
    public double currentGrade(String username) {
        this.studentFound(username);
        // Find the Student in the ArrayList
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                return s.currentGrade(assignments);
            }
        }
        throw new NoSuchElementException("Student now found");
    }

    /**
     * Calculates the current grade for all students
     * 
     * @return HashMap of the current grades for all students. The key of the
     *         HashMap is the username of the student. The value is the current
     *         grade for the associated student. The current grade is calculated
     *         based on the current assignment grades, assignment total points,
     *         assignment percent of semester. The current grade for a student
     *         is the sum of the relative assignment grades divided by the
     *         current percent of semester time 100. Since all grades may not
     *         currently be entered, we have to divide by the current percent.
     *         The relative assignment grade is the student's assignment grade
     *         divide by total point value for the assignment times the percent
     *         of semester.
     */
    public HashMap<String, Double> currentGrades() {
        // Use currentGrade for each Student, add to hashMap on each loop
        HashMap<String, Double> result = new HashMap<String, Double>();
        for (Student s : this.students) {
            result.put(s.username, this.currentGrade(s.username));
        }
        return result;
    }

    /**
     * Provides the grade earned by the given student for the given assignment
     * 
     * @param assignmentName
     *            name of the assignment
     * @param username
     *            username for the student
     * @return the grade earned by username for assignmentName
     */
    public double assignmentGrade(String assignmentName, String username) {
        this.studentFound(username);
        this.assignmentFound(assignmentName);
        // Find the Student in ArrayList
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                // use student.assignmentGrade(assignmentName) to get grade
                return s.assignmentGrade(assignmentName);
            }
        }
        throw new NoSuchElementException("Assignment not found");
    }
    
    /**
     * Get the grades of all students for an assignment
     * @param assignmentName Name of the assignment to get grades for
     * @return HashMap of student usernames and grades for the assignment
     */
    HashMap<String, Double> assignmentGrades(String assignmentName) {
        this.assignmentFound(assignmentName);
        // For each Student, find the Assignment and get the grade
        HashMap<String, Double> grades = new HashMap<String, Double>();
        for (Student s : this.students) {
            grades.put(s.username, s.assignmentGrade(assignmentName));
        }
        return grades;
        // (Create HashMap with username and grade)
        // (error if no such assignment)
    }

    /**
     * Provide a String that contains the current grades of all students in the
     * course
     * 
     * @return a String that contains the current grades of all students in the
     *         course. The String should be formatted like
     *         currentGrades.txt---CURRENT_GRADES heading and each row: username
     *         followed by tab and current grade. The usernames will be listed
     *         alphabetically.
     */
    public String outputCurrentGrades() {
        // Add current grades heading
        String output = "CURRENT_GRADES";
        // Get grades with currentGrades()
        for (Student s : students) {
            output = output + "\n" + s.username + "\t" + 
                    s.currentGrade(assignments);
        }
        return output;
    }

    /**
     * Provide a String that contains the current grades of the given student
     * 
     * @param username
     *            username for student
     * @return a String that contains the current grades of username. The String
     *         should be formatted like studentGrades.txt---STUDENT_GRADES
     *         heading, student info, dividers, each assignment (assignment name
     *         followed by tab and assignment grade), and current grade.
     */
    public String outputStudentGrades(String username) {
        studentFound(username);
        Student targetStudent = null;
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                targetStudent = s;
                break;
            }
        }
        String output = "STUDENT_GRADES\n" + targetStudent.username + "\n" +
            targetStudent.firstName + "\n" + targetStudent.lastName + "\n" +
            targetStudent.advisor + "\n" + targetStudent.gradYear + "\n----\n";
        // Add info about assignments (with correct number of preceeding tabs)
        HashMap<String, Double> targetGrades = targetStudent.grades;
        ArrayList<String> assignmentNames = new ArrayList<String>();
        for (Assignment a : assignments) {
            assignmentNames.add(a.name);
        }
        for (String name : assignmentNames) {
            output = output + name + "\t" + targetGrades.get(name) + "\n";
        }
        // Find student and add student.outputGrades() to String
        output = output + "----\nCURRENT GRADE\t" + this.currentGrade(username);
        return output;
    }

    /**
     * Provide a String that contains the assignment grades of all students in
     * the course for the given assignment
     * 
     * @param assignName
     *            name of the assignment
     * @return a String that contains the assignment grades of all students in
     *         the course for assignName. The String should be formatted like
     *         assignmentGrade.txt---ASSIGNMENT_GRADES heading, assignment info,
     *         dividers, each student (username followed by tab and assignment
     *         grade), and assignment stats. The usernames will be listed
     *         alphabetically.
     */
    public String outputAssignmentGrades(String assignName) {
        assignmentFound(assignName);
        Assignment targetAssignment = null;
        for (Assignment a : this.assignments) {
            if (a.name.equals(assignName)) {
                targetAssignment = a;
                break;
            }
        }
        // Add header to string
        String output = "ASSIGNMENT_GRADES\n" + assignName + "\n" +
            targetAssignment.totalPoints + "\n" +
            targetAssignment.percentGrade + "\n----\n";
        // Get grades with assignmentGrades
        HashMap<String, Double> grades = this.assignmentGrades(assignName);
        // Loop through HashMap to list username & grades
        for (Student s : this.students) {
            output = output + s.username + "\t" +
                grades.get(s.username) + "\n";
        }
        // Add divider
        output = output + "----\nSTATS\n";
        // Use other methods to calculate stats and add to end
        output = output + "Average\t" + this.average(assignName) +
            "\nMedian\t" + this.median(assignName) + 
            "\nMax\t" + this.max(assignName) + 
            "\nMin\t" + this.min(assignName);
        return output;
    }

    /**
     * Provide a String that contains the current grade book. This String could
     * be used to initialize a new grade book.
     * 
     * @return a String that contains the current grade book. This String could
     *         be used to initialize a new grade book. The String should be
     *         formatted like gradebook.txt. The usernames will be listed
     *         alphabetically.
     */
    public String outputGradebook() {
        // Add header
        String output = "GRADEBOOK\n\t\t\t\t";
        // Add info about assignments (with correct number of preceding tabs)
        for (Assignment a : this.assignments) {
            output = output + "\t" + a.name;
        }
        output = output + "\n\t\t\t\t";
        for (Assignment a : this.assignments) {
            output = output + "\t" + a.totalPoints;
        }
        output = output + "\n\t\t\t\t";
        for (Assignment a : this.assignments) {
            output = output + "\t" + a.percentGrade;
        }
        // Loop through students and use student.outputGrades to add lines
        for (Student s : this.students) {
            output = output + "\n" + s.outputGrades(this.assignments);
        }
        return output;
    }
    
    /**
     * Output a list of human-readable forms of the information about each
     * Student.
     * @return List of descriptions of all students
     */
    public ArrayList<String> listStudents() {
        ArrayList<String> studentList = new ArrayList<String>(students.size());
        for (Student student : students) {
            studentList.add(student.toString());
        }
        return studentList;
    }
    
    /**
     * Output a list of human-readable forms of the information about each
     * Assignment
     * @return List of Strings of all Assignments
     */
    public ArrayList<String> listAssignments() {
        ArrayList<String> assignmentList =
            new ArrayList<String>(assignments.size());
        for (Assignment assignment : assignments) {
            assignmentList.add(assignment.toString());
        }
        return assignmentList;
    }
    
    /**
     * Override the equals method for MyGradeBook
     * Must have same students and assignments
     * @param o Object to compare to the gradebook
     * @return boolean of whether they are the same
     */
    //@SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof MyGradeBook) {
            MyGradeBook thatGradeBook = (MyGradeBook)o;
            return assignments.equals(thatGradeBook.assignments) &&
                students.equals(thatGradeBook.students);
        }
        else {
            return false;
        }
    }
    
    /**
     * Overwrite the hashCode method for MyGradeBook
     * @return int hashCode of the MyGradeBook
     */
    public int hashCode() {
        return assignments.hashCode() ^ students.hashCode();
    }
}
