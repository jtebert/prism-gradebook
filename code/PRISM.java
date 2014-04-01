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
    GradeBook gradebook;
    /** Items contained in the menu for user selection */
    menu ArrayList<String>
    
    /**
     * Constructor for PRISM class
     */
    PRISM(GradeBook gradebook, menu ArrayList<String>) {
        this.gradebook = gradebook;
        this.menu = menu;
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
            menuWithNumbers.add(i + menu.get(i));
        }
        printList(menuWithNumbers);
    }
}