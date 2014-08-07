/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.service;

import internship.issuetracker.dto.IssueDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.UploadedFile;
import internship.issuetracker.entity.User;
import internship.issuetracker.util.IssueEditUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author scalin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
public class NotificationsTest {
    
    @Autowired
    IssueService issueService;
    
    @Autowired
    MailService mailService;
    
    
    
    public NotificationsTest() {
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

    //notify owner, notify assignee, notify random user, 
        
        
        //simple comment, 
        
        //changed state
        
        //changed state and comment
    
    
    private User getUser(int num) {
        User user = new User();
        user.setActive(true);
        user.setEmail("someemail" + num + "@email.com");
        user.setId(new Long(num));
        user.setName("name" + num);
        user.setUsername("username + num");
        return user;
    }
    
    private Comment getComment(int num) {
        Comment comment = new Comment();
        comment.setId(new Long(num));
        comment.setAttachments(new ArrayList<UploadedFile>());
        comment.setAuthor(getUser(num));
        comment.setDate(new Date());
        comment.setIssue(new Issue());
        comment.setContent("conent" + num);
     
        return comment;
    }
    
    private Issue getIssue(int num){
        Issue issue = new Issue();
        issue.setAssignee(null);
        issue.setContent("content" + num);
        issue.setState(IssueState.OPEN);
        issue.setOwner(getUser(num));
        return issue;
    }
    
    /**
     * Testing case when 
     * NotificationTarget:random user, 
     * CommentType: simple comment (text only) 
     */
    @Test
    public void sendNotificationForCommentSimpleCommentTest() {
        Comment comment = getComment(1);
        User user = getUser(2);
        comment.setChangeState(null);
        
        issueService.sendNotificationForComment(comment, user, "some link");
    }
     /**
     * Testing case when 
     * NotificationTarget:issue owner, 
     * CommentType: simple comment (text only) 
     */
    @Test
    public void sendNotificationForCommentSimpleCommentToIssueOwnerTest() {
        Comment comment = getComment(1);
        Issue issue = getIssue(1);
        User user = issue.getOwner();
        comment.setChangeState(null);
        
        issueService.sendNotificationForComment(comment, user, "some link");
    }
    
     /**
     * Testing case when 
     * NotificationTarget:issue assignee, 
     * CommentType: simple comment (text only) 
     */
    @Test
    public void sendNotificationForCommentSimpleCommentToIssueAssigneeTest() {
        Comment comment = getComment(1);
        Issue issue = getIssue(1);
        User user = issue.getOwner();
        User assignee = getUser(2);
        comment.setChangeState(null);
        issue.setAssignee(assignee);

        issueService.sendNotificationForComment(comment, assignee, "some link");
    }
    
    /**
     * Testing case when 
     * NotificationTarget:issue owner, 
     * CommentType: Changed state 
     */
    @Test
    public void sendNotificationForChangeStateCommentToIssueOwnerTest() {
        Comment comment = getComment(1);
        Issue issue = getIssue(1);
        User user = issue.getOwner();
        comment.setChangeState(IssueState.CLOSED);
        
        issueService.sendNotificationForComment(comment, user, "some link");
    }
    
    /**
     * Testing case when 
     * NotificationTarget:issue assignee, 
     * CommentType: simple comment (text only) 
     */
     @Test
    public void sendNotificationForCommentChangeStateToIssueAssigneeTest() {
        Comment comment = getComment(1);
        Issue issue = getIssue(1);
        User user = issue.getOwner();
        User assignee = getUser(2);
        comment.setChangeState(IssueState.CLOSED);
        issue.setAssignee(assignee);

        issueService.sendNotificationForComment(comment, assignee, "some link");
    }
    
    /**
     * Testing case when 
     * NotificationTarget:assignee, 
     * NotificationCase: Logged in user assigns himself to an issue 
     */
    @Test
    public void sendNotificationForAssignSelfAssign() {
        Issue issue = getIssue(1);
        issue.setAssignee(getUser(2));
        
        issueService.sendNotificationForAssign(issue, getUser(2), "SomeHttpStuff");
    }
    
    /**
     * Testing case when 
     * NotificationTarget:assignee, 
     * NotificationCase: Logged in user assigns someone else to an issue
     */
    @Test
    public void sendNotificationForAssigningOthers() {
        
        Issue issue = getIssue(1);
        issue.setAssignee(getUser(2));
        
        issueService.sendNotificationForAssign(issue, getUser(3), "SomeHttpStuff");
    }
    
    /**
     * Testing case when 
     * NotificationTarget:assignee, 
     * NotificationCase: Logged in user assigns someone else to an issue
     */
    @Test
    public void sendNotificationWhenClearingAssignee() {
        
        Issue issue = getIssue(1);
        issue.setAssignee(null);
        
        issueService.sendNotificationForAssign(issue, getUser(2), "SomeHttpStuff");
    }
    
    /**
     * Testing case when 
     * NotificationTarget:owner, assignee
     * NotificationCase: Editing an issue
     */
    @Test
    public void sendNotificationForEditingTest() {
        NewIssueDTO issue1 = new NewIssueDTO();
        issue1.setIssue(getIssue(1));
        issue1.setLabelIdList(new ArrayList<Long>());
        issue1.setAttachments(new ArrayList<Long>());
        issue1.getIssue().setAssignee(getUser(2));
        List<Long> issue1Labels = issue1.getLabelIdList();
        issue1Labels.add(33L);
        issue1Labels.add(34L);
        issue1Labels.add(35L);
        issue1Labels.add(36L);
        
        IssueDTO issue2 = new IssueDTO();
        issue2.setIssue(getIssue(1));
        issue2.getIssue().setAssignee(getUser(2));
        issue2.setLabels(new ArrayList<Label>());
        List<Label> issue2Labels = issue2.getLabels();
        issue2Labels.add(new Label());
        issue2Labels.add(new Label());
        issue2Labels.get(0).setId(88L);
        issue2Labels.get(1).setId(89L);
        
        issue1.getIssue().setContent("different content");
        issue1.getIssue().setTitle("different title");
        List<Long> newLabelList = issue1.getLabelIdList();
        newLabelList.add(10L);
        issue1.setLabelIdList(newLabelList);
        
        
        IssueEditUtil.storeChanges(issue1, issue2);
        Issue issue = getIssue(1);
        issue.setLastUpdatedBy(getUser(3));
        issue.setAssignee(getUser(4));
        
        issueService.sendNotificationForEdit(issue, "SomeHttpStuff");
    }
    
    
}
