package gradebook;

import java.util.ArrayList;

/**
 * GradeBook represents the assignments, students, and associated scores for a 
 * coure.
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
class MyGradeBook {
    /** Name of the course with information stored in the GradeBook */
    String courseName;
    /** List of the students in the gradebook, SORTED ALPHABETICALLY */
    ArrayList<Student> students;
    /** List of the assignments in the gradebook */
    ArrayList<Assignment> assignments;
    
    /**
     * Constructor for GradeBook
     */
    GradeBook(String courseName, ArrayList<Student> students,
            ArrayList<Assignment> assignments) {
        this.courseName = courseName;
        this.students = students;
        this.assignments = assignments;
    }
    
    /**
     * Factory method to construct an empty MyGradebook
     * 
     * @return an empty MyGradeBook
     */
    public static MyGradeBook initialize() {
        // This doesn't work with the fact that every GradeBook for our
        // design has to have a course name.
        // Should this prompt them to enter a name?
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
    public static MyGradeBook initializeWithString(
            String startingString) {
        // How does this differ from initializeWithFile?
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
            studentFound.grades.remove(assignmentName);
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
        // Get ArrayList of grades from assignmentGrades
        // Get average of ArrayList
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
        // Find the median of ArrayList
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
        // Find minimum of ArrayList
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
        // Find the maximum of ArrayList
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
        //     (error is no such student)
        // use student.currentGrade() to get grade
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
        // (error if no such student)
        // use student.assignmentGrade(assignmentName) to get grade
    }
    
    /**
     * Get the grades of all students for an assignment
     */
    HashMap<String, Double> assignmentGrades() {
        // For each Student, find the Assignment and get the grade
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
        // Get grades with currentGrades()
        // Add line for each element in hashMap
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
        // Add info about assignments (with correct number of preceeding tabs)
        // Find student and add student.outputGrades() to String
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
        // Add header to string
        // Get grades with assignmentGrades
        // Loop through HashMap to list username & grades
        // Add divider
        // Use other methods to calculate stats and add to end
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
        // Add info about assignments (with correct number of preceeding tabs)
        // Loop through students and use student.outputGrades to add lines
    }
}
