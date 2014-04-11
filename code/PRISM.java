import gradebook.MyGradeBook;

import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;

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
    /** Scanner used to get user input from console */
    Scanner in = new Scanner(System.in);
    
    /**
     * Constructor for PRISM class
     * @param gradebook Gradebook that the UI will be handling
     */
    PRISM(MyGradeBook gradebook) {
        this.gradebook = gradebook;
        this.running = true;
        menu = new ArrayList<String>(15);
        menu.add("Add student"); // 0
        menu.add("Remove student"); // 1
        menu.add("Add assignment"); // 2
        menu.add("Remove assignment"); // 3
        menu.add("Add or change a grade"); // 4
        menu.add("Add from file (students, assignments, student grades, or " +
            "assignment grades)"); // 5
        menu.add("View assignment statistics"); // 6
        menu.add("View student's course grade"); // 7
        menu.add("View student's assignment grade"); // 8
        menu.add("View students"); // 9
        menu.add("View assignments"); // 10
        menu.add("Output grade book"); // 11
        menu.add("Output assignments"); // 12
        menu.add("Output student's grades"); // 13
        menu.add("Output course grades"); // 14
        menu.add("Compare grade books"); // 15
        menu.add("Quit PRISM"); // 16
    }
    
    /**
     * Runs the console user interface for the PRISM grade book.
     * @param args Specifies starting file used to initialize the grade book.
     * if no file is specified, the program will start a new grade book.
     */
    public static void main(String[] args) {
       // Print welcome message
        System.out.println("\n" +
            "+-----------------------------------------------------------+\n" +
            "|  /\\   WELCOME TO THE PRISM GRADEBOOK SYSTEM               |\n" +
            "| /  \\                                                      |\n" +
            "|/____\\ Portfolio of Records for Instructors' Student Marks |\n" +
            "+-----------------------------------------------------------+\n");
        try {
            // Load file or start new Gradebook (depending on arguments)
            MyGradeBook newGradebook;
            if (args.length >= 1) {
                newGradebook = MyGradeBook.initializeWithFile(args[0]);
                System.out.println("Loaded grade book from " + args[0] + "\n");
            }
            else {
                newGradebook = MyGradeBook.initialize();
                System.out.println("Empty grade book created\n");
            }
            PRISM prism = new PRISM(newGradebook);
            prism.runPRISM();
        }
        catch (UnsupportedOperationException e) {
            // Invalid contents format
            System.out.println("Error: " + e.getMessage() + "\nPRISM quitting");
        }
        catch (NumberFormatException e) {
            // contents of file invalid
            System.out.println("Error: File contents invalid\nPRISM quitting");
        }
        catch (IndexOutOfBoundsException e) {
            // Contents of file not correctly formatted
            System.out.println("Error: File contents invalid\nPRISM quitting");
        }
        /*catch (Exception e) {
            System.out.println("Error: Unknown error encountered\n" + 
                "PRISM quitting");
        }*/
    }
    
    /**
     * Run the PRISM progam until given command to quit
     */
    private void runPRISM() {
        while (running) {
            printMenu();
            
            // Request and handle user input
            int selection = getMenuSelection();
            actOnInput(selection);
            if (running) {
                System.out.println("\n[ Press enter to continue. ]\n");
                in.nextLine();
            }
        }
        System.out.println("Would you like to save the grade book before " + 
            "quitting? (Y/N)");
        String confirm = in.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            menuOutputGradeBook();
        }
        else {
            System.out.println("Gradebook not saved");
        }
        System.out.println("\nPRISM quitting...\nThank you for using PRISM\n");
    }
    
    /**
     * Print all of the items in the list to lines on the console
     * @param strings List of Strings to be printed
     */
    private void printList(ArrayList<String> strings) {
        if (strings.size() == 0) {
            System.out.println("[List is empty]");
        }
        for (String string : strings) {
            System.out.println(string);
        }
    }
    
    /**
     * Print the items in the menu with numbers
     */
    private void printMenu() {
        ArrayList<String> menuWithNumbers = new ArrayList<String>(menu.size());
        System.out.println(
            "+--------+\n" +
            "|  MENU  |\n" +
            "+--------+");
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
    private int getMenuSelection() {
        System.out.println(
            "\nEnter a number to select an option from the menu:");
        while (true) {
            // Read integer from console
            if (in.hasNextLine()) {
                try {
                    int selection = Integer.parseInt(in.next());
                    in.nextLine();
                    if (selection < menu.size()) {
                        return selection;
                    }
                    else {
                        throw new NumberFormatException("Number not in menu");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println(
                        "Invalid selection. Select a number from the menu:");
                }
            }
        }
    }
    
    /**
     * Output the given String to a file specified by console user input
     * Also includes confirmation of whether or not to overwrite existing files.
     * @param outputString String to save to the text file
     * @param file Name of the file to save to
     */
    private void outputToFile(String outputString, File file) {
        try {
            PrintStream output = new PrintStream(file);
            output.println(outputString);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File could not be written");
        }
    }
    
    /**
     * Ask for a file name, check if it exists, and call to actually save, if
     * that's what the user wants
     * @param outputString String to save to the text file
     */
    private void outputString(String outputString) {
        String filename = in.nextLine();
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("File already exists. " +
                "Do you want to overwrite it? (Y/N)");
            String confirm = in.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                outputToFile(outputString, file);
            }
            else {
                System.out.println("Output not saved to file.");
            }
        }
        else {
            outputToFile(outputString, file);
        }
    }
    
    /**
     * Add a student to the Gradebook based on the user input
     */
    private void menuAddStudent() {
        System.out.println("Enter the username of the student to add:");
        String username = in.nextLine();
        System.out.println("Enter the student's first name:");
        String firstName = in.nextLine();
        System.out.println("Enter the student's last name:");
        String lastName = in.nextLine();
        System.out.println("Enter the student's advisor:");
        String advisor = in.nextLine();
        System.out.println("Enter the student's graduation year:");
        if (in.hasNextLine()) {
            try {
                int gradYear = Integer.parseInt(in.next());
                in.nextLine();
                gradebook.addStudent(username, firstName, lastName,
                    advisor, gradYear);
                System.out.println("Student added to grade book");
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "Error: Invalid graduation year. Student not added.");
                in.nextLine();
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage()
                    + ". Student not added.");
                in.nextLine();
            }
        }
    }
    
    /**
     * Remove a student from the grade book based on user input
     */
    private void menuRemoveStudent() {
        System.out.println("Enter the username of the student to remove:");
        String username = in.nextLine();
        try {
            gradebook.removeStudent(username);
            System.out.println("Student removed from grade book");
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: Student not in grade book");
        }
    }
    
    /**
     * Add an assignment to the grade book based on user input
     */
    private void menuAddAssignment() {
        System.out.println("Enter the name of the assignment to add:");
        String assignmentName = in.nextLine();
        System.out.println(
            "Enter the total points for the assignment:");
        if (in.hasNextLine()) {
            try {
                double totalPoints = Double.parseDouble(in.next());
                in.nextLine();
                if (totalPoints >= 0) {
                    System.out.println("Enter the percent of the total grade " +
                        "this assignment counts for:");
                    if (in.hasNextLine()) {
                        try {
                            double percentGrade = Double.parseDouble(in.next());
                            in.nextLine();
                            if (0 <= percentGrade && percentGrade <= 100) {
                                gradebook.addAssignment(assignmentName,
                                    totalPoints, percentGrade);
                                System.out.println(
                                    "Assignment added to grade book");
                            }
                            else {
                                throw new NumberFormatException(
                                    "Invalid percent");
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println(
                                "Error: Percent must be 0-100. " + 
                                "Assignment not added.");
                        }
                    }
                }
                else {
                    throw new NumberFormatException("Invalid point total");
                }
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "Error: Not a valid point total. Assignment not added.");
            }
        }
    }
    
    /**
     * Remove an assignment from the grade book based on user input
     */
    private void menuRemoveAssignment() {
        System.out.println("Enter the name of the assignment to remove:");
        String assignment = in.nextLine();
        try {
            gradebook.removeAssignment(assignment);
            System.out.println("Assignments removed from grade book");
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: Assignment not in grade book");
        }
    }
    
    /**
     * Add or change a grade based on user input
     */
    private void menuAddGrade() {
        System.out.println("Enter the username of the student whose" +
            " grade to set:");
        String studentUsername = in.nextLine();
        System.out.println("Enter the name of the assignment to to set the " +
            "grade for:");
        String assignmentName = in.nextLine();
        System.out.println("Enter the new score:");
        
        if (in.hasNextLine()) {
            try {
                double newScore = Double.parseDouble(in.next());
                in.nextLine();
                boolean success = gradebook.changeGrade(assignmentName,
                    studentUsername, newScore);
                if (success) {
                    System.out.println("Grade changed");
                }
                else {
                    System.out.println("Error: student/assignment combination" +
                        " does not exist");
                }
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "Error: Invalid score. Student not added.");
                in.nextLine();
            }
        }
    }
        
    /**
     * Add students, assignments, student grades, or assignment grades to the
     * grade book based on user input of file
     * (type automatically detected and added appropriately)
     */
    private void menuAddFromFile() {
        System.out.println("Enter the location and name of the file from" +
            " which to add contents:\n" +
            "(the type of contents will be automatically detected)");
        String filename = in.nextLine();
        try {
            gradebook.processFile(filename);
            System.out.println("File contents added to grade book");
        }
        catch (UnsupportedOperationException e) {
            System.out.println("Error: Invalid or nonexistent file");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: File contents conflict with existing " +
                "grade book contents");
        }
    }
    
    /**
     * View statistics for an assignment based on user input
     */
    private void menuAssignmentStats() {
        System.out.println("Enter the name of the assignment for " +
            "which to see statistics:");
        String assignmentName = in.nextLine();
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
        catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * View a student's grades for the course based on user input
     */
    private void menuStudentCourseGrade() {
        System.out.println("Enter the username of the student whose " +
            "course grade to view:");
        String username = in.nextLine();
        try {
            double currentGrade = gradebook.currentGrade(username);
            System.out.println("Current grade: " + currentGrade);
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * View a student's grade for a particular assignment, based on user input
     */
    private void menuStudentAssignmentGrade() {
        System.out.println("Enter the username of the student whose " +
            "grade to view:");
        String username = in.nextLine();
        System.out.println("Enter the name of the assignment " +
            "to view the grade of:");
        String assignmentName = in.nextLine();
        try {
            double assignmentGrade = gradebook.assignmentGrade(
                assignmentName, username);
            System.out.println(assignmentName + " grade for " + 
                username + ": " + assignmentGrade);
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Output a file of all assignments, students, and grades based on user
     * input
     */
    private void menuOutputGradeBook() {
        String outputString = gradebook.outputGradebook();
        System.out.println("Enter the location and name of the file for the " +
            "grade book output:");
        outputString(outputString);
    }
    
    /**
     * Output a file of an assignment based on user input
     */
    private void menuOutputAssginments() {
        System.out.println("Enter the name of the assignment to output " +
            "grades for:");
        String assignmentName = in.nextLine();
        try {
            String outputString =
                gradebook.outputAssignmentGrades(assignmentName);
            System.out.println("Enter the location and name of the file for " +
                "the assignment grade output:");
            outputString(outputString);
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Output a file of a student's grades based on user input
     */
    private void menuOutputStudentGrades() {
        System.out.println("Enter the username of the student to output " +
            "grades for:");
        String username = in.nextLine();
        try {
            String outputString = gradebook.outputStudentGrades(username);
            System.out.println("Enter the location and name of the file for " +
                "the student grade output:");
            outputString(outputString);
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Output a file of course grades for all students based on user input
     */
    private void menuOutputCourseGrades() {
        String outputString = gradebook.outputCurrentGrades();
        System.out.println("Enter the location and name of the file for the " +
            "course grade output:");
        outputString(outputString);
    }
    
    /**
     * Check whether 2 grade books are the same, based on user input
     */
    private void menuCompareGradeBooks() {
        System.out.println("Enter the filename of the Gradebook to " +
            "check if the same as this grade book:");
        String filename = in.nextLine();
        try {
            MyGradeBook otherGradeBook =
                MyGradeBook.initializeWithFile(filename);
            boolean equality = gradebook.equals(otherGradeBook);
            if (equality) {
                System.out.println("Grade books are the same");
            }
            else {
                System.out.println("Grade books are not the same");
            }
        }
        catch (UnsupportedOperationException e) {
            System.out.println("Error: Invalid grade book for comparison");
        }
    }
    
    /**
     * Perform the correct operation based on the menu item selected by the user
     * @param selection Number of the menu item selected
     */
    private void actOnInput(int selection) {
        switch (selection) {
            case 0: // Add student
                menuAddStudent();
                break;
            case 1: // Remove student
                menuRemoveStudent();
                break;
            case 2: // Add assignment
                menuAddAssignment();
                break;
            case 3: // Remove assignment
                menuRemoveAssignment();
                break;
            case 4: // Add/change grade
                menuAddGrade();
                break;
            case 5: // Add from file
                menuAddFromFile();
                break;
            case 6: // View assignment statistics
                menuAssignmentStats();
                break;
            case 7: // View student's course grade
                menuStudentCourseGrade();
                break;
            case 8: // View student's assignment grade
                menuStudentAssignmentGrade();
                break;
            case 9: // View students
                printList(gradebook.listStudents());
                break;
            case 10: // View assignments
                printList(gradebook.listAssignments());
                break;
            case 11: // Output gradebook
                menuOutputGradeBook();
                break;
            case 12: // Output assignments
                menuOutputAssginments();
                break;
            case 13: // Output student's grades
                menuOutputStudentGrades();
                break;
            case 14: // Output course grades
                menuOutputCourseGrades();
                break;
            case 15: // Compare gradebooks
                menuCompareGradeBooks();
                break;
            case 16: // Quit PRISM
                running = false;
                break;
            default: // throw exception?
                System.out.println("ERROR: Past end of the menu. " + 
                    "You should never see this.");
                break;
        }
    }
    
    /**
     * Override equals method
     * @param o object to campare to
     * @return whether they're equal or not
     */
    public boolean equals(Object o) {
        if (o instanceof PRISM) {
            PRISM thatPRISM = (PRISM)o;
            return gradebook.equals(thatPRISM.gradebook) &&
                menu.equals(thatPRISM.menu);
        }
        else {
            return false;
        }
    }
    
    /**
     * Override the hashCode method
     * @return hashCode of the PRISM
     */
    public int hashCode() {
        return gradebook.hashCode() * menu.hashCode();
    }
}