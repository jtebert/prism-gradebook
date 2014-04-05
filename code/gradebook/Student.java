package gradebook;

import java.util.HashMap;

/**
 * Student represents an individual in a GradeBook, with identifying
 * information and scores for assignments
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
class Student {
    /** username of the Student */
    String username;
    /** First name of the Student */
    String firstName;
    /** Last name of the Student */
    String lastName;
    /** Name of the Student's advisor/guidance counselor */
    String advisor;
    /** Student's anticipated graduation year */
    Integer gradYear;
    /** Student's grades for all assignments */
    HashMap<String, Double> grades;
    
    /**
     * Constructor for the Student class
     * @param username Username of the Student
     * @param firstName First name of the Student
     * @param lastName Last name of the Student
     * @param advisor Advisor/guidance counselor of the Student
     * @param gradYear Student's expected year of graduation
     * @param grades Student's grades so far (assignment name, score)
     */
    Student(String username, String firstName, String lastName, String advisor,
            Integer gradYear, HashMap<String, Double> grades) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.advisor = advisor;
        this.gradYear = gradYear;
    }
    
    /**
     * Factory method to construct an new Student
     * @param username Username of the Student
     * @param firstName First name of the Student
     * @param lastName Last name of the Student
     * @param advisor Advisor/guidance counselor of the Student
     * @param gradYear Student's expected year of graduation
     * @return an empty MyGradeBook
     */
    static Student newStudent(String username, String firstName,
            String lastName, String advisor, Integer gradYear) {
        return new Student(username, firstName, lastName, advisor, gradYear,
            new HashMap<String, Double>);
    }
    
    /**
     * Return the Student's grade for a given assignment
     * @param assignmentName Name of the assignment for which to get the grade
     * @return Student's score on the assignment
     */
    Double assignmentGrade(String assignmentName) {
        // Find the assignment in the student's grades
        Set<String> names = this.grades.keySet();
        for (String name : names) {
            if (name.equals(assignmentName)) {
                return this.grades.get(name);
            }
        }
        // (error if no such assignment)
        throw new NoSuchElementException();
    }
    
    /**
     * Return a String of the student's grades for all assignments, separated by
     * tabs.
     */
    String outputGrades() {
        // Add student info to String
        String output = this.username + "\t" + this.firstName + "\t" + this.lastName + "\t" +
            this.advisor + "\t" + this.gradYear;
        Set<String> assignments = this.grades.keySet();
        // Loop through grades and add scores to String
        for (String s : assignments) {
            output = "\t" + this.grades.get(s);
        }
        return output;
    }
    
    /**
     * Return a human-readable String description of the Student
     * @return String of the Student
     */
    public String toString() {
        return firstName + " " + lastName + " (" + username "), " + gradYear +
            "\n\tAdvisor: " + advisor;
    }
}
