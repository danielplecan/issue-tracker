/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.validator;

import internship.issuetracker.dto.CommentDTO;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 *
 * @author atataru
 */
public class CommentValidatorTest {
    
    public CommentValidatorTest() {
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

    /**
     * Test of supports method, of class CommentValidator.
     */
    @Test
    public void testSupportsTrue() {
        Class type = CommentDTO.class;
        CommentValidator instance = new CommentValidator();
        boolean expResult = true;
        boolean result = instance.supports(type);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of supports method, of class CommentValidator.
     */
    @Test
    public void testSupportsFalse() {
        Class type = Issue.class;
        CommentValidator instance = new CommentValidator();
        boolean expResult = false;
        boolean result = instance.supports(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class CommentValidator.
     */
    @Test
    public void testValidateStateIsNull() {
        System.out.println("validate");
        CommentDTO comment = new CommentDTO();
        comment.setAttachments(new ArrayList<Long>());
        comment.setChangeState(null);
        comment.setContent("husddde");
        comment.setDate(new Date());
        
        
        Errors errors = new BeanPropertyBindingResult(comment, null);
        
        CommentValidator instance = new CommentValidator();
        instance.validate(comment, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
    /**
     * Test of validate method, of class CommentValidator.
     */
    @Test
    public void testValidateStateIsNullWithBadSize() {
        System.out.println("validate");
        CommentDTO comment = new CommentDTO();
        comment.setAttachments(new ArrayList<Long>());
        comment.setChangeState(null);
        comment.setContent("h");
        comment.setDate(new Date());
        
        
        Errors errors = new BeanPropertyBindingResult(comment, "lol");
        
        CommentValidator instance = new CommentValidator();
        instance.validate(comment, errors);
        assertEquals(1, errors.getAllErrors().size());
    }
    
    /**
     * Test of validate method, of class CommentValidator.
     */
    @Test
    public void testValidateStateIsNotNullWithSize13() {
        System.out.println("validate");
        CommentDTO comment = new CommentDTO();
        comment.setAttachments(new ArrayList<Long>());
        comment.setChangeState(IssueState.CLOSED);
        comment.setContent("hq");
        comment.setDate(new Date());
        
        
        Errors errors = new BeanPropertyBindingResult(comment, "lol");
        
        CommentValidator instance = new CommentValidator();
        instance.validate(comment, errors);
        assertEquals(1, errors.getAllErrors().size());
    }
    
    /**
     * Test of validate method, of class CommentValidator.
     */
    @Test
    public void testValidateStateIsNotNullWithSize500() {
        System.out.println("validate");
        CommentDTO comment = new CommentDTO();
        comment.setAttachments(new ArrayList<Long>());
        comment.setChangeState(IssueState.OPEN);
        comment.setContent("hqsssssssssssssssssssssssssssssssssssssssssssssssssshqssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssshqssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssshqssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "hqsssssssssssssssssssssssssssssssssssssssssssssssssshqssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssshqsssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssshqssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssshqsssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssshqsssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssshqsssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssshqsssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        comment.setDate(new Date());
        
        
        Errors errors = new BeanPropertyBindingResult(comment, "lol");
        
        CommentValidator instance = new CommentValidator();
        instance.validate(comment, errors);
        assertEquals(1, errors.getAllErrors().size());
    }
    
    /**
     * Test of validate method, of class CommentValidator.
     */
    @Test
    public void testValidateStateIsNotNullWithNoErrors() {
        System.out.println("validate");
        CommentDTO comment = new CommentDTO();
        comment.setAttachments(new ArrayList<Long>());
        comment.setChangeState(IssueState.CLOSED);
        comment.setContent("valid content");
        comment.setDate(new Date());
        
        
        Errors errors = new BeanPropertyBindingResult(comment, "lol");
        
        CommentValidator instance = new CommentValidator();
        instance.validate(comment, errors);
        assertEquals(0, errors.getAllErrors().size());
    }
    
}
