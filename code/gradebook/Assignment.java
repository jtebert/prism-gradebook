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
     * @return new Assignment with the given specifications
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
        return name + ": " + totalPoints + ", " + percentGrade + "%";
    }
    
    /**
     * Override the equals method
     * @param o Object to check if equal to the Assignment
     * @return whether or not they are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Assignment) {
            Assignment thatAssignment = (Assignment)o;
            return name.equals(thatAssignment.name) &&
                totalPoints.equals(thatAssignment.totalPoints) &&
                percentGrade.equals(thatAssignment.percentGrade);
        }
        else {
            return false;
        }
    }
    
    /**
     * Override the hashCode method
     * @return hashCode of the Assignment
     */
    public int hashCode() {
        return name.hashCode() * totalPoints.hashCode()
            ^ percentGrade.hashCode();
    }
}