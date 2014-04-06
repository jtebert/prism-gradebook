package gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.Iterator;

/**
 * GradeBook represents the assignments, students, and associated scores for a 
 * coure.
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
        // Calls processFile (which adds to the gradebook)
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
        // Actually process the lines of the text file
        // Sort the Students alphabetically
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
        // Create the file with scanner
        // Check if the file exists
        // Read in the contents of the file (making sure that it follows a valid
        // format) and creating objects accordingly
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
        // Does this just process the data that's acquired from the file?
        // i.e., Does processFile get the String from the file and pass it to
        // this?
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
        Student newStudent = Student.newStudent(username, firstName,
            lastName, advisor, gradYear);
        students.add(newStudent);
        Collections.sort(students);
        // Needs Comparator
        // TODO : Write Comparator to sort Student alphabetically by username
    }
    
    /**
     * Add an assginment to the Gradebook according to the inputs
     * Modifies the Gradebook's assignments field and adds to the student's
     * list of grades (initializing with a score of 0)
     * @param name Name of the assignment
     * @param totalPoints Maximum points for the assignment
     * @param percentGrade Percentage of the semester grade that the assignment
     *     counts for
     */
    public void addAssignment(String name, Double totalPoints,
            Double percentGrade) {
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
            if (this.students.get(i).username == username) {
                studentIndex = i;
                break;
            }
        }
        // (error if no such student)
        // Find the assignment and set the grade
        int assignmentIndex = -1;
        for (int j = 0; j < this.assignments.size(); j++) {
            if (this.assignments.get(j).name == assignmentName) {
                assignmentIndex = j;
                break;
            }
        }
        // (error if no such assignment)
        if (studentIndex != -1 && assignmentIndex != -1) {
            Student studentFound = this.students.get(studentIndex);
            studentFound.grades.put(assignmentName, newGrade);
            return true;
        }
        else {
            return false;
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
        // For each student, sum the grade they received for the given assignment
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
            return (grades.get((gradesCount - 1) / 2) + grades.get(gradesCount / 2)) / 2;
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
        // Find the Student in the ArrayList
        Student targetStudent = null;
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                targetStudent = s;
                break;
            }
        }
        //     (error is no such student)
        if (targetStudent == null) {
            throw new NoSuchElementException();
        }
        // use student.currentGrade() to get grade
        Double grade = targetStudent.currentGrade();
        // multiply each grade by percent score
        // Add all these
        // Divide by total number of points for semester
        return grade;
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
        // Find the Student in ArrayList
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                // use student.assignmentGrade(assignmentName) to get grade
                return s.assignmentGrade(assignmentName);
            }
        }
        // (error if no such student)
        throw new NoSuchElementException();
    }
    
    /**
     * Get the grades of all students for an assignment
     */
    HashMap<String, Double> assignmentGrades(String assignmentName) {
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
        // Add current grades heading and \n
        String output = "CURRENT_GRADES\n";
        // Get grades with currentGrades()
        HashMap<String, Double> grades = this.currentGrades();
        // Add line for each element in hashMap
        ArrayList<String> names = new ArrayList<String>();
        for (String s : grades.keySet()) {
            names.add(s);
        }
        Iterator<String> nameIter = names.iterator();
        while (nameIter.hasNext()) {
            String student = nameIter.next();
            output = output + student + "\t" + grades.get(student);
            if (nameIter.hasNext()) {
                output = output + "\n";
            }
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
        // Add header
        Student targetStudent = null;
        for (Student s : this.students) {
            if (s.username.equals(username)) {
                targetStudent = s;
                break;
            }
        }
        if (targetStudent == null) {
            throw new NoSuchElementException();
        }
        String output = "STUDENT_GRADES\n" + targetStudent.username + "\n" +
            targetStudent.firstName + "\n" + targetStudent.lastName + "\n" +
            targetStudent.advisor + "\n" + targetStudent.gradYear + "\n----\n";
        // Add info about assignments (with correct number of preceeding tabs)
        HashMap<String, Double> targetGrades = targetStudent.grades;
        ArrayList<String> assignmentNames = new ArrayList<String>();
        for (String s : targetGrades.keySet()) {
            assignmentNames.add(s);
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
        Assignment targetAssignment = null;
        for (Assignment a : this.assignments) {
            if (a.name == assignName) {
                targetAssignment = a;
                break;
            }
        }
        if (targetAssignment == null) {
            throw new NoSuchElementException();
        }
        // Add header to string
        String output = "ASSIGNMENT_GRADES\n" + assignName + "\n" +
            targetAssignment.totalPoints + "\n" + targetAssignment.percentGrade +
            "\n----\n";
        // Get grades with assignmentGrades
        HashMap<String, Double> grades = this.assignmentGrades(assignName);
        // Loop through HashMap to list username & grades
        ArrayList<String> students = new ArrayList<String>();
        for (String s : grades.keySet()) {
            students.add(s);
        }
        // Move sorting to addStudent
        Collections.sort(students);
        for (String s : students) {
            output = output + s + "\t" + grades.get(s) + "\n";
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
        // Add info about assignments (with correct number of preceeding tabs)
        for (Assignment a : this.assignments) {
            output = output + "\t" + a.name;
        }
        output = "\n\t\t\t\t";
        for (Assignment a : this.assignments) {
            output = output + "\t" + a.totalPoints;
        }
        output = "\n\t\t\t\t";
        for (Assignment a : this.assignments) {
            output = output + "\t" + a.percentGrade;
        }
        // Loop through students and use student.outputGrades to add lines
        HashMap<String, Student> studentUsernames = new HashMap<String, Student>();
        for (Student s : this.students) {
            studentUsernames.put(s.username, s);
        }
        ArrayList<String> sortedStudents = new ArrayList<String>();
        for (Student s : this.students) {
            sortedStudents.add(s.username);
        }
        // Move sorting to addStudent
        Collections.sort(sortedStudents);
        for (String s : sortedStudents) {
            output = output + "\n" + studentUsernames.get(s).outputGrades();
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
     * Assginment
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
}
