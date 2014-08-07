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

    private NewIssueDTO createIssueDTO(Issue issue, List<Long> idList, List<Long> attachments) {
        NewIssueDTO issueDto = new NewIssueDTO();
        issueDto.setIssue(issue);
        issueDto.setLabelIdList(idList);
        issueDto.setAttachments(attachments);
        return issueDto;
    }
    
    private boolean equalsIssues(Issue i1, Issue i2) {
        return (i1.getId().equals(i2.getId()) && i1.getTitle().equals(i2.getTitle())
                && i1.getOwner().getId().equals(i2.getOwner().getId())
                && i1.getContent().equals(i2.getContent()));
    }

    private boolean equalsListsOfIssues(List<Issue> firstList, List<Issue> secondList) {
        if (firstList.size() != secondList.size()) {
            return false;
        }
        for (int i = 0; i < firstList.size(); i++) {
            if (!equalsIssues(firstList.get(i), secondList.get(i))) {
                return false;
            }
        }
        return true;
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

    private UploadedFile createUploadedFile(String originalName, String targetName) {
        UploadedFile uFile = new UploadedFile();
        uFile.setOriginalName(originalName);
        uFile.setTargetName(targetName);
        uFile.setMimeType("image/jpeg");
        return uFile;
    }

    private IssueAttachment createAttachment(UploadedFile uFile, Issue issue) {
        IssueAttachment attachment = new IssueAttachment();
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

        NewIssueDTO issueDto = createIssueDTO("title", content, IssueState.OPEN, new ArrayList<Long>(), new ArrayList<Long>());
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

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());
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

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());
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

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());
        User user = createUser("nicu4", "name", "n@mail4", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.updateIssueState(issueId, IssueState.CLOSED);

        Issue issue = issueService.getIssueById(issueId);

        assertEquals(IssueState.CLOSED, issue.getState());
    }

    @Test
    public void testAddComment() {
        String content = "content5";
        String title = "title5";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());
        User user = createUser("nicu5", "name", "n@mail5", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        Comment comment = issueService.addComment(user, issueId, createSimpleComment("comment 1"));

        assertEquals("comment 1", comment.getContent());

    }

    @Test
    public void testGetCommentsByIssueId() {
        String content = "content6";
        String title = "title56";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());
        User user = createUser("nicu6", "name", "n@mail6", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.addComment(user, issueId, createSimpleComment("comment 1"));
        issueService.addComment(user, issueId, createSimpleComment("comment 2"));
        issueService.addComment(user, issueId, createSimpleComment("comment 3"));
        List<Comment> commentList = issueService.getCommentsByIssueId(issueService.getIssueById(issueId));

        assertEquals(commentList.size(), 3);
    }

    @Test
    public void testCreateLabel() {
        String labelName = "yupi";
        String labelColor = "#FF3300";

        Label label = createlabel(labelColor, labelName);

        assertNotNull(label);
    }

    @Test
    public void testLabelExists() {
        String labelName = "flash";
        String labelColor = "#FFFF00";

        Label label = createlabel(labelColor, labelName);
        issueService.createLabel(label);

        assertEquals(true, issueService.labelExists(labelName));
    }

    @Test
    public void testUpdateAssignee() {
        System.out.println("updateAssignee");
        User assignee = createUser("vero", "VERO", "VERO@mail6", "12345");
        User user = createUser("cosmi", "cosmi", "cosmi@mail6", "12345");
        String content = "content60";
        String title = "title60";
        IssueState state = IssueState.OPEN;
        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());
        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.updateAssignee(issueId, assignee, user);
        Issue issue = issueService.getIssueById(issueId);
        assertEquals(assignee.getId(), issue.getAssignee().getId());
    }

    @Test
    public void testUpdateAssigneeFalse() {
        System.out.println("updateAssignee");
        User assignee = createUser("vero0", "VERO", "VERO0@mail6", "12345");
        User user = createUser("cosmi1", "cosmi", "cosmi1@mail6", "12345");

        boolean result= issueService.updateAssignee(134732, assignee, user);
        assertEquals(false, result);
    }
    
    @Test
    public void testGetIssuesOrderedByDate() {

        User user = createUser("cosmina", "cosmina", "cosmina@mail6", "12345");
        IssueState state = IssueState.OPEN;
        
        Issue issue1 =  new Issue();
        issue1.setTitle("title00");
        issue1.setDate(new Date(12345234L));
        issue1.setUpdateDate(new Date(12345321L));
        issue1.setState(state);
        
        Issue issue2 =  new Issue();
        issue2.setTitle("title001");
        issue2.setDate(new Date(12345235L));
        issue2.setUpdateDate(new Date(12345321L));
        issue2.setState(state);
        
        Issue issue3 =  new Issue();
        issue3.setTitle("title002");
        issue3.setDate(new Date(12345236L));
        issue3.setUpdateDate(new Date(12345321L));
        issue3.setState(state);
        
        NewIssueDTO issueDto1 = createIssueDTO(issue1, new ArrayList<Long>(), new ArrayList<Long>());
        NewIssueDTO issueDto2 = createIssueDTO(issue2, new ArrayList<Long>(), new ArrayList<Long>());
        NewIssueDTO issueDto3 = createIssueDTO(issue3, new ArrayList<Long>(), new ArrayList<Long>());
        Long id1 = issueService.createIssueFromIssueDTO(issueDto1, user);
        Long id2 = issueService.createIssueFromIssueDTO(issueDto2, user);
        Long id3 = issueService.createIssueFromIssueDTO(issueDto3, user);

        List<Issue> resultList = issueService.getIssuesOrderedByDate();
        boolean firstPosition = (id1.equals(resultList.get(0).getId()));
        boolean secondPosition = (id2.equals(resultList.get(1).getId()));
        boolean thirdPosition = (id3.equals(resultList.get(2).getId()));
        assertEquals(true,firstPosition&&secondPosition&&thirdPosition);
    }

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
        List<Long> labelsId = new ArrayList<>();
        labelsId.add(persistedLabel1.getId());
        labelsId.add(persistedLabel2.getId());
        NewIssueDTO issueDto = createIssueDTO(title, content, state, labelsId, new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        Issue issue = issueService.getIssueById(issueId);
        List<Label> result = issueService.getLabelsByIssueId(issue);
        boolean firstPosition = (persistedLabel1.getId().equals(result.get(0).getId()));
        boolean secondPosition = (persistedLabel2.getId().equals(result.get(1).getId()));
        assertEquals(firstPosition && secondPosition, true);
    }

    /**
     * cosmina
     */
    @Test
    public void testGetDTOsFromIssues() {
        System.out.println("getDTOsFromIssues");
        User user = createUser("cosminav", "cosminav", "cosminav@mail6", "12345");
        String content = "content010";
        String title = "title010";
        String content2 = "content7110";
        String title2 = "title7110";
        IssueState state = IssueState.OPEN;
        NewIssueDTO issueDto1 = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());
        NewIssueDTO issueDto2 = createIssueDTO(title2, content2, state, new ArrayList<Long>(), new ArrayList<Long>());
        Long id1 = issueService.createIssueFromIssueDTO(issueDto1, user);
        Long id2 = issueService.createIssueFromIssueDTO(issueDto2, user);

        List<Issue> issues = new ArrayList<>();
        issues.add(issueService.getIssueById(id1));
        issues.add(issueService.getIssueById(id2));

        List<Issue> expResult = new ArrayList<>();
        List<IssueDTO> result = issueService.getDTOsFromIssues(issues);
        for (IssueDTO issueDTO : result) {
            expResult.add(issueDTO.getIssue());
        }
        assertEquals(equalsListsOfIssues(issues, expResult), true);
    }

    /**
     * Test of getLabelByName method, of class IssueService.
     */
    @Test
    public void testGetLabelByName() {
        System.out.println("getLabelByName");
        String labelName = "yupiya";
        String labelColor = "#FF3300";

        Label label = createlabel(labelColor, labelName);
        label = issueService.createLabel(label);
        Label result = issueService.getLabelByName(labelName);
        assertEquals(result.getId(),label.getId());;
    }

    /**
     * Test of findUsersIssuesOwnersByNamePrefix method, of class IssueService.
     */
    @Test
    public void testFindUsersIssuesOwnersByNamePrefixNotMatching() {
        String usernamePrefix = "a4983e57gfh7854rg";
        List<User> expResult = new ArrayList<>();
        List<User> result = issueService.findUsersIssuesOwnersByNamePrefix(usernamePrefix);
        assertEquals(expResult, result);
    }

    /**
     * Test of findUsersIssuesOwnersByNamePrefix method, of class IssueService.
     */
    @Test
    public void testFindUsersIssuesOwnersByNamePrefix() {
        System.out.println("findUsersIssuesOwnersByNamePrefix");
        User user = createUser("xavier", "xavier", "xavier@mail6", "12345");
        User userx = createUser("xenofob", "xenofob", "xenofob@mail6", "12345");
        IssueState state = IssueState.OPEN;
        NewIssueDTO issueDto = createIssueDTO("tex", "lalalalalal", state, new ArrayList<Long>(), new ArrayList<Long>());
        Long id1 = issueService.createIssueFromIssueDTO(issueDto, user);
        String usernamePrefix = "x";
        List<User> result = issueService.findUsersIssuesOwnersByNamePrefix(usernamePrefix);
        assertEquals(result.get(0).getId(), user.getId());
    }

    /**
     * Test of findUsersAssigneesByNamePrefix method, of class IssueService.
     */
    @Test
    public void testFindUsersAssigneesByNamePrefixNotExisting() {
        String usernamePrefix = "cos";
        List<User> expResult = new ArrayList<>();
        List<User> result = issueService.findUsersAssigneesByNamePrefix(usernamePrefix);
        assertEquals(expResult, result);
    }

    @Test
    public void testFindUsersIssuesAssigneesByNamePrefix() {
        System.out.println("findUsersIssuesOwnersByNamePrefix");
        User user = createUser("bavier", "bavier", "bavier@mail6", "12345");
        User userx = createUser("benofob", "benofob", "benofob@mail6", "12345");
        IssueState state = IssueState.OPEN;
        NewIssueDTO issueDto = createIssueDTO("texxx", "lalalalalal", state, new ArrayList<Long>(), new ArrayList<Long>());
        issueDto.getIssue().setAssignee(userx);
        Long id1 = issueService.createIssueFromIssueDTO(issueDto, user);
        String usernamePrefix = "b";
        List<User> result = issueService.findUsersAssigneesByNamePrefix(usernamePrefix);
        assertEquals(result.get(0).getId(), userx.getId());
    }

    //AUGUSTIN

    @Test
    public void testUpdateIssueStateFalse() {
        String content = "content8";
        String title = "title8";
        IssueState state = IssueState.CLOSED;
        User lastUpdateBy = createUser("agust668", "dbzklm", "ag668@gmail", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());
        User user = createUser("nicu668", "name", "n@mail668", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        boolean succes = issueService.updateIssueState(10001, IssueState.CLOSED);

        assertEquals(false, succes);
    }

    @Test
    public void testUpdateIssueStateTrue() {
        String content = "content8";
        String title = "title8";
        User user = createUser("nicu6611", "name", "n@mai6611", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        boolean succes = issueService.updateIssueState(issueId, IssueState.CLOSED);

        assertEquals(true, succes);
    }

    @Test
    public void testChangeStateOfAnIssueToNewStateFalse() {
        String content = "content4";
        String title = "title4";
        User lastUpdateBy = createUser("agust669", "dbzklm", "ag669@gmail", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());
        User user = createUser("nicu669", "name", "n@mail669", "12345");

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.updateIssueState(issueId, IssueState.CLOSED);

        Issue issue = issueService.getIssueById(issueId);

        assertEquals(false, issueService.changeStateOfAnIssueToNewState(issue, IssueState.CLOSED));
    }

    @Test
    public void testChangeStateOfAnIssueToNewStateTrue() {
        String content = "content4";
        String title = "title4";
        IssueState state = IssueState.OPEN;
        User user = createUser("nicu6610", "name", "n@mail6610", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.updateIssueState(issueId, IssueState.CLOSED);

        Issue issue = issueService.getIssueById(issueId);

        assertEquals(true, issueService.changeStateOfAnIssueToNewState(issue, IssueState.OPEN));
    }

    @Test
    public void testChangeStateOfIssueFalse() {
        String content = "content4";
        String title = "title4";
        IssueState state = IssueState.OPEN;
        User user = createUser("nicu6612", "name", "n@mail6612", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        boolean result = issueService.changeStateOfIssue(issueId, null, user);

        assertEquals(false, result);

    }

    @Test
    public void testChangeStateOfIssueFalse2() {
        String content = "content4";
        String title = "title4";
        IssueState state = IssueState.OPEN;
        User user = createUser("nicu6614", "name", "n@mail6614", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        boolean result = issueService.changeStateOfIssue(issueId, IssueState.OPEN, user);

        assertEquals(false, result);

    }

    @Test
    public void testChangeStateOfIssueTrue() {
        String content = "content4";
        String title = "title4";
        User user = createUser("nicu6615", "name", "n@mail6615", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        boolean result = issueService.changeStateOfIssue(issueId, IssueState.CLOSED, user);

        assertEquals(true, result);
    }

    @Test
    public void testChangeStateOfIssueTrue2() {
        String content = "content4";
        String title = "title4";
        User user = createUser("nicu6616", "name", "n@mail6616", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.CLOSED,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        boolean result = issueService.changeStateOfIssue(issueId, IssueState.OPEN, user);

        assertEquals(true, result);
    }

    @Test
    public void testRemoveLabelTrue() {
        String labelName = "sanasara";
        String labelColor = "#FF3300";

        Label label = createlabel(labelColor, labelName);
        label = issueService.createLabel(label);

        boolean result = issueService.removeLabel(label.getId());

        assertEquals(true, result);
    }

    @Test
    public void testRemoveLabelFalse() {
        String labelName = "tldr2";
        String labelColor = "#FF3300";

        Label label = createlabel(labelColor, labelName);
        issueService.createLabel(label);

        boolean result = issueService.removeLabel((long) -1);

        assertEquals(false, result);
    }

    @Test
    public void testGetMissedCommentsNegativeCommentId() {
        String content = "content6";
        String title = "title56";
        User user = createUser("nicu6617", "name", "n@mai66l7", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.addComment(user, issueId, createSimpleComment("comment 1"));
        issueService.addComment(user, issueId, createSimpleComment("comment 2"));
        issueService.addComment(user, issueId, createSimpleComment("comment 3"));
        List<Comment> commentList = issueService.getMissedComments(issueId, -5);

        assertEquals(commentList.size(), 3);
    }

    @Test
    public void testGetMissedCommentsUnexistentCommentId() {
        String content = "content6";
        String title = "title56";
        User user = createUser("nicu6621", "name", "n@mai6621", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.addComment(user, issueId, createSimpleComment("comment 1"));
        issueService.addComment(user, issueId, createSimpleComment("comment 2"));
        issueService.addComment(user, issueId, createSimpleComment("comment 3"));
        List<Comment> commentList = issueService.getMissedComments(issueId, 1001);

        assertEquals(commentList.size(), 3);
    }

    @Test
    public void testGetMissedCommentsIssueIdMismatch() {
        String content = "content6";
        String title = "title56";
        User user = createUser("nicu6620", "name", "n@mai6620", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());
        NewIssueDTO issueDto2 = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        Long issueId2 = issueService.createIssueFromIssueDTO(issueDto2, user);

        issueService.addComment(user, issueId, createSimpleComment("comment 1"));
        issueService.addComment(user, issueId, createSimpleComment("comment 2"));
        Comment comment = issueService.addComment(user, issueId, createSimpleComment("comment 3"));
        List<Comment> commentList = issueService.getMissedComments(issueId2, comment.getId());

        assertEquals(commentList.size(), 0);
    }

    @Test
    public void testGetMissedCommentsTrue() {
        String content = "content6";
        String title = "title56";
        User user = createUser("nicu6622", "name", "n@mai6622", "12345");
        NewIssueDTO issueDto = createIssueDTO(title, content, IssueState.OPEN,
                new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        issueService.addComment(user, issueId, createSimpleComment("comment 1"));
        Comment comment = issueService.addComment(user, issueId, createSimpleComment("comment 2"));
        issueService.addComment(user, issueId, createSimpleComment("comment 3"));
        List<Comment> commentList = issueService.getMissedComments(issueId, comment.getId());

        assertEquals(0, 0);
    }

    @Test
    public void testUpdateLabel() {
        String labelName = "mdka";
        String labelColor = "#FF3300";

        Label label = createlabel(labelColor, labelName);
        issueService.createLabel(label);

        String newLabelName = "interesting";
        String newLabelColor = "#FFFFFF";

        label.setName(newLabelName);
        label.setColor(newLabelColor);

        issueService.updateLabel(label);

        assertEquals(newLabelName, label.getName());
        assertEquals(newLabelColor, label.getColor());
    }

    @Test
    public void testEditIssueTitleFromIssueDTO() {
        System.out.println("editIssueTitleFromIssueDTO");
        User user = createUser("edit123", "edit12", "edr3@mail6", "12345");
        String content = "initialContent";
        String title = "initialTitle";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        Issue issue = issueService.getIssueById(issueId);
        issue.setTitle("newTitle");
        issueDto.setIssue(issue);

        Issue editedIssue = issueService.editIssueFromIssueDTO(issueDto, user);
        assertEquals(editedIssue.getTitle(), "newTitle");
    }

    @Test
    public void testEditIssueContentFromIssueDTO() {
        System.out.println("editIssueContentFromIssueDTO");
        User user = createUser("edzit123", "edzit12", "edzr3@mail6", "12z345");
        String content = "initialContent";
        String title = "initialTitle";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        Issue issue = issueService.getIssueById(issueId);
        issue.setContent("newContent");
        issueDto.setIssue(issue);

        Issue editedIssue = issueService.editIssueFromIssueDTO(issueDto, user);
        assertEquals(editedIssue.getContent(), "newContent");
    }

    @Test
    public void testEditIssueLabelsFromIssueDTO() {
        System.out.println("editIssueLabelsFromIssueDTO");
        User user = createUser("ir4321", "ior4t123321", "ior423321@mail6", "12345");
        String content = "ior43321";
        String title = "tionr464";
        IssueState state = IssueState.OPEN;

        String labelName1 = "14rupi";
        String labelName2 = "14rpi2";
        String labelColor = "#FF3300";
        Label label1 = createlabel(labelColor, labelName1);
        Label label2 = createlabel(labelColor, labelName2);
        Label persistedLabel1 = issueService.createLabel(label1);
        Label persistedLabel2 = issueService.createLabel(label2);
        List<Long> labelsId = new ArrayList<>();
        labelsId.add(persistedLabel1.getId());
        labelsId.add(persistedLabel2.getId());
        NewIssueDTO issueDto = createIssueDTO(title, content, state, labelsId, new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);

        String labelName3 = "test?";
        Label label3 = createlabel(labelColor, labelName3);
        Label persistedLabel3 = issueService.createLabel(label3);
        issueDto.getLabelIdList().remove(0);
        issueDto.getLabelIdList().add(persistedLabel3.getId());
        issueDto.getLabelIdList().add(new Long(9999));

        Issue editedIssue = issueService.editIssueFromIssueDTO(issueDto, user);
        issueService.getLabelsByIssueId(editedIssue);
        Issue issue = issueService.getIssueById(issueId);
        List<Label> result = issueService.getLabelsByIssueId(issue);
        boolean found = false;
        for (Label label : result) {
            if (label.getName().equals(labelName3)) {
                found = true;
            }
        }
        assertEquals(found, true);
    }

    /**
     * Test of removeAllLabelsFromAnIssue method, of class IssueService.
     */
    @Test
    public void testRemoveAllLabelsFromAnIssue() {
        System.out.println("removeAllLabelsFromAnIssue");
        User user = createUser("ionut123321", "ionut123321", "ionut123321@mail6", "12345");
        String content = "ionut123321";
        String title = "tionut123321itle64";
        IssueState state = IssueState.OPEN;

        String labelName1 = "123yupi";
        String labelName2 = "123yupi2";
        String labelColor = "#FF3300";
        Label label1 = createlabel(labelColor, labelName1);
        Label label2 = createlabel(labelColor, labelName2);
        Label persistedLabel1 = issueService.createLabel(label1);
        Label persistedLabel2 = issueService.createLabel(label2);
        List<Long> labelsId = new ArrayList<>();
        labelsId.add(persistedLabel1.getId());
        labelsId.add(persistedLabel2.getId());
        NewIssueDTO issueDto = createIssueDTO(title, content, state, labelsId, new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        Issue issue = issueService.getIssueById(issueId);

        issueService.removeAllLabelsFromAnIssue(issueId);
        List<Label> result = issueService.getLabelsByIssueId(issue);

        assertTrue(result.isEmpty());
    }

    /**
     * Test of removeAllAttachmentsFromAnIssue method, of class IssueService.
     */
    @Test
    public void testRemoveAllAttachmentsFromAnIssue() {
        System.out.println("removeAllAttachmentsFromAnIssue");
        User user = createUser("ionut1765", "i56723321", "io6t123321@mail6", "12345");
        String content = "io567321";
        String title = "t567itle64";
        IssueState state = IssueState.OPEN;

        NewIssueDTO issueDto = createIssueDTO(title, content, state, new ArrayList<Long>(), new ArrayList<Long>());

        Long issueId = issueService.createIssueFromIssueDTO(issueDto, user);
        Issue issue = issueService.getIssueById(issueId);

        issueService.removeAllAttachmentsFromAnIssue(issueId);
        List<Label> result = issueService.getLabelsByIssueId(issue);

        assertTrue(result.isEmpty());
    }
}
