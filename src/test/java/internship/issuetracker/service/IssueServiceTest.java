package internship.issuetracker.service;

import internship.issuetracker.dto.IssueDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueAttachment;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.UploadedFile;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.FilterResult;
import internship.issuetracker.filter.QueryFilter;
import internship.issuetracker.order.QueryOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
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

    @Autowired
    private IssueService issueService;

    /**
     * Helper method for creating a user
     *
     * @author AUGUSTIN
     * @param username
     * @param name
     * @param email
     * @param password
     * @return
     */
    private User createUser(String username, String name, String email, String password) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setName(name);
        userDTO.setPassword(password);
        userDTO.setUsername(username);

        userService.registerUser(userDTO);

        User user = userService.getUserByEmail(email);
        return user;
    }

    /**
     * Helper method for creating a NewIssueDTO with no id
     *
     * @author AUGUSTIN
     * @param title
     * @param content
     * @param state
     * @param idList
     * @return
     */
    private NewIssueDTO createIssueDTO(String title, String content, IssueState state, List<Long> idList, List<Long> attachments) {
        Issue simpleIssue = new Issue();
        simpleIssue.setContent(content);
        simpleIssue.setDate(new Date());
        simpleIssue.setTitle(title);
        simpleIssue.setState(state);

        NewIssueDTO issueDto = new NewIssueDTO();
        issueDto.setIssue(simpleIssue);
        issueDto.setLabelIdList(idList);
        issueDto.setAttachments(attachments);
        return issueDto;
    }

    /**
     * Helper method for creating a comment with no id
     *
     * @param content
     * @return
     */
    private Comment createSimpleComment(String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        return comment;
    }

    private Label createlabel(String color, String name) {
        Label label = new Label();
        label.setColor(color);
        label.setName(name);
        return label;
    }

    private UploadedFile createUploadedFile(String originalName, String targetName){
        UploadedFile uFile =  new UploadedFile();
        uFile.setOriginalName(originalName);
        uFile.setTargetName(targetName);
        uFile.setMimeType("image/jpeg");
        return uFile;
    }
    
    private IssueAttachment createAttachment(UploadedFile uFile,Issue issue){
        IssueAttachment attachment =  new IssueAttachment();
        attachment.setAttachment(uFile);
        attachment.setIssue(issue);
        return attachment;
    }
    /**
     * @author AUGUSTIN IssueService.
     */
    @Test
    public void testCreateIssueFromIssueDTO() {
        String content = "content";

        NewIssueDTO issueDto = createIssueDTO("title", content, IssueState.OPEN, new ArrayList<Long>(),new ArrayList<Long>());
        User user = createUser("nicu", "name", "n@mail", "12345");

        issueService.createIssueFromIssueDTO(issueDto, user);
        
        Issue issue = issueService.getIssueById(issueDto.getIssue().getId());

        assertEquals(issue.getContent(), content);
    }

    /**
     * @author AUGUSTIN
     */
    @Test
    public void testGetIssueById() {
        String content = "content2";
        String title = "title2";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(),new ArrayList<Long>());
        User user = createUser("nicu2", "name", "n@mail2", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        Issue issue = issueService.getIssueById(issueId);

        assertArrayEquals(new Object[]{title, content, state},
                new Object[]{issue.getTitle(), issue.getContent(), issue.getState()});
    }

    /**
     * @author AUGUSTIN
     */
    @Test
    public void testUpdateIssueState() {
        String content = "content3";
        String title = "title3";
        IssueState state = IssueState.CLOSED;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(),new ArrayList<Long>());
        User user = createUser("nicu3", "name", "n@mail3", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.updateIssueState(issueId, IssueState.CLOSED);

        Issue issue = issueService.getIssueById(issueId);

        assertEquals(IssueState.CLOSED, issue.getState());
    }

    /**
     * @author AUGUSTIN IssueService.
     */
    @Test
    public void testChangeStateOfIssue() {
        String content = "content4";
        String title = "title4";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(),new ArrayList<Long>());
        User user = createUser("nicu4", "name", "n@mail4", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.updateIssueState(issueId, IssueState.CLOSED);

        Issue issue = issueService.getIssueById(issueId);

        assertEquals(IssueState.CLOSED, issue.getState());
    }

    /**
     * Test of addComment method, of class IssueService.
     */
    @Test
    public void testAddComment() {
        String content = "content5";
        String title = "title5";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(),new ArrayList<Long>());
        User user = createUser("nicu5", "name", "n@mail5", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        Comment comment = issueService.addComment(user, issueId, createSimpleComment("comment 1"));

        assertEquals("comment 1", comment.getContent());

    }

    /**
     * Test of getCommentsByIssueId method, of class IssueService.
     */
    @Test
    public void testGetCommentsByIssueId() {
        String content = "content6";
        String title = "title56";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(),new ArrayList<Long>());
        User user = createUser("nicu6", "name", "n@mail6", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.addComment(user, issueId, createSimpleComment("comment 1"));
        issueService.addComment(user, issueId, createSimpleComment("comment 2"));
        issueService.addComment(user, issueId, createSimpleComment("comment 3"));
        List<Comment> commentList = issueService.getCommentsByIssueId(issueService.getIssueById(issueId));

        assertEquals(commentList.size(), 3);
    }

    /**
     * Test of createLabel method, of class IssueService.
     */
    @Test
    public void testCreateLabel() {
        String labelName = "yupi";
        String labelColor = "#FF3300";

        Label label = createlabel(labelColor, labelName);

        assertNotNull(label);
    }

    /**
     * Test of labelExists method, of class IssueService.
     */
    @Test
    public void testLabelExists() {
        String labelName = "flash";
        String labelColor = "#FFFF00";

        Label label = createlabel(labelColor, labelName);
        issueService.createLabel(label);

        assertEquals(true, issueService.labelExists(labelName));
    }

    /**
     * Test of updateAssignee method, of class IssueService.
     */
    @Test
    public void testUpdateAssignee() {
        System.out.println("updateAssignee");
        User assignee = createUser("vero", "VERO", "VERO@mail6", "12345");
        User user = createUser("cosmi", "cosmi", "cosmi@mail6", "12345");
        String content = "content60";
        String title = "title60";
        IssueState state = IssueState.OPEN;
        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(),new ArrayList<Long>());
        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        
        issueService.updateAssignee(issueId, assignee, user);
        Issue issue = issueService.getIssueById(issueId);
        assertEquals(assignee.getId(),issue.getAssignee().getId());
    }

    /**
     * Test of getIssuesOrderedByDate method, of class IssueService.
     */
    @Test
    public void testGetIssuesOrderedByDate() {
        System.out.println("getIssuesOrderedByDate");
        User user = createUser("cosmina", "cosmina", "cosmina@mail6", "12345");
        String content = "content700";
        String title = "title700";
        String content2 = "content710";
        String title2 = "title710";
        String content20 = "content7100";
        String title20 = "title7100";
        IssueState state = IssueState.OPEN;
        NewIssueDTO issueDto1 = createIssueDTO(title, content, state, new ArrayList<Long>(),new ArrayList<Long>());
        NewIssueDTO issueDto2 = createIssueDTO(title2, content2, state, new ArrayList<Long>(),new ArrayList<Long>());
        NewIssueDTO issueDto3 = createIssueDTO(title20, content20, state, new ArrayList<Long>(),new ArrayList<Long>());
        Long id1 =issueService.createIssueFromIssueDTO(issueDto1, user);
        Long id2 =issueService.createIssueFromIssueDTO(issueDto2, user);
        Long id3 =issueService.createIssueFromIssueDTO(issueDto3, user);
        
        List<Issue> resultList = issueService.getIssuesOrderedByDate();
        boolean firstPosition = (id1.equals(resultList.get(0).getId()));
        boolean secondPosition = (id2.equals(resultList.get(1).getId()));
        boolean thirdPosition = (id3.equals(resultList.get(2).getId()));
        assertEquals(firstPosition&&secondPosition&&thirdPosition,true);
    }

    /**
     * Test of getLabelsByIssueId method, of class IssueService.
     */
    @Test
    public void testGetLabelsByIssue() {
        System.out.println("getLabelsByIssueId");
        User user = createUser("cocos", "cocos", "cocos@mail6", "12345");
        String content = "content64";
        String title = "title64";
        IssueState state = IssueState.OPEN;
        
        String labelName1 = "yupi";
        String labelName2 = "yupi2";
        String labelColor = "#FF3300";
        Label label1 = createlabel(labelColor, labelName1);
        Label label2 = createlabel(labelColor, labelName2);
        Label persistedLabel1 = issueService.createLabel(label1);
        Label persistedLabel2 = issueService.createLabel(label2);
        List<Long> labelsId =  new ArrayList<>();
        labelsId.add(persistedLabel1.getId());
        labelsId.add(persistedLabel2.getId());
        NewIssueDTO issueDto = createIssueDTO(title, content, state, labelsId ,new ArrayList<Long>());
        
        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        Issue issue =  issueService.getIssueById(issueId);
        List<Label> result = issueService.getLabelsByIssueId(issue);
        boolean firstPosition = (persistedLabel1.getId().equals(result.get(0).getId()));
        boolean secondPosition = (persistedLabel2.getId().equals(result.get(1).getId()));
        assertEquals(firstPosition&&secondPosition,true);
    }

    /**
     * Test of filterIssues method, of class IssueService.
     */
    @Ignore
    @Test
    public void testFilterIssues() {
        System.out.println("filterIssues");
        List<QueryFilter<Issue>> filters = null;
        QueryOrder order = null;
        Integer pageNumber = null;
        Integer itemsPerPage = null;
        IssueService instance = new IssueService();
        FilterResult expResult = null;
        FilterResult result = instance.filterIssues(filters, order, pageNumber, itemsPerPage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDTOsFromIssues method, of class IssueService.
     */
     @Ignore
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
     * Test of getLabelByName method, of class IssueService.
     */
     @Ignore
    @Test
    public void testGetLabelByName() {
        System.out.println("getLabelByName");
        String labelName = "";
        IssueService instance = new IssueService();
        Label expResult = null;
        Label result = instance.getLabelByName(labelName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeIssueLabels method, of class IssueService.
     */
     @Ignore
    @Test
    public void testRemoveIssueLabels() {
        System.out.println("removeIssueLabels");
        Long labelId = null;
        IssueService instance = new IssueService();
        instance.removeIssueLabels(labelId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeLabel method, of class IssueService.
     */
     @Ignore
    @Test
    public void testRemoveLabel() {
        System.out.println("removeLabel");
        Long labelId = null;
        IssueService instance = new IssueService();
        boolean expResult = false;
        boolean result = instance.removeLabel(labelId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLabel method, of class IssueService.
     */
     @Ignore
    @Test
    public void testUpdateLabel() {
        System.out.println("updateLabel");
        Label label = null;
        IssueService instance = new IssueService();
        Label expResult = null;
        Label result = instance.updateLabel(label);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMissedComments method, of class IssueService.
     */
     @Ignore
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

    /**
     * Test of findUsersIssuesOwnersByNamePrefix method, of class IssueService.
     */
     @Ignore
    @Test
    public void testFindUsersIssuesOwnersByNamePrefix() {
        System.out.println("findUsersIssuesOwnersByNamePrefix");
        String usernamePrefix = "";
        IssueService instance = new IssueService();
        List<User> expResult = null;
        List<User> result = instance.findUsersIssuesOwnersByNamePrefix(usernamePrefix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUsersAssigneesByNamePrefix method, of class IssueService.
     */
     @Ignore
    @Test
    public void testFindUsersAssigneesByNamePrefix() {
        System.out.println("findUsersAssigneesByNamePrefix");
        String usernamePrefix = "";
        IssueService instance = new IssueService();
        List<User> expResult = null;
        List<User> result = instance.findUsersAssigneesByNamePrefix(usernamePrefix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editIssueFromIssueDTO method, of class IssueService.
     */
     @Ignore
    @Test
    public void testEditIssueFromIssueDTO() {
        System.out.println("editIssueFromIssueDTO");
        NewIssueDTO issueDTO = null;
        User currentUser = null;
        IssueService instance = new IssueService();
        Issue expResult = null;
        Issue result = instance.editIssueFromIssueDTO(issueDTO, currentUser);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllLabelsFromAnIssue method, of class IssueService.
     */
     @Ignore
    @Test
    public void testRemoveAllLabelsFromAnIssue() {
        System.out.println("removeAllLabelsFromAnIssue");
        Long issueId = null;
        IssueService instance = new IssueService();
        instance.removeAllLabelsFromAnIssue(issueId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllAttachmentsFromAnIssue method, of class IssueService.
     */
     @Ignore
    @Test
    public void testRemoveAllAttachmentsFromAnIssue() {
        System.out.println("removeAllAttachmentsFromAnIssue");
        Long issueId = null;
        IssueService instance = new IssueService();
        instance.removeAllAttachmentsFromAnIssue(issueId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
