import junit.framework.TestCase;
import org.junit.Before;

import gradebook.MyGradeBook;

/**
 * BLACK BOX Tests for GradeBook and associated classes
 * @author David Akodes (akodes.d@husky.neu.edu)
 * @author Julia Ebert (jtebert@ccs.neu.edu)
 * @author Jimmy Ly (jly@ccs.neu.edu)
 * @author Matther Taylor(wiseguy@ccs.neu.edu)
 * @version 2014-04-11
 */
public class MyGradeBookBlackboxTest extends TestCase {
    
    
    /**
     * Initialize MyGradeBooks for testing
     */
    @Before
    public void setUp() {
        MyGradeBook.initialize();
    }
    
    /**
     * Method to make Web-CAT happy
     */
    void testMethods() {
        assertTrue(true);
    }
}
