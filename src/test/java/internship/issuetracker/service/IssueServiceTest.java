package internship.issuetracker.service;

import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private NewIssueDTO createIssueDTO(String title, String content, IssueState state, List<Long> idList) {
        Issue simpleIssue = new Issue();
        simpleIssue.setContent(content);
        simpleIssue.setDate(new Date());
        simpleIssue.setTitle(title);
        simpleIssue.setState(state);

        NewIssueDTO issueDto = new NewIssueDTO();
        issueDto.setIssue(simpleIssue);
        issueDto.setLabelIdList(idList);
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

    /**
     * @author AUGUSTIN IssueService.
     */
    @Test
    public void testCreateIssueFromIssueDTO() {
        String content = "content";

        NewIssueDTO issueDto = createIssueDTO("title", content, IssueState.OPEN, new ArrayList<Long>());
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
        IssueState state = IssueState.REOPENED;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>());
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

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>());
        User user = createUser("nicu3", "name", "n@mail3", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.updateIssueState(issueId, IssueState.REOPENED);

        Issue issue = issueService.getIssueById(issueId);

        assertEquals(IssueState.REOPENED, issue.getState());
    }

    /**
     * @author AUGUSTIN IssueService.
     */
    @Test
    public void testChangeStateOfIssue() {
        String content = "content4";
        String title = "title4";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>());
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

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>());
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

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>());
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
}
