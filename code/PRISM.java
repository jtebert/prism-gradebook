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
        menu.add("Add students from file"); // 1
        menu.add("Remove student"); // 2
        menu.add("Add assignment"); // 3
        menu.add("Add assignments from file"); // 4
        menu.add("Remove assignment"); // 5
        menu.add("Add or change a grade"); // 6
        menu.add("Add student grades from file"); // 7
        menu.add("Add assignment grades from file"); // 8
        menu.add("View assignment statistics"); // 9
        menu.add("View student's course grade"); // 10
        menu.add("View student's assignment grade"); // 11
        menu.add("View students"); // 12
        menu.add("View assignments"); // 13
        menu.add("Output gradebook"); // 14
        menu.add("Output assignments"); // 15
        menu.add("Output student's grades"); // 16
        menu.add("Output course grades"); // 17
        menu.add("Compare gradebooks"); // 18
        menu.add("Quit PRISM"); // 19
    }
    
    /**
     * Runs the console user interface for the PRISM gradebook.
     * @param args Specifies starting file used to initialize the gradebook.
     * if no file is specified, the program will start a new gradebook.
     */
    public static void main(String[] args) {
       // Print welcome message
        System.out.println("\n" +
            "+-----------------------------------------------------------+\n" +
            "|  /\\   WELCOME TO THE PRISM GRADEBOOK SYSTEM               |\n" +
            "| /  \\                                                      |\n" +
            "|/____\\ Portfolio of Records for Instructors' Student Marks |\n" +
            "+-----------------------------------------------------------+\n");
        // TODO : Add back in try/catch once everything is working
        try {
            // Load file or start new Gradebook (depending on arguments)
            // (loading and invalid/nonexistent file handled by initialize)
            MyGradeBook newGradebook;
            if (args.length >= 1) {
                newGradebook = MyGradeBook.initializeWithFile(args[0]);
                System.out.println("Loaded gradebook from " + args[0] + "\n");
            }
            else {
                newGradebook = MyGradeBook.initialize();
                System.out.println("Empty gradebook created\n");
            }
            PRISM prism = new PRISM(newGradebook);
            prism.runPRISM();
        }
        /*catch (IOException e) {
            // File not found
            System.out.println("Error: " + e.getMessage() + "\nPRISM quitting");
        }*/
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
        System.out.println("Would you like to save the gradebook before " + 
            "quitting? (Y/N)");
        String confirm = in.nextLine();
        if (confirm.toLowerCase().equals("y")) {
                menuOutputGradeBook();
            }
            else {
                System.out.println("Gradebook not saved");
            }
        menuOutputGradeBook();
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
     * @param String String to save to the text file
     */
    private void outputString(String outputString) {
        String filename = in.nextLine();
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("File already exists. " +
                "Do you want to overwrite it? (Y/N)");
            String confirm = in.nextLine();
            if (confirm.toLowerCase().equals("y")) {
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
                System.out.println("Student added to gradebook");
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "Error: Invalid graduation year. Student not added.");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage()
                    + ". Student not added.");
            }
        }
    }
    
    /**
     * Add students from a file based on user input
     */
    private void menuAddStudents() {
        System.out.println("Enter the name of the file from which to" +
            " add students:");
        String filename = in.nextLine();
        try {
            gradebook.processFile(filename);
            System.out.println("Students added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: File not found");
        }
    }
    
    /**
     * Remove a student from the gradebookS based on user input
     */
    private void menuRemoveStudent() {
        System.out.println("Enter the username of the student to remove:");
        String username = in.nextLine();
        try {
            gradebook.removeStudent(username);
            System.out.println("Student removed from gradebook");
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: Student not in Gradebook");
        }
    }
    
    /**
     * Add an assignment to the gradebook based on user input
     */
    private void menuAddAssignment() {
        System.out.println("Enter the name of the assignment to add:");
        String assignmentName = in.nextLine();
        System.out.println(
            "Enter the total points for the assignment:");
        // TODO : I don't know if this is valid.
        if (in.hasNextLine()) {
            try {
                double totalPoints = Double.parseDouble(in.next());
                in.nextLine();
                System.out.println("Enter the percent of the total grade " +
                    "this assignment counts for:");
                if (in.hasNextLine()) {
                    try {
                        double percentGrade = Double.parseDouble(in.next());
                        in.nextLine();
                        if (0 <= percentGrade && percentGrade <= 100) {
                            gradebook.addAssignment(assignmentName,
                                totalPoints, percentGrade);
                            System.out.println("Assignment added to gradebook");
                        }
                        else {
                            throw new NumberFormatException("Invalid percent");
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println(
                            "Error: Percent must be 0-100. " + 
                            "Assignment not added.");
                    }
                }
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "Error: Not a valid point total. Assignment not added.");
            }
        }
    }
    
    /**
     * Add assignments from a file based on user input
     */
    private void menuAddAssignments() {
        System.out.println("Enter the name of the file from which to" +
            " add assignments:");
        String filename = in.nextLine();
        try {
            gradebook.processFile(filename);
            System.out.println("Assignments added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: File not found");
        }
    }
    
    /**
     * Remove an assignment from the gradebookS based on user input
     */
    private void menuRemoveAssignment() {
        System.out.println("Enter the name of the assignment to remove:");
        String assignment = in.nextLine();
        try {
            // TODO : add back when method implemented
            gradebook.removeAssignment(assignment);
            System.out.println("Assignments removed from gradebook");
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: Assignment not in Gradebook");
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
                    "Error: Invalid graduation year. Student not added.");
            }
        }
    }
    
    /**
     * Add grades for a student from a file based on user input
     */
    private void menuAddStudentGrades() {
        System.out.println("Enter the name of the file from which to" +
            " add student's grades:");
        String filename = in.nextLine();
        try {
            gradebook.processFile(filename);
            System.out.println("Grades added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: File not found");
        }
    }
    
    /**
     * Add grades for an assignment for multiple students from a file based on
     * user input
     */
    private void menuAddAssignmentGrades() {
        System.out.println("Enter the name of the file from which to" +
            " add student's grades:");
        String filename = in.nextLine();
        try {
            gradebook.processFile(filename);
            System.out.println("Grades added to gradebook");
        }
        catch (Exception e) {
            // TODO : Adjust to catch the correct type of error
            // or file not in right format. (Different type of error?)
            System.out.println("Error: File not found");
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
            // TODO : Adjust to catch the correct type of error
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Output a file of all assignments, students, and grades based on user
     * input
     */
    private void menuOutputGradeBook() {
        String outputString = gradebook.outputGradebook();
        System.out.println("Enter the name of the file for the gradebook " +
            "output:");
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
            System.out.println("Enter the name of the file for the assignment" +
                "grade output:");
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
            System.out.println("Enter the name of the file for the student " +
                "grade output:");
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
        System.out.println("Enter the name of the file for the course grade " +
            "output:");
        outputString(outputString);
    }
    
    /**
     * Check whether 2 gradebooks are the same, based on user input
     */
    private void menuCompareGradeBooks() {
        System.out.println("Enter the filename of the Gradebook to " +
            "check if the same as this gradebook:");
        String filename = in.nextLine();
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
            System.out.println("Error: Student/assignment combination" +
                " does not exist");
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
            case 1: // Add students from file
                menuAddStudents();
                break;
            case 2: // Remove student
                menuRemoveStudent();
                break;
            case 3: // Add assignment
                menuAddAssignment();
                break;
            case 4: // Add assignments from file
                menuAddAssignments();
                break;
            case 5: // Remove assignment
                menuRemoveAssignment();
                break;
            case 6: // Add/change grade
                menuAddGrade();
                break;
            case 7: // Add student grades from file
                menuAddStudentGrades();
                break;
            case 8: // Add assignment grades from file
                menuAddAssignmentGrades();
                break;
            case 9: // View assignment statistics
                menuAssignmentStats();
                break;
            case 10: // View student's course grade
                menuStudentCourseGrade();
                break;
            case 11: // View student's assignment grade
                menuStudentAssignmentGrade();
                break;
            case 12: // View students
                printList(gradebook.listStudents());
                break;
            case 13: // View assignments
                printList(gradebook.listAssignments());
                break;
            case 14: // Output gradebook
                menuOutputGradeBook();
                break;
            case 15: // Output assignments
                menuOutputAssginments();
                break;
            case 16: // Output student's grades
                menuOutputStudentGrades();
                break;
            case 17: // Output course grades
                menuOutputCourseGrades();
                break;
            case 18: // Compare gradebooks
                menuCompareGradeBooks();
                break;
            case 19: // Quit PRISM
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