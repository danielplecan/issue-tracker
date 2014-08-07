/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author atataru
 */
public class IssueStateTest {
    
    public IssueStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFromString() {
        System.out.println("fromString");
        String issueState = "";
        IssueState expResult = null;
        IssueState result = IssueState.fromString(issueState);
        assertNull(result);
    }
    
    @Test
    public void testFromStringOpen() {
        System.out.println("fromString");
        String issueState = "open";
        IssueState expResult = IssueState.OPEN;
        IssueState result = IssueState.fromString(issueState);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFromStringClose() {
        System.out.println("fromString");
        String issueState = "closed";
        IssueState expResult = IssueState.CLOSED;
        IssueState result = IssueState.fromString(issueState);
        assertEquals(expResult, result);
    }
    
}
