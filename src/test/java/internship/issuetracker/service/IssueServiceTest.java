package internship.issuetracker.service;

import internship.issuetracker.dto.IssueDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.FilterResult;
import internship.issuetracker.filter.QueryFilter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author dplecan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
public class IssueServiceTest {
    
    @Autowired
    private UserService userService;

    @Test
    public void test_X() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("a@a");
        userDTO.setName("zaname");
        userDTO.setPassword("ohohohoh");
        userDTO.setUsername("zaUserName");
        
        userService.registerUser(userDTO);
        
        User user = userService.getUserByEmail("a@a");
        Assert.assertEquals(userDTO.getUsername(), user.getUsername());
    }

    /**
     * Test of createIssueFromIssueDTO method, of class IssueService.
     */
    @Test
    public void testCreateIssueFromIssueDTO() {
        System.out.println("createIssueFromIssueDTO");
        NewIssueDTO issueDto = null;
        User owner = null;
        IssueService instance = new IssueService();
        long expResult = 0L;
        long result = instance.createIssueFromIssueDTO(issueDto, owner);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIssueById method, of class IssueService.
     */
    @Test
    public void testGetIssueById() {
        System.out.println("getIssueById");
        Long id = null;
        IssueService instance = new IssueService();
        Issue expResult = null;
        Issue result = instance.getIssueById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateIssueState method, of class IssueService.
     */
    @Test
    public void testUpdateIssueState() {
        System.out.println("updateIssueState");
        long issueId = 0L;
        IssueState newState = null;
        IssueService instance = new IssueService();
        boolean expResult = false;
        boolean result = instance.updateIssueState(issueId, newState);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeStateOfIssue method, of class IssueService.
     */
    @Test
    public void testChangeStateOfIssue() {
        System.out.println("changeStateOfIssue");
        Long issueId = null;
        IssueState newState = null;
        IssueService instance = new IssueService();
        boolean expResult = false;
        boolean result = instance.changeStateOfIssue(issueId, newState);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIssues method, of class IssueService.
     */
    @Test
    public void testGetIssues() {
        System.out.println("getIssues");
        IssueService instance = new IssueService();
        List<Issue> expResult = null;
        List<Issue> result = instance.getIssues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIssuesOrderedByDate method, of class IssueService.
     */
    @Test
    public void testGetIssuesOrderedByDate() {
        System.out.println("getIssuesOrderedByDate");
        IssueService instance = new IssueService();
        List<Issue> expResult = null;
        List<Issue> result = instance.getIssuesOrderedByDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addComment method, of class IssueService.
     */
    @Test
    public void testAddComment() {
        System.out.println("addComment");
        User author = null;
        Long issueId = null;
        Comment comment = null;
        IssueService instance = new IssueService();
        Comment expResult = null;
        Comment result = instance.addComment(author, issueId, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommentsByIssueId method, of class IssueService.
     */
    @Test
    public void testGetCommentsByIssueId() {
        System.out.println("getCommentsByIssueId");
        Issue issue = null;
        IssueService instance = new IssueService();
        List<Comment> expResult = null;
        List<Comment> result = instance.getCommentsByIssueId(issue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelsByIssueId method, of class IssueService.
     */
    @Test
    public void testGetLabelsByIssueId() {
        System.out.println("getLabelsByIssueId");
        Issue issue = null;
        IssueService instance = new IssueService();
        List<Label> expResult = null;
        List<Label> result = instance.getLabelsByIssueId(issue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllLabels method, of class IssueService.
     */
    @Test
    public void testGetAllLabels() {
        System.out.println("getAllLabels");
        IssueService instance = new IssueService();
        List<Label> expResult = null;
        List<Label> result = instance.getAllLabels();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterIssues method, of class IssueService.
     */
    @Test
    public void testFilterIssues() {
        System.out.println("filterIssues");
        List<QueryFilter<Issue>> filters = null;
        Integer pageNumber = null;
        Integer itemsPerPage = null;
        IssueService instance = new IssueService();
        FilterResult expResult = null;
        FilterResult result = instance.filterIssues(filters, pageNumber, itemsPerPage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDTOsFromIssues method, of class IssueService.
     */
    @Test
    public void testGetDTOsFromIssues() {
        System.out.println("getDTOsFromIssues");
        List<Issue> issues = null;
        IssueService instance = new IssueService();
        List<IssueDTO> expResult = null;
        List<IssueDTO> result = instance.getDTOsFromIssues(issues);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLabel method, of class IssueService.
     */
    @Test
    public void testCreateLabel() {
        System.out.println("createLabel");
        Label label = null;
        IssueService instance = new IssueService();
        Label expResult = null;
        Label result = instance.createLabel(label);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of labelExists method, of class IssueService.
     */
    @Test
    public void testLabelExists() {
        System.out.println("labelExists");
        String labelName = "";
        IssueService instance = new IssueService();
        boolean expResult = false;
        boolean result = instance.labelExists(labelName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMissedComments method, of class IssueService.
     */
    @Test
    public void testGetMissedComments() {
        System.out.println("getMissedComments");
        long issueId = 0L;
        long commentId = 0L;
        IssueService instance = new IssueService();
        List<Comment> expResult = null;
        List<Comment> result = instance.getMissedComments(issueId, commentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
