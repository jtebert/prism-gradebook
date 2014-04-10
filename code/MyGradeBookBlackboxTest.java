import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import junit.framework.TestCase;
import gradebook.MyGradeBook;


/**
 * Black Box Tests For MyGradeBook
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
public class MyGradeBookBlackboxTest extends TestCase {

    /**
     * Type Declaration For Example Variables
     */
    private MyGradeBook mgb1;

    private MyGradeBook mgb2;

    private MyGradeBook mgb3;
    //TODO
    private String example_file = null;
    //TODO
    private String example_string = null;


    /**
     * Setup Method
     */
    protected void setUp() {
        mgb1 = MyGradeBook.initialize();

        mgb1.addStudent("as15",
                "Astrid",
                "Scarlett",
                "Phyllis",
                2015);
        mgb1.addStudent("cd14",
                "Caseo",
                "Dent",
                "Norphyllis",
                2014);
        mgb1.addStudent("lou.car",
                "Louis",
                "Carb",
                "Comi Scans",
                2014);
        mgb1.addStudent("FBluver",
                "Norwood",
                "Bilder",
                "Tim Snoo Roman",
                2015);
        mgb1.addStudent("hgwrtz98",
                "Abby",
                "Granger",
                "Comi Scans",
                1999);


        mgb1.addAssignment("Quiz 1", 30.0, 6.0);
        mgb1.addAssignment("Quiz 2", 20.0, 6.0);
        mgb1.addAssignment("Quiz 3", 10.0, 6.0);
        mgb1.addAssignment("Quiz 4", 15.0, 6.0);
        mgb1.addAssignment("Assignment 1", 34.0, 8.0);
        mgb1.addAssignment("Assignment 2", 23.0, 8.0);
        mgb1.addAssignment("Test 1", 100.0, 20.0);
        mgb1.addAssignment("Test 2", 100.0, 20.0);
        mgb1.addAssignment("Test 3", 100.0, 20.0);


        assertTrue(mgb1.changeGrade("Quiz 1", "cd14", 20.0));
        assertTrue(mgb1.changeGrade("Quiz 2", "cd14", 15.0));
        assertTrue(mgb1.changeGrade("Quiz 3", "cd14", 2.0));
        assertTrue(mgb1.changeGrade("Quiz 4", "cd14", 12.0));
        assertTrue(mgb1.changeGrade("Assignment 1", "cd14", 17.0));
        assertTrue(mgb1.changeGrade("Assignment 2", "cd14", 16.0));
        assertTrue(mgb1.changeGrade("Test 1", "cd14", 43.0));
        assertTrue(mgb1.changeGrade("Test 2", "cd14", 57.0));
        assertTrue(mgb1.changeGrade("Test 3", "cd14", 94.0));

        assertTrue(mgb1.changeGrade("Quiz 1", "lou.car", 27.0));
        assertTrue(mgb1.changeGrade("Quiz 2", "lou.car", 18.0));
        assertTrue(mgb1.changeGrade("Quiz 3", "lou.car", 13.0));
        assertTrue(mgb1.changeGrade("Quiz 4", "lou.car", 10.0));
        assertTrue(mgb1.changeGrade("Assignment 1", "lou.car", 23.0));
        assertTrue(mgb1.changeGrade("Assignment 2", "lou.car", 19.0));
        assertTrue(mgb1.changeGrade("Test 1", "lou.car", 85.0));
        assertTrue(mgb1.changeGrade("Test 2", "lou.car", 71.0));
        assertTrue(mgb1.changeGrade("Test 3", "lou.car", 15.0));

        assertTrue(mgb1.changeGrade("Quiz 1", "hgwrtz98", 12.0));
        assertTrue(mgb1.changeGrade("Quiz 2", "hgwrtz98", 4.0));
        assertTrue(mgb1.changeGrade("Quiz 3", "hgwrtz98", 10.0));
        assertTrue(mgb1.changeGrade("Quiz 4", "hgwrtz98", 19.0));
        assertTrue(mgb1.changeGrade("Assignment 1", "hgwrtz98", 19.0));
        assertTrue(mgb1.changeGrade("Assignment 2", "hgwrtz98", 20.0));
        assertTrue(mgb1.changeGrade("Test 1", "hgwrtz98", 88.0));
        assertTrue(mgb1.changeGrade("Test 2", "hgwrtz98", 34.0));
        assertTrue(mgb1.changeGrade("Test 3", "hgwrtz98", 77.0));

        assertTrue(mgb1.changeGrade("Quiz 1", "FBluver", 30.0));
        assertTrue(mgb1.changeGrade("Quiz 2", "FBluver", 20.0));
        assertTrue(mgb1.changeGrade("Quiz 3", "FBluver", 10.0));
        assertTrue(mgb1.changeGrade("Quiz 4", "FBluver", 15.0));
        assertTrue(mgb1.changeGrade("Assignment 1", "FBluver", 34.0));
        assertTrue(mgb1.changeGrade("Assignment 2", "FBluver", 23.0));
        assertTrue(mgb1.changeGrade("Test 1", "FBluver", 100.0));
        assertTrue(mgb1.changeGrade("Test 2", "FBluver", 100.0));
        assertTrue(mgb1.changeGrade("Test 3", "FBluver", 100.0));

    }

    /**
     * Test for Method addStudent(String, String, String, String, int)
     */
    public void testAddStudent() {

        try {
            mgb1.addStudent("hgwrtz98",
                    "Abby",
                    "Granger",
                    "Comi Scans",
                    1999);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "A student with" +
                    " the given username already exists");
        } 

        try {
            mgb1.addStudent("rufus",
                    "RU",
                    "fus",
                    "Phyllis",
                    2016);
            assertTrue(true);
        }
        catch (Exception e) {
            fail();
        }

    }

    /**
     * Test for Method removeStudent(Student)
     */
    public void testRemoveStudent() {

        MyGradeBook temp1 = MyGradeBook.initialize();
        temp1.addStudent("as15",
                "Astrid",
                "Scarlett",
                "Phyllis",
                2015);
        temp1.addStudent("cd14",
                "Caseo",
                "Dent",
                "Norphyllis",
                2014);
        MyGradeBook temp2 = MyGradeBook.initialize();
        temp2.addStudent("as15",
                "Astrid",
                "Scarlett",
                "Phyllis",
                2015);
        temp2.addStudent("cd14",
                "Caseo",
                "Dent",
                "Norphyllis",
                2014);
        assertTrue(temp1.equals(temp2));
        temp1.removeStudent("cd14");
        assertFalse(temp1.equals(temp2));
    }

    /**
     * Test for Method addAssignment(String, Double, Double)
     */
    public void testAddAssignment() {

        try {
            mgb1.addAssignment("Test 3", 100.0, 20.0);
            fail();
        }
        catch (RuntimeException e) {

        } 

        try {
            mgb1.addAssignment("Test 233", 100.0, 20.0);
            assertTrue(true);
        }
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "An assignment with" +
                    "the given name already exists");
        }

    }

    /**
     * Test for Method removeAssignment(Assignment)
     */
    public void testRemoveAssignment() {
        MyGradeBook temp1 = MyGradeBook.initialize();
        temp1.addAssignment("Quiz 1", 30.0, 6.0);
        temp1.addAssignment("Quiz 2", 20.0, 6.0);
        MyGradeBook temp2 = MyGradeBook.initialize();
        temp2.addAssignment("Quiz 1", 30.0, 6.0);
        temp2.addAssignment("Quiz 2", 20.0, 6.0);
        assertTrue(temp1.equals(temp2));
        temp1.removeAssignment("Quiz 1");
        assertFalse(temp1.equals(temp2));
    }

    /**
     * Test for Methods Relate to Grade Operations, Including:
     * currentGrade(String)
     * changeGrade(String, String, Double)
     * average()
     * min()
     * max()
     * median()
     */
    public void testGradeOperations() {

        /**
         * Current/Change grade tests
         */
        assertEquals(mgb1.currentGrade("as15"), 0.0);

        try {
            mgb1.currentGrade("RubADubDub");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }

        assertTrue(mgb1.changeGrade("Test 2", "as15", 50.0));

        assertEquals(mgb1.currentGrade("as15"), 10.0);

        assertTrue(Math.abs(mgb1.currentGrade("as15") - 10.0) < .01);
        assertTrue(Math.abs(mgb1.currentGrade("cd14") - 62.87) < .01);
        assertTrue(Math.abs(mgb1.currentGrade("lou.car") - 68.82) < .01);
        assertTrue(Math.abs(mgb1.currentGrade("FBluver") - 100.0) < .01);
        assertTrue(Math.abs(mgb1.currentGrade("hgwrtz98") - 68.43) < .01);

        assertFalse(mgb1.changeGrade("Test 24", "as15", 50.0));
        assertFalse(mgb1.changeGrade("Test 2", "as151", 50.0));

        /**
         * Average Tests
         */
        assertEquals(mgb1.average("Test 2"), 62.4);

        assertTrue(mgb1.changeGrade("Test 2", "as15", 100.00));

        assertEquals(mgb1.average("Quiz 1"), 17.8);
        assertEquals(mgb1.average("Quiz 2"), 11.4);
        assertEquals(mgb1.average("Quiz 3"), 7.0);
        assertEquals(mgb1.average("Quiz 4"), 11.2);
        assertEquals(mgb1.average("Assignment 1"), 18.6);
        assertEquals(mgb1.average("Assignment 2"), 15.6);
        assertEquals(mgb1.average("Test 1"), 63.2);
        assertEquals(mgb1.average("Test 2"), 72.4);
        assertEquals(mgb1.average("Test 3"), 57.2);

        try {
            mgb1.average("Test 15");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }


        /**
         * Min Tests
         */
        assertEquals(mgb1.min("Quiz 1"), 0.0);
        assertEquals(mgb1.min("Quiz 2"), 0.0);
        assertEquals(mgb1.min("Quiz 3"), 0.0);
        assertEquals(mgb1.min("Quiz 4"), 0.0);
        assertEquals(mgb1.min("Assignment 1"), 0.0);
        assertEquals(mgb1.min("Assignment 2"), 0.0);
        assertEquals(mgb1.min("Test 1"), 0.0);
        assertEquals(mgb1.min("Test 2"), 34.0);
        assertEquals(mgb1.min("Test 3"), 0.0);
        
        MyGradeBook testMin = MyGradeBook.initialize();
        testMin.addAssignment("testMin", 40.0, 5.0);
        assertEquals(testMin.min("testMin"), 0.0);

        try {
            mgb1.min("Test 15");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }
        /**
         * Max Tests
         */
        assertEquals(mgb1.max("Quiz 1"), 30.0);
        assertEquals(mgb1.max("Quiz 2"), 20.0);
        assertEquals(mgb1.max("Quiz 3"), 13.0);
        assertEquals(mgb1.max("Quiz 4"), 19.0);
        assertEquals(mgb1.max("Assignment 1"), 34.0);
        assertEquals(mgb1.max("Assignment 2"), 23.0);
        assertEquals(mgb1.max("Test 1"), 100.0);
        assertEquals(mgb1.max("Test 2"), 100.0);
        assertEquals(mgb1.max("Test 3"), 100.0);

        MyGradeBook testMax = MyGradeBook.initialize();
        testMax.addAssignment("testMax", 40.0, 5.0);
        assertEquals(testMax.max("testMax"), 0.0);

        try {
            mgb1.max("Test 15");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }


        /**
         * Median Tests
         */

        assertEquals(mgb1.median("Quiz 1"), 20.0);
        assertEquals(mgb1.median("Quiz 2"), 15.0);
        assertEquals(mgb1.median("Quiz 3"), 10.0);
        assertEquals(mgb1.median("Quiz 4"), 12.0);
        assertEquals(mgb1.median("Assignment 1"), 19.0);
        assertEquals(mgb1.median("Assignment 2"), 19.0);
        assertEquals(mgb1.median("Test 1"), 85.0);
        assertEquals(mgb1.median("Test 2"), 71.0);
        assertEquals(mgb1.median("Test 3"), 77.0);

        try {
            mgb1.median("Test 15");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }

    /**
     * Test for Method currentGrades()
     */
    public void testCurrentGrades() {

        HashMap<String, Double> testMap = mgb1.currentGrades();

        assertTrue(Math.abs(testMap.get("as15") - 0.0) < .01);
        assertTrue(Math.abs(testMap.get("cd14") - 62.87) < .01);
        assertTrue(Math.abs(testMap.get("lou.car") - 68.82) < .01);
        assertTrue(Math.abs(testMap.get("FBluver") - 100.0) < .01);
        assertTrue(Math.abs(testMap.get("hgwrtz98") - 68.43) < .01);

    }

    /**
     * Test for Method assignmentGrade(String, String)
     */
    public void testAssignmentGrade() {

        assertEquals(mgb1.assignmentGrade("Quiz 1", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Quiz 2", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Quiz 3", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Quiz 4", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Assignment 1", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Assignment 2", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Test 1", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Test 2", "as15"), 0.0);
        assertEquals(mgb1.assignmentGrade("Test 3", "as15"), 0.0);

        assertEquals(mgb1.assignmentGrade("Quiz 1", "cd14"), 20.0);
        assertEquals(mgb1.assignmentGrade("Quiz 2", "cd14"), 15.0);
        assertEquals(mgb1.assignmentGrade("Quiz 3", "cd14"), 2.0);
        assertEquals(mgb1.assignmentGrade("Quiz 4", "cd14"), 12.0);
        assertEquals(mgb1.assignmentGrade("Assignment 1", "cd14"), 17.0);
        assertEquals(mgb1.assignmentGrade("Assignment 2", "cd14"), 16.0);
        assertEquals(mgb1.assignmentGrade("Test 1", "cd14"), 43.0);
        assertEquals(mgb1.assignmentGrade("Test 2", "cd14"), 57.0);
        assertEquals(mgb1.assignmentGrade("Test 3", "cd14"), 94.0);

        assertEquals(mgb1.assignmentGrade("Quiz 1", "lou.car"), 27.0);
        assertEquals(mgb1.assignmentGrade("Quiz 2", "lou.car"), 18.0);
        assertEquals(mgb1.assignmentGrade("Quiz 3", "lou.car"), 13.0);
        assertEquals(mgb1.assignmentGrade("Quiz 4", "lou.car"), 10.0);
        assertEquals(mgb1.assignmentGrade("Assignment 1", "lou.car"), 23.0);
        assertEquals(mgb1.assignmentGrade("Assignment 2", "lou.car"), 19.0);
        assertEquals(mgb1.assignmentGrade("Test 1", "lou.car"), 85.0);
        assertEquals(mgb1.assignmentGrade("Test 2", "lou.car"), 71.0);
        assertEquals(mgb1.assignmentGrade("Test 3", "lou.car"), 15.0);

        assertEquals(mgb1.assignmentGrade("Quiz 1", "hgwrtz98"), 12.0);
        assertEquals(mgb1.assignmentGrade("Quiz 2", "hgwrtz98"), 4.0);
        assertEquals(mgb1.assignmentGrade("Quiz 3", "hgwrtz98"), 10.0);
        assertEquals(mgb1.assignmentGrade("Quiz 4", "hgwrtz98"), 19.0);
        assertEquals(mgb1.assignmentGrade("Assignment 1", "hgwrtz98"), 19.0);
        assertEquals(mgb1.assignmentGrade("Assignment 2", "hgwrtz98"), 20.0);
        assertEquals(mgb1.assignmentGrade("Test 1", "hgwrtz98"), 88.0);
        assertEquals(mgb1.assignmentGrade("Test 2", "hgwrtz98"), 34.0);
        assertEquals(mgb1.assignmentGrade("Test 3", "hgwrtz98"), 77.0);

        assertEquals(mgb1.assignmentGrade("Quiz 1", "FBluver"), 30.0);
        assertEquals(mgb1.assignmentGrade("Quiz 2", "FBluver"), 20.0);
        assertEquals(mgb1.assignmentGrade("Quiz 3", "FBluver"), 10.0);
        assertEquals(mgb1.assignmentGrade("Quiz 4", "FBluver"), 15.0);
        assertEquals(mgb1.assignmentGrade("Assignment 1", "FBluver"), 34.0);
        assertEquals(mgb1.assignmentGrade("Assignment 2", "FBluver"), 23.0);
        assertEquals(mgb1.assignmentGrade("Test 1", "FBluver"), 100.0);
        assertEquals(mgb1.assignmentGrade("Test 2", "FBluver"), 100.0);
        assertEquals(mgb1.assignmentGrade("Test 3", "FBluver"), 100.0);

        assertTrue(mgb1.changeGrade("Quiz 1", "as15", 17.0));
        assertTrue(mgb1.changeGrade("Quiz 2", "cd14", 6.0));
        assertTrue(mgb1.changeGrade("Quiz 3", "hgwrtz98", 3.0));
        assertTrue(mgb1.changeGrade("Quiz 4", "FBluver", 11.0));
        assertTrue(mgb1.changeGrade("Assignment 1", "lou.car", 19.0));
        assertTrue(mgb1.changeGrade("Assignment 2", "as15", 20.0));
        assertTrue(mgb1.changeGrade("Test 1", "cd14", 70.0));
        assertTrue(mgb1.changeGrade("Test 2", "hgwrtz98", 95.0));
        assertTrue(mgb1.changeGrade("Test 3", "FBluver", 91.0));

        assertEquals(mgb1.assignmentGrade("Quiz 1", "as15"), 17.0);
        assertEquals(mgb1.assignmentGrade("Quiz 2", "cd14"), 6.0);
        assertEquals(mgb1.assignmentGrade("Quiz 3", "hgwrtz98"), 3.0);
        assertEquals(mgb1.assignmentGrade("Quiz 4", "FBluver"), 11.0);
        assertEquals(mgb1.assignmentGrade("Assignment 1", "lou.car"), 19.0);
        assertEquals(mgb1.assignmentGrade("Assignment 2", "as15"), 20.0);
        assertEquals(mgb1.assignmentGrade("Test 1", "cd14"), 70.0);
        assertEquals(mgb1.assignmentGrade("Test 2", "hgwrtz98"), 95.0);
        assertEquals(mgb1.assignmentGrade("Test 3", "FBluver"), 91.0);

        try {
            mgb1.assignmentGrade("Quarz 2", "as15");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }

        try {
            mgb1.assignmentGrade("Quiz 2", "as155");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }

        try {
            mgb1.assignmentGrade("Quarz 2", "as155");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }

    }

    /**
     * Test for Method outputCurrentGrades()
     */
    public void testOutputCurrentGrades() {

        String test = "CURRENT_GRADES" + "\n" +
                "as15\t" + mgb1.currentGrade("as15") + "\n" +
                "cd14\t" + mgb1.currentGrade("cd14") + "\n" +
                "FBluver\t" + mgb1.currentGrade("FBluver") + "\n" +
                "hgwrtz98\t" + mgb1.currentGrade("hgwrtz98") + "\n" +
                "lou.car\t" + mgb1.currentGrade("lou.car");

        assertEquals(mgb1.outputCurrentGrades(), test);
    }

    /**
     * Test for Method outputStudentGrades(String)
     */
    public void testOutputStudentGrades() {

        String as15 = "STUDENT_GRADES\nas15\n" +
                "Astrid\nScarlett\nPhyllis\n2015\n----\n" +
                "Quiz 1\t0.0\n" +
                "Quiz 2\t0.0\n" +
                "Quiz 3\t0.0\n" +
                "Quiz 4\t0.0\n" +
                "Assignment 1\t0.0\n" +
                "Assignment 2\t0.0\n" +
                "Test 1\t0.0\n" +
                "Test 2\t0.0\n" +
                "Test 3\t0.0\n" +
                "----\nCURRENT GRADE\t" +
                mgb1.currentGrade("as15");

        assertEquals(mgb1.outputStudentGrades("as15"), as15);

        String cd14 = "STUDENT_GRADES\ncd14\n" +
                "Caseo\nDent\nNorphyllis\n2014\n----\n" +
                "Quiz 1\t20.0\n" +
                "Quiz 2\t15.0\n" +
                "Quiz 3\t2.0\n" +
                "Quiz 4\t12.0\n" +
                "Assignment 1\t17.0\n" +
                "Assignment 2\t16.0\n" +
                "Test 1\t43.0\n" +
                "Test 2\t57.0\n" +
                "Test 3\t94.0\n" +
                "----\nCURRENT GRADE\t" +
                mgb1.currentGrade("cd14");

        assertEquals(mgb1.outputStudentGrades("cd14"), cd14);

        String FBluver = "STUDENT_GRADES\nFBluver\n" +
                "Norwood\nBilder\nTim Snoo Roman\n2015\n----\n" +
                "Quiz 1\t30.0\n" +
                "Quiz 2\t20.0\n" +
                "Quiz 3\t10.0\n" +
                "Quiz 4\t15.0\n" +
                "Assignment 1\t34.0\n" +
                "Assignment 2\t23.0\n" +
                "Test 1\t100.0\n" +
                "Test 2\t100.0\n" +
                "Test 3\t100.0\n" +
                "----\nCURRENT GRADE\t" +
                mgb1.currentGrade("FBluver");

        assertEquals(mgb1.outputStudentGrades("FBluver"), FBluver);

        String hgwrtz98 = "STUDENT_GRADES\nhgwrtz98\n" +
                "Abby\nGranger\nComi Scans\n1999\n----\n" +
                "Quiz 1\t12.0\n" +
                "Quiz 2\t4.0\n" +
                "Quiz 3\t10.0\n" +
                "Quiz 4\t19.0\n" +
                "Assignment 1\t19.0\n" +
                "Assignment 2\t20.0\n" +
                "Test 1\t88.0\n" +
                "Test 2\t34.0\n" +
                "Test 3\t77.0\n" +
                "----\nCURRENT GRADE\t" +
                mgb1.currentGrade("hgwrtz98");

        assertEquals(mgb1.outputStudentGrades("hgwrtz98"), hgwrtz98);

        String loucar = "STUDENT_GRADES\nlou.car\n" +
                "Louis\nCarb\nComi Scans\n2014\n----\n" +
                "Quiz 1\t27.0\n" +
                "Quiz 2\t18.0\n" +
                "Quiz 3\t13.0\n" +
                "Quiz 4\t10.0\n" +
                "Assignment 1\t23.0\n" +
                "Assignment 2\t19.0\n" +
                "Test 1\t85.0\n" +
                "Test 2\t71.0\n" +
                "Test 3\t15.0\n" +
                "----\nCURRENT GRADE\t" +
                mgb1.currentGrade("lou.car");

        assertEquals(mgb1.outputStudentGrades("lou.car"), loucar);

        try {
            mgb1.outputStudentGrades("billo");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }

    }

    /**
     * Test for Method outputAssignmentGrades(String)
     */
    public void testOutputAssignmentGrades() {

        String quiz1 = "ASSIGNMENT_GRADES\nQuiz 1\n30.0\n6.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t20.0\n" +
                "FBluver\t30.0\n" +
                "hgwrtz98\t12.0\n" +
                "lou.car\t27.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Quiz 1") +
                "\nMedian\t" + mgb1.median("Quiz 1") + 
                "\nMax\t" + mgb1.max("Quiz 1") + 
                "\nMin\t" + mgb1.min("Quiz 1");
        
        assertEquals(mgb1.outputAssignmentGrades("Quiz 1"), quiz1);
        
        String quiz2 = "ASSIGNMENT_GRADES\nQuiz 2\n20.0\n6.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t15.0\n" +
                "FBluver\t20.0\n" +
                "hgwrtz98\t4.0\n" +
                "lou.car\t18.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Quiz 2") +
                "\nMedian\t" + mgb1.median("Quiz 2") + 
                "\nMax\t" + mgb1.max("Quiz 2") + 
                "\nMin\t" + mgb1.min("Quiz 2");
        
        assertEquals(mgb1.outputAssignmentGrades("Quiz 2"), quiz2);
        

        String quiz3 = "ASSIGNMENT_GRADES\nQuiz 3\n10.0\n6.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t2.0\n" +
                "FBluver\t10.0\n" +
                "hgwrtz98\t10.0\n" +
                "lou.car\t13.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Quiz 3") +
                "\nMedian\t" + mgb1.median("Quiz 3") + 
                "\nMax\t" + mgb1.max("Quiz 3") + 
                "\nMin\t" + mgb1.min("Quiz 3");
        
        assertEquals(mgb1.outputAssignmentGrades("Quiz 3"), quiz3);
        

        String quiz4 = "ASSIGNMENT_GRADES\nQuiz 4\n15.0\n6.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t12.0\n" +
                "FBluver\t15.0\n" +
                "hgwrtz98\t19.0\n" +
                "lou.car\t10.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Quiz 4") +
                "\nMedian\t" + mgb1.median("Quiz 4") + 
                "\nMax\t" + mgb1.max("Quiz 4") + 
                "\nMin\t" + mgb1.min("Quiz 4");
        
        assertEquals(mgb1.outputAssignmentGrades("Quiz 4"), quiz4);
        

        String assignment1 =
            "ASSIGNMENT_GRADES\nAssignment 1\n34.0\n8.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t17.0\n" +
                "FBluver\t34.0\n" +
                "hgwrtz98\t19.0\n" +
                "lou.car\t23.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Assignment 1") +
                "\nMedian\t" + mgb1.median("Assignment 1") + 
                "\nMax\t" + mgb1.max("Assignment 1") + 
                "\nMin\t" + mgb1.min("Assignment 1");
        
        assertEquals(mgb1.outputAssignmentGrades("Assignment 1"), assignment1);
        

        String assignment2 =
            "ASSIGNMENT_GRADES\nAssignment 2\n23.0\n8.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t16.0\n" +
                "FBluver\t23.0\n" +
                "hgwrtz98\t20.0\n" +
                "lou.car\t19.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Assignment 2") +
                "\nMedian\t" + mgb1.median("Assignment 2") + 
                "\nMax\t" + mgb1.max("Assignment 2") + 
                "\nMin\t" + mgb1.min("Assignment 2");
        
        assertEquals(mgb1.outputAssignmentGrades("Assignment 2"), assignment2);
        

        String test1 = "ASSIGNMENT_GRADES\nTest 1\n100.0\n20.0\n----\n" + 
                "as15\t0.0\n" +
                "cd14\t43.0\n" +
                "FBluver\t100.0\n" +
                "hgwrtz98\t88.0\n" +
                "lou.car\t85.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Test 1") +
                "\nMedian\t" + mgb1.median("Test 1") + 
                "\nMax\t" + mgb1.max("Test 1") + 
                "\nMin\t" + mgb1.min("Test 1");
        
        assertEquals(mgb1.outputAssignmentGrades("Test 1"), test1);
        

        String test2 = "ASSIGNMENT_GRADES\nTest 2\n100.0\n20.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t57.0\n" +
                "FBluver\t100.0\n" +
                "hgwrtz98\t34.0\n" +
                "lou.car\t71.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Test 2") +
                "\nMedian\t" + mgb1.median("Test 2") + 
                "\nMax\t" + mgb1.max("Test 2") + 
                "\nMin\t" + mgb1.min("Test 2");
        
        assertEquals(mgb1.outputAssignmentGrades("Test 2"), test2);
        

        String test3 = "ASSIGNMENT_GRADES\nTest 3\n100.0\n20.0\n----\n" +
                "as15\t0.0\n" +
                "cd14\t94.0\n" +
                "FBluver\t100.0\n" +
                "hgwrtz98\t77.0\n" +
                "lou.car\t15.0\n" +
                "----\nSTATS\n" +
                "Average\t" + mgb1.average("Test 3") +
                "\nMedian\t" + mgb1.median("Test 3") + 
                "\nMax\t" + mgb1.max("Test 3") + 
                "\nMin\t" + mgb1.min("Test 3");
        
        assertEquals(mgb1.outputAssignmentGrades("Test 3"), test3);
        
        try {
            mgb1.outputAssignmentGrades("In da gadda da vida");
            fail();
        }
        catch (NoSuchElementException e) {
            assertEquals(e.getClass(), NoSuchElementException.class);
        }

    }

    /**
     * Test for Method outputGradebook()
     */
    public void testOutputGradebook() {

        String test = "GRADEBOOK\n\t\t\t\t" +
                "\t" + "Quiz 1" +
                "\t" + "Quiz 2" +
                "\t" + "Quiz 3" +
                "\t" + "Quiz 4" +
                "\t" + "Assignment 1" +
                "\t" + "Assignment 2" +
                "\t" + "Test 1" +
                "\t" + "Test 2" +
                "\t" + "Test 3" +
                "\n\t\t\t\t" +
                "\t" + 30.0 +
                "\t" + 20.0 +
                "\t" + 10.0 +
                "\t" + 15.0 +
                "\t" + 34.0 +
                "\t" + 23.0 +
                "\t" + 100.0 +
                "\t" + 100.0 +
                "\t" + 100.0 +
                "\n\t\t\t\t" +
                "\t" + 6.0 +
                "\t" + 6.0 +
                "\t" + 6.0 +
                "\t" + 6.0 +
                "\t" + 8.0 +
                "\t" + 8.0 +
                "\t" + 20.0 +
                "\t" + 20.0 +
                "\t" + 20.0 +
                "\nas15\t" +
                "Astrid\tScarlett\tPhyllis\t2015\t" +
                "0.0\t" +
                "0.0\t" +
                "0.0\t" +
                "0.0\t" +
                "0.0\t" +
                "0.0\t" +
                "0.0\t" +
                "0.0\t" +
                "0.0" +
                "\ncd14\t" +
                "Caseo\tDent\tNorphyllis\t2014\t" +
                "20.0\t" +
                "15.0\t" +
                "2.0\t" +
                "12.0\t" +
                "17.0\t" +
                "16.0\t" +
                "43.0\t" +
                "57.0\t" +
                "94.0" +
                "\nFBluver\t" +
                "Norwood\tBilder\tTim Snoo Roman\t2015\t" +
                "30.0\t" +
                "20.0\t" +
                "10.0\t" +
                "15.0\t" +
                "34.0\t" +
                "23.0\t" +
                "100.0\t" +
                "100.0\t" +
                "100.0" +
                "\nhgwrtz98\t" +
                "Abby\tGranger\tComi Scans\t1999\t" +
                "12.0\t" +
                "4.0\t" +
                "10.0\t" +
                "19.0\t" +
                "19.0\t" +
                "20.0\t" +
                "88.0\t" +
                "34.0\t" +
                "77.0" +
                "\nlou.car\t" +
                "Louis\tCarb\tComi Scans\t2014\t" +
                "27.0\t" +
                "18.0\t" +
                "13.0\t" +
                "10.0\t" +
                "23.0\t" +
                "19.0\t" +
                "85.0\t" +
                "71.0\t" +
                "15.0";
        
        assertEquals(test, mgb1.outputGradebook());    
        
    }
    
    /**
     * Test the Method listStudents
     */
    public void testListStudents() {
        ArrayList<String> studentStrings = mgb1.listStudents();
        assertEquals(studentStrings.get(0), 
                "Astrid Scarlett (as15), 2015\n\tAdvisor: Phyllis");
        assertEquals(studentStrings.get(1), 
                "Caseo Dent (cd14), 2014\n\tAdvisor: Norphyllis");
        assertEquals(studentStrings.get(2), 
                "Norwood Bilder (FBluver), 2015\n\tAdvisor: Tim Snoo Roman");
        assertEquals(studentStrings.get(3), 
                "Abby Granger (hgwrtz98), 1999\n\tAdvisor: Comi Scans");
        assertEquals(studentStrings.get(4), 
                "Louis Carb (lou.car), 2014\n\tAdvisor: Comi Scans");
    }
    
    /**
     * Test the Method listAssignments
     */
    public void testListAssignments() {
        ArrayList<String> assignmentStrings = mgb1.listAssignments();
        assertEquals(assignmentStrings.get(0), "Quiz 1, 30.0, 6.0%");
        assertEquals(assignmentStrings.get(1), "Quiz 2, 20.0, 6.0%");
        assertEquals(assignmentStrings.get(2), "Quiz 3, 10.0, 6.0%");
        assertEquals(assignmentStrings.get(3), "Quiz 4, 15.0, 6.0%");
        assertEquals(assignmentStrings.get(4), "Assignment 1, 34.0, 8.0%");
        assertEquals(assignmentStrings.get(5), "Assignment 2, 23.0, 8.0%");
        assertEquals(assignmentStrings.get(6), "Test 1, 100.0, 20.0%");
        assertEquals(assignmentStrings.get(7), "Test 2, 100.0, 20.0%");
        assertEquals(assignmentStrings.get(8), "Test 3, 100.0, 20.0%");
    }

    /**
     * Test for Method initializeWithFile(String)
     */
    public void testInitializeWithFile() {

        

    }

    /**
     * Test for Method initializeWithString(String)
     */
    public void testInitializeWithString() {
        
        
        
    }

    /**
     * Test for Method processFile(String)
     */
    public void testProcessFile() {

    }

    /**
     * Test for Method processString(String)
     */
    public void testProcessString() {

    }

}
