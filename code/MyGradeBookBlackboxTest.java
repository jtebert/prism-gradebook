import junit.framework.TestCase;

import gradebook.MyGradeBook;


/**
 * Black Box Tests For MyGradeBook
 * @author 
 *
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
        
        mgb2 = MyGradeBook.initializeWithFile(example_file);
        
        mgb3 = MyGradeBook.initializeWithString(example_string);
        
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
        mgb1.addStudent("pyroh8r",
                "Silvia",
                "Blaze",
                "Phyllis",
                2015);
        mgb1.addStudent("TitanSlayerTheWarriorKing",
                "Gloria",
                "Scott",
                "Phyllis",
                2015);
        mgb1.addStudent("FBluver",
                "Norwood",
                "Bilder",
                "Tim Snoo Roman",
                2015);
        mgb1.addStudent("dabadabadabada",
                "Charlie",
                "Milverton",
                "Phyllis",
                2016);
        mgb1.addStudent("hgwrtz98",
                "Abby",
                "Granger",
                "Comi Scans",
                1999);
        mgb1.addStudent("idrmdrmftmgnb",
                "Francis",
                "Carfox",
                "Tim Snoo Roman",
                2015);
        mgb1.addStudent("ruafreuduvthdrk",
                "Dan",
                "Sigmund",
                "Comi Scans",
                2016);
        
        mgb1.addAssignment("Quiz 1", 10.0, 7.0);
        mgb1.addAssignment("Assignment 1", 4.0, 2.0);
        mgb1.addAssignment("Assignment 2", 3.0, 2.0);
        mgb1.addAssignment("Quiz 2", 10.0, 6.0);
        mgb1.addAssignment("Quiz 3", 10.0, 6.0);
        mgb1.addAssignment("Test 1", 100.0, 20.0);
        mgb1.addAssignment("Test 2", 100.0, 20.0);
        mgb1.addAssignment("Quiz 4", 10.0, 5.0);
        mgb1.addAssignment("Assignment 3", 7.0, 2.0);
        mgb1.addAssignment("Test 3", 100.0, 20.0);
        
    }
    
    
    /**
     * Test for Method addStudent(String, String, String, String, int)
     */
    public void testAddStudent() {
        
        Exception check1;
        
        boolean check2 = true;
        
        Student t = new Student("hgwrtz98",
                "Abby",
                "Granger",
                "Comi Scans",
                1999);
        
        Student u = new Student("rufus",
                "RU",
                "fus",
                "Phyllis",
                2016);
        
        try {
            mgb1.addStudent(t.username,
                    t.firstName,
                    t.lastName,
                    t.advisor,
                    t.gradYear);
        } catch (RuntimeException e) {
            check1 = e;
        } finally {
            assertEquals(check1, new RuntimeException("Student Exists"));
        }
        
        try {
            mgb1.addStudent(u.username,
                    u.firstName,
                    u.lastName,
                    u.advisor,
                    u.gradYear);
        } catch (Exception e) {
            check2 = false;
        } finally {
            assertTrue(check2);
        }
        
    }
    
    /**
     * Test for Method removeStudent(Student)
     */
    public void testRemoveStudent() {
        
        Exception check1;
        
        boolean check2 = true;
        
        Student t = new Student("ruafreuduvthdrk",
                "Dan",
                "Sigmund",
                "Comi Scans",
                2016);
        
        Student u = new Student("ruafreuduv",
                "Dan",
                "Sigmund",
                "Comi Scans",
                2016);
        
        try {
            mgb1.removeStudent(u);
        } catch (RuntimeException e) {
            check1 = e;
        } finally {
            assertEquals(check1, new RuntimeException("No Such Student"));
        }
        
        try {
            mgb1.removeStudent(t);
        } catch (Exception e) {
            check2 = false;
        } finally {
            assertTrue(check2);
        }
    }
    
    /**
     * Test for Method addAssignment(String, Double, Double)
     */
    public void testAddAssignment() {

        Exception check1;
        
        boolean check2 = true;
        
        Assignment t = new Assignment("Test 3", 100.0, 20.0);
        
        Assignment u = new Assignment("Test 233", 100.0, 20.0);
        
        try {
            mgb1.addAssignment(t.name,
                    t.totalPoints,
                    t.percentGrade);
        } catch (RuntimeException e) {
            check1 = e;
        } finally {
            assertEquals(check1, new RuntimeException("Assignment Exists"));
        }
        
        try {
            mgb1.addAssignment(u.name,
                    u.totalPoints,
                    u.percentGrade);
        } catch (Exception e) {
            check2 = false;
        } finally {
            assertTrue(check2);
        }
        
    }
    
    /**
     * Test for Method removeAssignment(Assignment)
     */
    public void testRemoveAssignment() {
Exception check1;
        
        boolean check2 = true;
        
        Assignment t = new Assignment("Test 3", 100.0, 20.0);
        
        Assignment u = new Assignment("Test 32", 100.0, 20.0);
        
        try {
            mgb1.removeAssignment(u);
        } catch (RuntimeException e) {
            check1 = e;
        } finally {
            assertEquals(check1, new RuntimeException("No Such Assignment"));
        }
        
        try {
            mgb1.removeAssignment(t);
        } catch (Exception e) {
            check2 = false;
        } finally {
            assertTrue(check2);
        }
    }
    
    /**
     * Test for Method changeGrade(String, String, Double)
     */
    public void testChangeGrade() {
        
    }
    
    /**
     * Test for Method average(String)
     */
    public void testAverage() {
        
    }    
    
    /**
     * Test for Method median(String)
     */
    public void testMedian() {
        
    }
    
    /**
     * Test for Method min(String)
     */
    public void testMin() {
        
    }
    
    /**
     * Test for Method max(String)
     */
    public void testMax() {
        
    }
    
    /**
     * Test for Method currentGrade(String)
     */
    public void testCurrentGrade() {
        
    }
    
    /**
     * Test for Method currentGrades()
     */
    public void testCurrentGrades() {
        
    }
    
    /**
     * Test for Method assignmentGrade(String, String)
     */
    public void testAssignmentGrade() {
        
    }
    
    /**
     * Test for Method outputCurrentGrades()
     */
    public void testOutputCurrentGrades() {
        
    }
    
    /**
     * Test for Method outputStudentGrades(String)
     */
    public void testOutputStudentGrades() {
        
    }
    
    /**
     * Test for Method outputAssignmentGrades(String)
     */
    public void testOutputAssignmentGrades() {
        
    }
    
    /**
     * Test for Method outputGradebook()
     */
    public void testOutputGradebook() {
        
    }
    
    /**
     * Test for Method initialize()
     */
    public void testInitialize() {
        
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
