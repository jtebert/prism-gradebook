package gradebook;

/**
 * Assignment represents a piece of coursework in a GradeBook
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
class Assignment {
    /** Name of the assignment */
    String name;
    /** Total number of points available for the assignment */
    Double totalPoints;
    /** Percentage of the total grade the assignment counts for */
    Double percentGrade;
    
    /**
     * Constructor for the Assignment class
     * @param name Name of the assignment
     * @param totalPoints Maximum points for the assignment
     * @param percentGrade Percentage of the semester grade that the assignment
     *     counts for
     */
    Assignment(String name, Double totalPoints, Double percentGrade) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.percentGrade = percentGrade;
    }
    
    /**
     * Factory method to construct a new Assignment
     * @param name Name of the assignment
     * @param totalPoints Maximum points for the assignment
     * @param percentGrade Percentage of the semester grade that the assignment
     *     counts for
     */
    static Assignment newAssignment(String name, Double totalPoints,
            Double percentGrade) {
        return new Assignment(name, totalPoints, percentGrade);
    }
    
    /**
     * Return a human-readable String describing the Assignment
     * @return String of the Assignment
     */
    public String toString() {
        return name + ", " totalPoints + ", " + percentGrade + "%";
    }
}