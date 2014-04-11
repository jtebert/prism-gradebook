package gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import junit.framework.TestCase;
import org.junit.Before;

/**
 * WHITE BOX Tests for GradeBook and associated classes
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
public class GradebookWhiteboxTest extends TestCase {

    /** instance of username comparator */
    private ByUsername comp;
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
    private MyGradeBook gradebook =
        new MyGradeBook(studentList, assignmentList);

    /**
     * Initialize the data for the tests
     */
    @Before
    public void setUp() {
        comp = new ByUsername();
        HashMap<String, Double> grades1 = new HashMap<String, Double>();
        HashMap<String, Double> grades2 = new HashMap<String, Double>();
        HashMap<String, Double> grades3 = new HashMap<String, Double>();
        HashMap<String, Double> grades4 = new HashMap<String, Double>();
        HashMap<String, Double> grades5 = new HashMap<String, Double>();
        grades1.put("assignment1", new Double(8));
        grades1.put("assignment2", new Double(71));
        grades1.put("assignment3", new Double(82));
        grades1.put("assignment4", new Double(65));
        grades1.put("assignment5", new Double(20));
        grades2.put("assignment1", new Double(6));
        grades2.put("assignment2", new Double(90));
        grades2.put("assignment3", new Double(92));
        grades2.put("assignment4", new Double(88));
        grades2.put("assignment5", new Double(45));
        grades3.put("assignment1", new Double(8));
        grades3.put("assignment2", new Double(79));
        grades3.put("assignment3", new Double(122));
        grades3.put("assignment4", new Double(85));
        grades3.put("assignment5", new Double(37));
        grades4.put("assignment1", new Double(8));
        grades4.put("assignment2", new Double(85));
        grades4.put("assignment3", new Double(146));
        grades4.put("assignment4", new Double(57));
        grades4.put("assignment5", new Double(46));
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
     * test the method newStudent for the Student class
     */
    public void testNewStudentStudent() {
        Student testStudent = Student.newStudent("enwilson", "Aiden",
                "Wilson", "Nelson", 2014);
        assertEquals(testStudent.username, "enwilson");
        assertEquals(testStudent.firstName, "Aiden");
        assertEquals(testStudent.lastName, "Wilson");
        assertEquals(testStudent.advisor, "Nelson");
        assertEquals(testStudent.gradYear, 2014);
        assertEquals(testStudent.grades, new HashMap<String, Double>());
    }

    /**
     * test the method assignmentGrade for the Student class
     */
    public void testAssignmentGradeStudent() {
        assertEquals(student1.assignmentGrade("assignment1"), new Double(8));
        assertEquals(student2.assignmentGrade("assignment2"), new Double(90));
        assertEquals(student3.assignmentGrade("assignment3"), new Double(122));
        assertEquals(student4.assignmentGrade("assignment4"), new Double(57));
        assertEquals(student5.assignmentGrade("assignment5"), new Double(26));
        try {
            student1.assignmentGrade("assignment0");
            assertTrue(false);
        }
        catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }
    
    /**
     * test the method currentGrade for the Student class
     */
    public void testCurrentGradeStudent() {
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
        assertEquals(student1.outputGrades(assignmentList),
                "abetaylor\tIsabella\tTaylor\tBaker\t2016"
                + "\t8.0\t71.0\t82.0\t65.0\t20.0");
        assertEquals(student2.outputGrades(assignmentList),
                "abethes\tElizabeth\tWhite\tNelson\t2014"
                + "\t6.0\t90.0\t92.0\t88.0\t45.0");
    }
    
    /**
     * test the method toString for the Student class
     */
    public void testToStringStudent() {
        assertEquals(student1.toString(), "Isabella Taylor (abetaylor), "
                + "2016\n\tAdvisor: Baker");
        assertEquals(student2.toString(), "Elizabeth White (abethes), "
                + "2014\n\tAdvisor: Nelson");
    }
    
    /**
     * test the method equals for the Student class
     */
    public void testEqualsStudent() {
        assertTrue(student1.equals(student1));
        assertFalse(student1.equals(student2));
        Student testStudent = Student.newStudent("enwilson", "Aiden",
                "Wilson", "Nelson", 2014);
        Student testStudent2 = Student.newStudent("enwilson", "Biden",
                "Wilson", "Nelson", 2014);
        Student testStudent3 = Student.newStudent("enwilson", "Biden",
                "Wilson", "Nelson", 2014);
        assertFalse(testStudent.equals(testStudent2));
        assertTrue(testStudent2.equals(testStudent3));
    }
    
    /**
     * test the method hashCode for the Student class
     */
    public void testHashCodeStudent() {
        Student testStudent2 = Student.newStudent("enwilson", "Biden",
                "Wilson", "Nelson", 2014);
        Student testStudent3 = Student.newStudent("enwilson", "Biden",
                "Wilson", "Nelson", 2014);
        assertEquals(testStudent2.hashCode(), testStudent3.hashCode());
    }
    
    /**
     * test the method newAssignment for the Assignment class
     */
    public void testNewAssignmentAssignment() {
        Assignment testAssignment = Assignment.newAssignment("assignment0", 
                new Double(20), new Double(3));
        assertEquals(testAssignment.name, "assignment0");
        assertEquals(testAssignment.totalPoints, new Double(20));
        assertEquals(testAssignment.percentGrade, new Double(3));
    }
    
    /**
     * test the method toString for the Assignment class
     */
    public void testToStringAssignment() {
        assertEquals(assignment1.toString(), "assignment1: 10.0, 1.0%");
        assertEquals(assignment2.toString(), "assignment2: 100.0, 5.0%");
    }
    
    /**
     * test the method equals for the Assignment class
     */
    public void testEqualsAssignment() {
        assertFalse(assignment3.equals(assignment4));
        assertFalse(assignment4.equals(assignment5));
        Assignment testAssignment1 = Assignment.newAssignment("assignment1", 
                new Double(10), new Double(1));
        Assignment testAssignment2 = Assignment.newAssignment("assignment2", 
                new Double(100), new Double(5));
        assertTrue(testAssignment1.equals(assignment1));
        assertTrue(testAssignment2.equals(assignment2));
    }

    /**
     * test the method hashCode for the Assignment class
     */ 
    public void testHashCodeAssignment() {
        Assignment testAssignment1 = Assignment.newAssignment("assignment1",
                new Double(10), new Double(1));
        Assignment testAssignment2 = Assignment.newAssignment("assignment2",
                new Double(100), new Double(5));
        assertEquals(testAssignment1.hashCode(), assignment1.hashCode());
        assertEquals(testAssignment2.hashCode(), assignment2.hashCode());
    }

    /**
     * test the method compare for the ByUsername class
     */
    public void testCompareByUsername() {
        assertTrue(comp.compare(student1, student2) < 0);
        assertEquals(comp.compare(student1, student1), 0);
        assertTrue(comp.compare(student2, student1) > 0);
    }
    
    /**
     * test the method assignmentFound for the MyGradeBook class
     */
    public void testAssignmentFoundMyGradeBook() {
        try {
            gradebook.assignmentFound("assignment1");
            assertTrue(true);
        }
        catch (NoSuchElementException e) {
            assertTrue(false);
        }
        try {
            gradebook.assignmentFound("assignment0");
            assertTrue(false);
        }
        catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }
    
    /**
     * test the method studentFound for the MyGradeBook class
     */
    public void testStudentFoundMyGradeBook() {
        try {
            gradebook.studentFound("abetaylor");
            assertTrue(true);
        }
        catch (NoSuchElementException e) {
            assertTrue(false);
        }
        try {
            gradebook.studentFound("abelincoln");
            assertTrue(false);
        }
        catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }
    
    /**
     * test the method assignmentGrades for the MyGradeBook class
     */
    public void testAssignmentGradesMyGradeBook() {
        HashMap<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("abetaylor", new Double(8));
        testMap.put("abethes", new Double(6));
        testMap.put("acit", new Double(8));
        testMap.put("ahrown", new Double(8));
        testMap.put("amller", new Double(5));
        assertEquals(gradebook.assignmentGrades("assignment1"), testMap);
    }
}
