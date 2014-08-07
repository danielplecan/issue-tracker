/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.validator;

import internship.issuetracker.entity.Label;
import internship.issuetracker.service.IssueService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 *
 * @author atataru
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
public class LabelValidatorTest {

    @Autowired
    LabelValidator instance;

    @Autowired
    IssueService issueService;

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

        instance.validate(label, errors);
        assertEquals(0, errors.getAllErrors().size());

    }

    /**
     * Test of validate method, of class LabelValidator.
     */
    @Test
    public void testValidate2() {

        Label label = new Label();
        label.setName("name1");
        label.setColor("#ffffff");
        
        issueService.createLabel(label);

        
        Label label2 = new Label();
        label2.setName("name1");
        label2.setColor("#ffffff");
        Errors errors2 = new BeanPropertyBindingResult(label, "label");

        instance.validate(label2, errors2);
        assertEquals(1, errors2.getAllErrors().size());

    }

}
