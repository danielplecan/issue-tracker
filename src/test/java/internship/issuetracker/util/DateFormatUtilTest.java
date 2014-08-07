/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author atataru
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
public class DateFormatUtilTest {

    public DateFormatUtilTest() {
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
     * Test of getDateFormat2 method, of class DateFormatUtil.
     */
    @Test
    public void testGetDateFormat2() {
        Date date = new Date();
        String result = DateFormatUtil.getDateFormat2(date);
        assertEquals(10, result.length());
    }

    /**
     * Test of appendAgo method, of class DateFormatUtil.
     */
    @Test
    public void testAppendAgoFalse() {
        StringBuilder date = new StringBuilder("some date ");
        boolean needsAgo = false;
        String result = DateFormatUtil.appendAgo(date, needsAgo);
        assertEquals(false, result.endsWith("ago"));
    }

    /**
     * Test of appendAgo method, of class DateFormatUtil.
     */
    @Test
    public void testAppendAgoTrue() {
        StringBuilder date = new StringBuilder("some date ");
        boolean needsAgo = true;
        String result = DateFormatUtil.appendAgo(date, needsAgo);
        assertEquals(true, result.endsWith("ago"));
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval1Second() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - 1000;
        oldDate.setTime(miliseconds);
        String expResult = " 1 second ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval3Second() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - 3 * 1000;
        oldDate.setTime(miliseconds);
        String expResult = "3 seconds ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval1Minute() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - 64 * 1000;
        oldDate.setTime(miliseconds);
        String expResult = "1 minute ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult.trim(), result.trim());
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval4Minute() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - 4 * 61 * 1000;
        oldDate.setTime(miliseconds);
        String expResult = "4 minutes ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult.trim(), result.trim());
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval1Hour() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - 61 * 60 * 1000;
        oldDate.setTime(miliseconds);
        String expResult = "1 hour ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult.trim(), result.trim());
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval5Hour() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - (5 * 60 * 60 * 1000 + 100);
        oldDate.setTime(miliseconds);
        String expResult = "5 hours ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult.trim(), result.trim());
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval1Day() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - (25 * 60 * 60 * 1000 + 100);
        oldDate.setTime(miliseconds);
        String expResult = "1 day ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult.trim(), result.trim());
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyInterval2Day() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - (2 * 24 * 60 * 60 * 1000 + 1000);
        oldDate.setTime(miliseconds);
        String expResult = "2 days ago";
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult.trim(), result.trim());
    }

    /**
     * Test of getFriendlyInterval method, of class DateFormatUtil.
     */
    @Test
    public void testGetFriendlyIntervalMoreThan14Days() {
        Date oldDate = new Date();
        long miliseconds = oldDate.getTime() - (16 * 24 * 60 * 60 * 1000 + 1000);
        oldDate.setTime(miliseconds);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String expResult = dateFormat.format(oldDate);
        String result = DateFormatUtil.getFriendlyInterval(oldDate);
        assertEquals(expResult.trim(), result.trim());
    }
}
