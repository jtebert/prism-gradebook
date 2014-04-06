package gradebook;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.ArrayList;

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
    int gradYear;
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
            int gradYear, HashMap<String, Double> grades) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.advisor = advisor;
        this.gradYear = gradYear;
        this.grades = grades;
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
            String lastName, String advisor, int gradYear) {
        return new Student(username, firstName, lastName, advisor, gradYear,
            new HashMap<String, Double>());
    }
    
    /**
     * Return the Student's grade for a given assignment
     * @param assignmentName Name of the assignment for which to get the grade
     * @return Student's score on the assignment
     */
    Double assignmentGrade(String assignmentName) {
        // Find the assignment in the student's grades
        for (String name : this.grades.keySet()) {
            if (name.equals(assignmentName)) {
                return this.grades.get(name);
            }
        }
        // (error if no such assignment)
        throw new NoSuchElementException();
    }
    
    /**
     * Calculates the current grade for the given student
     * 
     * @param assignments
     *            ArrayList containing all assignments for the semester
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
    Double currentGrade(ArrayList<Assignment> assignments) {
        Double scoreSum = new Double(0);
        Double percentSum = new Double(0);
        for (Assignment a : assignments) {
            scoreSum = scoreSum +  ((this.grades.get(a.name) / a.totalPoints) 
                    * a.percentGrade);
            percentSum = percentSum + a.percentGrade;
        }
        return (scoreSum / percentSum) * 100;
    }
    
    /**
     * Return a String of the student's grades for all assignments, separated by
     * tabs.
     */
    String outputGrades() {
        // Add student info to String
        String output = this.username + "\t" + this.firstName + "\t" + this.lastName + "\t" +
            this.advisor + "\t" + this.gradYear;
        ArrayList<String> assignments = new ArrayList<String>();
        for (String name : this.grades.keySet()) {
            assignments.add(name);
        }
        // Loop through grades and add scores to String
        for (int i = assignments.size() - 1; i >= 0; i--) {
            output = output + "\t" + this.grades.get(assignments.get(i));
        }
        return output;
    }
    
    /**
     * Return a human-readable String description of the Student
     * @return String of the Student
     */
    public String toString() {
        return firstName + " " + lastName + " (" + username + "), " + gradYear +
            "\n\tAdvisor: " + advisor;
    }
}
