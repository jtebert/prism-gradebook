import java.util.ArrayList;
import gradebook.MyGradeBook;

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
    GradeBook gradebook;
    /** Whether or not to keep running the PRISM program */
    Boolean running;
    /** Items contained in the menu for user selection */
    menu ArrayList<String> = new ArrayList<String>();
    menu.add("Add student");
    menu.add("Add students from file");
    menu.add("Remove a student");
    menu.add("Add assignment");
    menu.add("Add assignments from file");
    menu.add("Remove assignment");
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
    
    /**
     * Constructor for PRISM class
     */
    PRISM(GradeBook gradebook) {
        this.gradebook = gradebook;
        this.running = true;
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
        " /\  Welcome to the PRISM Gradebook System\n" +
        "/__\ Portfolio of Records for Instructors' Student Marks\n" +
        "----------------------------------------------------------");
        try {
            // Load file or start new Gradebook (depending on arguments)
            // (loading and invalid/nonexistent file handled by initialize)
            if (args.size() > 1) {
                gradebook = MyGradeBook.initializeWithFile(args.get(0));
                System.out.println("Loaded gradebook from " + args.get(0)+ "\n");
            }
            else {
                gradebook = MyGradeBook.initialize();
                System.out.println("New gradebook created\n");
            }
            gradebook.runPRISM();
        }
        catch (Exception e) {
            // TODO : Adjuct based on correct type of error
            System.out.println("Error: file not found\n" + 
                "PRISM quitting");
        }
    }
    
    /**
     * Run the PRISM progam until given command to quit
     */
    void runPRISM() {
        while(running) {
            // Print menu
            printMenu();
            
            // Request and handle user input
            int selection = getUserInput();
            actOnInput();
        }
        System.out.println("Thank you for using PRISM");
    }
    
    /**
     * Print all of the items in the list to lines on the console
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
        ArrayList<String> menuWithNumbers = new ArrayList<String>;
        for (int i; i < menu.length; i++) {
            menuWithNumbers.add(i + ": " + menu.get(i));
        }
        printList(menuWithNumbers);
    }
    
    /**
     * Request input from the user via the command line and return the
     * selection. If selection is invalid, user will be asked again until they
     * get it right.
     */
    int getUserInput() {
        System.out.println(
            "Enter a number to select an option from the menu:");
        Scanner in = new Scanner(System.in);
        while (true) {
            // Read integer from console
            int selection = in.nextInt();
            if (selection <= menu.size()) {
                in.close();
                return selection;
            }
            else {
                System.out.println(
                    "Invalid selection. Select a number from the menu:");
            }
        }
    }
    
    /**
     *
     */
    void actOnInput(int selection) {
        Scanner in = new Scanner(System.in);
        switch (selection) {
            case 0: // Add student
                System.out.println("Enter the username of the student to add:");
                String username = in.nextLine();
                System.out.println("Enter the student's first name:")
                String firstName = in.nextLine();
                System.out.println("Enter the student's last name:");
                String lastName = in.nextLine();
                System.out.println("Enter the student's advisor:");
                String advisor = in.nextLine();
                System.out.println("Enter the student's graduation year:");
                String gradYear = in.nextLine();
                gradebook.addStudent(username, firstName, lastName,
                    advisor, gradYear);
                System.out.println("Student added to gradebook");
            case 1: // Add students from file
                System.out.println("Enter the name of the file from which to" +
                    " add students:")
                String filename = in.nextLine();
                try {
                    gradebook.addStudents(filename);
                    System.out.println("Students added to gradebook");
                }
                catch (Exception e) {
                    // TODO : Adjust to catch the correct type of error
                    // or file not in right format. (Different type of error?)
                    System.out.println("Error: file not found");
                }
            case 2: // Add assignment
                System.out.println("Enter the assignment to add:");
                String assignmentName = in.nextLine();
                System.out.println(
                    "Enter the total points for the assignment:");
                // TODO : I don't know if this is valid.  Need some way to check
                // for invalid inputs here and for percent
                Double totalPoints = in.nextDouble();
                System.out.println("Enter the percent of the semester grade " +
                    "that the assignment counts for:");
                Double percentGrade = in.nextDouble();
                gradebook.addAssignment(assignmentName, totalPoints,
                    percentGrade);
                System.out.println("Assignment added to gradebook");
            case 3: // Add assignments from file
                System.out.println("Enter the name of the file from which to" +
                    " add assignments:")
                String filename = in.nextLine();
                try {
                    gradebook.addAssignments(filename);
                    System.out.println("Assignments added to gradebook");
                }
                catch (Exception e) {
                    // TODO : Adjust to catch the correct type of error
                    // or file not in right format. (Different type of error?)
                    System.out.println("Error: file not found");
                }
            case 4: // Add/change grade
                System.out.println("Enter the username of the student whose" +
                    " grade to set:")
                String studentUsername = in.nextLine();
                System.out.println("Enter the assignment to to set the grade " +
                    "for:")
                String assignmentName = in.nextLine();
                System.out.println("Enter the new score:")
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
            case 5: // Add student grades from file
                System.out.println("Enter the name of the file from which to" +
                    " add student's grades:")
                String filename = in.nextLine();
                try {
                    gradebook.addStudentGrades(filename);
                    System.out.println("Grades added to gradebook");
                }
                catch (Exception e) {
                    // TODO : Adjust to catch the correct type of error
                    // or file not in right format. (Different type of error?)
                    System.out.println("Error: file not found");
                }
            case 6: // Add assignment grades from file
                System.out.println("Enter the name of the file from which to" +
                    " add student's grades:")
                String filename = in.nextLine();
                try {
                    gradebook.addAssignmentGrades(filename);
                    System.out.println("Grades added to gradebook");
                }
                catch (Exception e) {
                    // TODO : Adjust to catch the correct type of error
                    // or file not in right format. (Different type of error?)
                    System.out.println("Error: file not found");
                }
            case 7: // View assignment statistics"
                System.out.println("Enter the name of the assignment for " +
                "which to see statistics:")
                String assignmentName = in.nextLine();
                try {
                    averageGrade = gradebook.average(assignmentName);
                    medianGrade = gradebook.median(assignmentName);
                    maxGrade = gradebook.max(assignmentName);
                    minGrade = gradebook.min(assignmentName);
                    System.out.println("Average: " + averageGrade +
                        "\nMedian: " + medianGrade + 
                        "\nMax: " + maxGrade + 
                        "\nMin: " + minGrade);
                }
                catch (Exception e) {
                    // TODO : Adjust to catch the correct type of error
                    System.out.println("Error: assignment not found");
                }
            case 8: // View student's course grade"
                System.out.println("Enter the username of the student whose " +
                    "course grade to view:");
                String username = in.nextLine();
                try {
                    currentGrade = gradebook.currentGrade(username);
                    System.out.println("Current grade: " + currentGrade);
                }
                catch (Exception e) {
                    // TODO : Adjust to catch the correct type of error
                    System.out.println("Error: student not found");
                }
            case 9: // View student's assignment grade
                System.out.println("Enter the username of the student whose " +
                    "grade to view:");
                String username = in.nextLine();
                System.out.println("Enter the name of the assignment " +
                    "to view the grade of:");
                String assignmentName = in.nextLine();
                try {
                    double assignmentGrade = gradebook.assignmentGrade(
                        assignmentName, username);
                }
                catch (Exception e) {
                    // TODO : Adjust to catch the correct type of error
                    System.out.println("Error: student/assignment combination" +
                        " does not exist");
                }
            case 10: // Output gradebook
                // TODO
            case 11: // Output assignments
                // TODO
            case 12: // Output student's grades
                // TODO
            case 13: // Output course grades
                // TODO
            case 14: // Compare gradebooks
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
                    System.out.println("Error: student/assignment combination" +
                        " does not exist");
                }
            default: // throw exception?
        }
    }
    
}

menu.add("View assignment statistics");
menu.add("View student's course grade");
menu.add("View student's assignment grade");
menu.add("Output gradebook");
menu.add("Output assignments");
menu.add("Output student's' grades");
menu.add("Output course grades");
menu.add("Compare gradebooks");