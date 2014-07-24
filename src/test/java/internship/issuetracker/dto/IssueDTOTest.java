///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package internship.issuetracker.dto;
//
//import internship.issuetracker.entity.Issue;
//import internship.issuetracker.entity.IssueState;
//import internship.issuetracker.entity.Label;
//import internship.issuetracker.entity.User;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.codehaus.jackson.map.JsonSerializer;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author dplecan
// */
//public class IssueDTOTest {
//    
//    public IssueDTOTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void testJSONSerialization() {
//        IssueDTO issueDTO = new IssueDTO();
//        
//        Issue issue = new Issue();
//        issue.setId(1L);
//        issue.setTitle("title");
//        issue.setContent("content");
//        issue.setState(IssueState.OPEN);
//        issue.setDate(new Date(1234L));
//        issue.setUpdateDate(new Date(4321L));
//        
//        User user = new User();
//        user.setId(1L);
//        user.setName("name");
//        user.setUsername("username");
//        user.setEmail("email@email.com");
//        user.setPasswordHash("abcdefghijklmn");
//        
//        Label label1 = new Label();
//        label1.setId(1L);
//        label1.setName("label1");
//        label1.setColor("#123456");
//        
//        Label label2 = new Label();
//        label2.setId(2L);
//        label2.setName("label2");
//        label2.setColor("#654321");
//        
//        List<Label> labels = new ArrayList<>();
//        labels.add(label1);
//        labels.add(label2);
//        
//        issue.setOwner(user);
//        
//        issueDTO.setIssue(issue);
//        issueDTO.setLabels(labels);
//        
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String returnedJSON = objectMapper.writeValueAsString(issueDTO);
//            String expectedJSON = "{\"id\":1,\"title\":\"title\",\"content\":\"content\",\"state\":\"OPEN\",\"date\":1234,\"updateDate\":4321,\"owner\":{\"id\":1,\"name\":\"name\",\"username\":\"username\",\"email\":\"email@email.com\"},\"dateFormat\":\"01-01-1970 03:00:01\",\"dateFormat2\":\"01-01-1970\",\"timeInterval\":\"01-01-1970\",\"lastUpdateDate\":\"01-01-1970\",\"labels\":[{\"id\":1,\"name\":\"label1\",\"color\":\"#123456\"},{\"id\":2,\"name\":\"label2\",\"color\":\"#654321\"}]}";
//            assertEquals(expectedJSON, expectedJSON);
//        } catch (IOException ex) {
//            Logger.getLogger(IssueDTOTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//}
