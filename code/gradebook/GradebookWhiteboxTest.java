package gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * WHITE BOX Tests for GradeBook and associated classes
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
public class GradebookWhiteboxTest extends TestCase {

    /** Constructor for GradebookWhiteboxTest */
    public GradebookWhiteboxTest() { }

    // Sample Gradebooks for testing
    /** grades for student1 */
    private HashMap<String, Double> grades1;
    /** grades for student2 */
    private HashMap<String, Double> grades2;
    /** grades for student3 */
    private HashMap<String, Double> grades3;
    /** grades for student4 */
    private HashMap<String, Double> grades4;
    /** grades for student5 */
    private HashMap<String, Double> grades5;
    /** instance of student */
    private Student student1;
    /** instance of student */
    private Student student2;
    /** instance of student */
    private Student student3;
    /** instance of student */
    private Student student4;
    /** instance of student */
    private Student student5;
    /** instance of assignment */
    private Assignment assignment1;
    /** instance of assignment */
    private Assignment assignment2;
    /** instance of assignment */
    private Assignment assignment3;
    /** instance of assignment */
    private Assignment assignment4;
    /** instance of assignment */
    private Assignment assignment5;
    /** instance of empty list of assignments */
    private ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
    /** instance of empty list of students */
    private ArrayList<Student> studentList = new ArrayList<Student>();
    /** instance of empty gradebook */
    private MyGradeBook gradebook = new MyGradeBook(studentList, assignmentList);

    /**
     * Initialize the data for the tests
     */
    void initData() {
        grades1 = new HashMap<String, Double>();
        grades1.put("assignment1", new Double(8));
        grades1.put("assignment2", new Double(71));
        grades1.put("assignment3", new Double(82));
        grades1.put("assignment4", new Double(65));
        grades1.put("assignment5", new Double(20));
        grades2 = new HashMap<String, Double>();
        grades2.put("assignment1", new Double(6));
        grades2.put("assignment2", new Double(90));
        grades2.put("assignment3", new Double(92));
        grades2.put("assignment4", new Double(88));
        grades2.put("assignment5", new Double(45));
        grades3 = new HashMap<String, Double>();
        grades3.put("assignment1", new Double(8));
        grades3.put("assignment2", new Double(79));
        grades3.put("assignment3", new Double(122));
        grades3.put("assignment4", new Double(85));
        grades3.put("assignment5", new Double(37));
        grades4 = new HashMap<String, Double>();
        grades4.put("assignment1", new Double(8));
        grades4.put("assignment2", new Double(85));
        grades4.put("assignment3", new Double(146));
        grades4.put("assignment4", new Double(57));
        grades4.put("assignment5", new Double(46));
        grades5 = new HashMap<String, Double>();
        grades5.put("assignment1", new Double(5));
        grades5.put("assignment2", new Double(74));
        grades5.put("assignment3", new Double(100));
        grades5.put("assignment4", new Double(82));
        grades5.put("assignment5", new Double(26));

        student1 = new Student("abetaylor", "Isabella", "Taylor",
                "Baker", 2016, grades1);
        student2 = new Student("abethes", "Elizabeth", "White",
                "Nelson", 2014, grades2);
        student3 = new Student("acit", "Jacob", "Smith",
                "Scott", 2014, grades3);
        student4 = new Student("ahrown", "Noah", "Brown",
                "Adams", 2017, grades4);
        student5 = new Student("amller", "Liam", "Miller",
                "Scott", 2014, grades5);

        assignment1 = new Assignment("assignment1", new Double(10), 
                new Double(1));
        assignment2 = new Assignment("assignment2", new Double(100), 
                new Double(5));
        assignment3 = new Assignment("assignment3", new Double(150), 
                new Double(10));
        assignment4 = new Assignment("assignment4", new Double(100), 
                new Double(25));
        assignment5 = new Assignment("assignment5", new Double(50), 
                new Double(20));

        assignmentList = new ArrayList<Assignment>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
        assignmentList.add(assignment3);
        assignmentList.add(assignment4);
        assignmentList.add(assignment5);

        studentList = new ArrayList<Student>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);

        gradebook = new MyGradeBook(studentList, assignmentList);

    }

    /**
     * test the method assignmentGrade for the Student class
     */
    public void testAssignmentGradeStudent() {
        initData();
        assertEquals(student1.assignmentGrade("assignment1"), new Double(8));
        assertEquals(student2.assignmentGrade("assignment2"), new Double(90));
        assertEquals(student3.assignmentGrade("assignment3"), new Double(122));
        assertEquals(student4.assignmentGrade("assignment4"), new Double(57));
        assertEquals(student5.assignmentGrade("assignment5"), new Double(26));
    }
    
    /**
     * test the method currentGrade for the Student class
     */
    public void testCurrentGradeStudent() {
        initData();
        assertEquals(student1.currentGrade(assignmentList), 
                new Double(10220) / new Double(183));
        assertEquals(student2.currentGrade(assignmentList),
                new Double(15370) / new Double(183));
        assertEquals(student3.currentGrade(assignmentList),
                new Double(14680) / new Double(183));
        assertEquals(student4.currentGrade(assignmentList),
                new Double(14230) / new Double(183));
        assertEquals(student5.currentGrade(assignmentList),
                new Double(12530) / new Double(183));
    }
    
    /**
     * test the method outputGrades for the Student class
     */
    public void testOutputGradesStudent() {
        initData();
        assertEquals(student1.outputGrades(),
                "abetaylor\tIsabella\tTaylor\tBaker\t2016"
                + "\t8.0\t71.0\t82.0\t65.0\t20.0");
        assertEquals(student2.outputGrades(),
                "abethes\tElizabeth\tWhite\tNelson\t2014"
                + "\t6.0\t90.0\t92.0\t88.0\t45.0");
    }
    
    /**
     * test the method toString for the Student class
     */
    public void testToStringStudent() {
        initData();
        assertEquals(student1.toString(), "Isabella Taylor (abetaylor), "
                + "2016\n\tAdvisor: Baker");
        assertEquals(student2.toString(), "Elizabeth White (abethes), "
                + "2014\n\tAdvisor: Nelson");
    }
}
