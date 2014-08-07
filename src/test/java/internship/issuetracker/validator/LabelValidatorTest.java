/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.validator;

import internship.issuetracker.entity.Label;
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
public class LabelValidatorTest {

    public LabelValidatorTest() {
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
        Class type = Label.class;
        LabelValidator instance = new LabelValidator();
        boolean expResult = true;
        boolean result = instance.supports(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of supports method, of class CommentValidator.
     */
    @Test
    public void testSupportsFalse() {
        Class type = LabelForEditValidator.class;
        LabelValidator instance = new LabelValidator();
        boolean expResult = false;
        boolean result = instance.supports(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class LabelValidator.
     */
    @Test
    public void testValidate() {
   
        Label label = new Label();
        label.setName("zzzz");
        label.setColor("#ffffff");
        
        
        Errors errors = new BeanPropertyBindingResult(label, "label");
        
        LabelValidator instance = new LabelValidator();
        assertEquals(0, errors.getAllErrors().size());

    }

}
