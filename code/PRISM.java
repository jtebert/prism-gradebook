import java.util.ArrayList;
import gradebook.MyGradeBook;
import java.io.*;
import java.util.Scanner;

/**
 * PRISM is the console user interface for interacting with GradeBooks
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
public class PRISM {
    /** GradeBook being handled by the UI */
    MyGradeBook gradebook;
    /** Whether or not to keep running the PRISM program */
    Boolean running;
    /** Items contained in the menu for user selection */
    ArrayList<String> menu;
    
    /**
     * Constructor for PRISM class
     * @param gradebook Gradebook that the UI will be handling
     */
    PRISM(MyGradeBook gradebook) {
        this.gradebook = gradebook;
        this.running = true;
        menu = new ArrayList<String>(15);
        menu.add("Add student");
        menu.add("Add students from file");
        menu.add("Add assignment");
        menu.add("Add assignments from file");
        menu.add("Add or change a grade");
        menu.add("Add student grades from file");
        menu.add("Add assignment grades from file");
        menu.add("View assignment statistics");
        menu.add("View student's course grade");
        menu.add("View student's assignment grade");
        menu.add("Output gradebook");
        menu.add("Output assignments");
        menu.add("Output student's grades");
        menu.add("Output course grades");
        menu.add("Compare gradebooks");
        menu.add("Quit PRISM");
    }
    
    /**
     * Runs the console user interface for the PRISM gradebook.
     * @param args Specifies starting file used to initialize the gradebook.
     * if no file is specified, the program will start a new gradebook.
     */
    public static void main(String[] args) {
       // Print welcome message
        System.out.println(
            "----------------------------------------------------------\n" +
            " /\\  Welcome to the PRISM Gradebook System\n" +
            "/__\\ Portfolio of Records for Instructors' Student Marks\n" +
            "----------------------------------------------------------");
        // TODO : Add back in try/catch once everything is working
        //try {
        // Load file or start new Gradebook (depending on arguments)
        // (loading and invalid/nonexistent file handled by initialize)
        MyGradeBook newGradebook;
        if (args.length > 1) {
            newGradebook = MyGradeBook.initializeWithFile(args[0]);
            System.out.println("Loaded gradebook from " + args[0] + "\n");
        }
        else {
            newGradebook = MyGradeBook.initialize();
            System.out.println("New gradebook created\n");
        }
        PRISM prism = new PRISM(newGradebook);
        prism.runPRISM();
        /*}
        catch (Exception e) {
            // TODO : Adjuct based on correct type of error
            System.out.println("Error: file not found\n" + 
                "PRISM quitting");
        }*/
    }
    
    /**
     * Run the PRISM progam until given command to quit
     */
    void runPRISM() {
        while (running) {
            // Print menu
            printMenu();
            
            // Request and handle user input
            int selection = getMenuSelection();
            actOnInput(selection);
            System.out.println();
        }
        System.out.println("Thank you for using PRISM");
    }
    
    /**
     * Print all of the items in the list to lines on the console
     * @param strings List of Strings to be printed
     */
    void printList(ArrayList<String> strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }
    
    /**
     * Print the items in the menu with numbers
     */
    void printMenu() {
        ArrayList<String> menuWithNumbers = new ArrayList<String>(menu.size());
        for (int i = 0; i < menu.size(); i++) {
            menuWithNumbers.add(i + ": " + menu.get(i));
        }
        printList(menuWithNumbers);
    }
    
    /**
     * Request input from the user via the command line and return the
     * selection for the menu. If selection is invalid, user will be asked again
     * until they get it right.
     * @return integer value of the selection
     */
    int getMenuSelection() {
        System.out.println(
            "Enter a number to select an option from the menu:");
        Scanner in = new Scanner(System.in);
        while (true) {
            // Read integer from console
            int selection = in.nextInt();
            if (selection < menu.size()) {
                in.close();
                System.out.println("Successful input");
                return selection;
            }
            else {
                System.out.println(
                    "Invalid selection. Select a number from the menu:");
            }
        }
    }
    
    /**
     * Add a student to the Gradebook based on the user input
     */
    void menuAddStudent() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the username of the student to add:");
        String username = in.next();
        System.out.println("Enter the student's first name:");
        String firstName = in.next();
        System.out.println("Enter the student's last name:");
        String lastName = in.next();
        System.out.println("Enter the student's advisor:");
        String advisor = in.next();
        System.out.println("Enter the student's graduation year:");
        int gradYear = in.nextInt();
        gradebook.addStudent(username, firstName, lastName,
            advisor, gradYear);
        System.out.println("Student added to gradebook");
        in.close();
    }
    
    /**
     * Add students from a file based on user input
     */
    void menuAddStudents() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file from which to" +
            " add students:");
        String filename = in.next();
        try {
            // TODO : add back when method implemented
            // gradebook.addStudents(filename);
            System.out.println("Students added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: file not found");
        }
        in.close();
    }
    
    /**
     * Add an assignment to the gradebook based on user input
     */
    void menuAddAssignment() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the assignment to add:");
        String assignmentName = in.next();
        System.out.println(
            "Enter the total points for the assignment:");
        // TODO : I don't know if this is valid.  Need some way to check
        // for invalid inputs here and for percent
        Double totalPoints = in.nextDouble();
        System.out.println("Enter the percent of the semester grade " +
            "that the assignment counts for:");
        Double percentGrade = in.nextDouble();
        gradebook.addAssignment(assignmentName, totalPoints, percentGrade);
        System.out.println("Assignment added to gradebook");
        in.close();
    }
    
    /**
     * Add assignments from a file based on user input
     */
    void menuAddAssignments() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file from which to" +
            " add assignments:");
        String filename = in.next();
        try {
            // TODO : add back when method implemented
            // gradebook.addAssignments(filename);
            System.out.println("Assignments added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: file not found");
        }
        in.close();
    }
    
    /**
     * Add or change a grade based on user input
     */
    void menuAddGrade() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the username of the student whose" +
            " grade to set:");
        String studentUsername = in.next();
        System.out.println("Enter the assignment to to set the grade " +
            "for:");
        String assignmentName = in.next();
        System.out.println("Enter the new score:");
        // TODO : Same thing with error catching
        Double newScore = in.nextDouble();
        boolean success = gradebook.changeGrade(assignmentName,
            studentUsername, newScore);
        if (success) {
            System.out.println("Grade changed");
        }
        else {
            System.out.println("Error: student/assignment combination" +
                " does not exist");
        }
        in.close();
    }
    
    /**
     * Add grades for a student from a file based on user input
     */
    void menuAddStudentGrades() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file from which to" +
            " add student's grades:");
        String filename = in.next();
        try {
            // TODO : add back when method implemented
            // gradebook.addStudentGrades(filename);
            System.out.println("Grades added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: file not found");
        }
        in.close();
    }
    
    /**
     * Add grades for an assignment for multiple students from a file based on
     * user input
     */
    void menuAddAssignmentGrades() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file from which to" +
            " add student's grades:");
        String filename = in.next();
        try {
            // TODO : add back when method implemented
            // gradebook.addAssignmentGrades(filename);
            System.out.println("Grades added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: file not found");
        }
        in.close();
    }
    
    /**
     * View statistics for an assignment based on user input
     */
    void menuAssignmentStats() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the assignment for " +
            "which to see statistics:");
        String assignmentName = in.next();
        try {
            double averageGrade = gradebook.average(assignmentName);
            double medianGrade = gradebook.median(assignmentName);
            double maxGrade = gradebook.max(assignmentName);
            double minGrade = gradebook.min(assignmentName);
            System.out.println("Average: " + averageGrade +
                "\nMedian: " + medianGrade + 
                "\nMax: " + maxGrade + 
                "\nMin: " + minGrade);
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            System.out.println("Error: assignment not found");
        }
        in.close();
    }
    
    /**
     * View a student's grades for the course based on user input
     */
    void menuStudentCourseGrade() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the username of the student whose " +
            "course grade to view:");
        String username = in.next();
        try {
            double currentGrade = gradebook.currentGrade(username);
            System.out.println("Current grade: " + currentGrade);
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            System.out.println("Error: student not found");
        }
        in.close();
    }
    
    /**
     * View a student's grade for a particular assignment, based on user input
     */
    void menuStudentAssignmentGrade() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the username of the student whose " +
            "grade to view:");
        String username = in.next();
        System.out.println("Enter the name of the assignment " +
            "to view the grade of:");
        String assignmentName = in.next();
        try {
            double assignmentGrade = gradebook.assignmentGrade(
                assignmentName, username);
            System.out.println(assignmentName + " grade for " + 
                username + ": " + assignmentGrade);
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            System.out.println("Error: student/assignment combination" +
                " does not exist");
        }
        in.close();
    }
    
    /**
     * Output a file of all assignments, students, and grades based on user
     * input
     */
    void menuOutputGradeBook() {
        Scanner in = new Scanner(System.in);
        // TODO
        in.close();
    }
    
    /**
     * Output a file of all assignments based on user input
     */
    void menuOutputAssginments() {
        Scanner in = new Scanner(System.in);
        // TODO
        in.close();
    }
    
    /**
     * Output a file of a student's grades based on user input
     */
    void menuOutputStudentGrades() {
        Scanner in = new Scanner(System.in);
        // TODO
        in.close();
    }
    
    /**
     * Output a file of course grades for all students based on user input
     */
    void menuOutputCourseGrades() {
        Scanner in = new Scanner(System.in);
        // TODO
        in.close();
    }
    
    /**
     * Check whether 2 gradebooks are the same, based on user input
     */
    void menuCompareGradeBooks() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the filename of the Gradebook to " +
            "check if the same as this gradebook:");
        String filename = in.next();
        try {
            MyGradeBook otherGradeBook =
                MyGradeBook.initializeWithFile(filename);
            boolean equality = gradebook.equals(otherGradeBook);
            if (equality) {
                System.out.println("Gradebooks are the same");
            }
            else {
                System.out.println("Gradebooks are not the same");
            }
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            System.out.println("Error: student/assignment combination" +
                " does not exist");
        }
        in.close();
    }
    
    /**
     * Perform the correct operation based on the menu item selected by the user
     * @param selection Number of the menu item selected
     */
    void actOnInput(int selection) {
        switch (selection) {
            case 0: // Add student
                menuAddStudent();
                break;
            case 1: // Add students from file
                menuAddStudents();
                break;
            case 2: // Add assignment
                menuAddAssignment();
                break;
            case 3: // Add assignments from file
                menuAddAssignments();
                break;
            case 4: // Add/change grade
                menuAddGrade();
                break;
            case 5: // Add student grades from file
                menuAddStudentGrades();
                break;
            case 6: // Add assignment grades from file
                menuAddAssignmentGrades();
                break;
            case 7: // View assignment statistics
                menuAssignmentStats();
                break;
            case 8: // View student's course grade
                menuStudentCourseGrade();
                break;
            case 9: // View student's assignment grade
                menuStudentAssignmentGrade();
                break;
            case 10: // Output gradebook
                menuOutputGradeBook();
                break;
            case 11: // Output assignments
                menuOutputAssginments();
                break;
            case 12: // Output student's grades
                menuOutputStudentGrades();
                break;
            case 13: // Output course grades
                menuOutputCourseGrades();
                break;
            case 14: // Compare gradebooks
                menuCompareGradeBooks();
                break;
            case 15: // Quit PRISM
                running = false;
                break;
            default: // throw exception?
                System.out.println("ERROR: Past end of the menu. " + 
                    "You should never see this.");
                break;
        }
    }
    
}