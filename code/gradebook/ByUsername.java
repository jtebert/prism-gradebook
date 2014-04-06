package gradebook;

import java.util.Comparator;

/**
 * ByUsername represents a comparator that orders students by username
 * lexicographically
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
class ByUsername implements Comparator<Student> {
    
    /**
     * Compare the two given Students by their username lexicographically
     * @param student1 the first student being compared
     * @param student2 the second student being compared
     * @return a negative int if the username of student1 comes before the 
     * username of student2 lexicographically, 0 if the usernames are the same, 
     * and a positive int otherwise
     */
    public int compare(Student student1, Student student2) {
        return student1.username.compareToIgnoreCase(student2.username);
    }
}
