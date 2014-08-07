/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.service;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.User;
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

/**
 *
 * @author scalin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
public class UserSettingsServiceTest {
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserSettingsService userSettingsService;
    
    public UserSettingsServiceTest() {
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
     * Test of createSettingsforUser method, of class UserSettingsService.
     */
    
    private User getTestUserWithCreatedSettings() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("user4452423");
        userDTO.setEmail("user23424@email.com");
        userDTO.setUsername("username2342344");
        userDTO.setPassword("123423425");
        User user = userService.getUserByUsername(userDTO.getUsername());
        if ( user != null) {
            return user;
        }
        else{
            userService.registerUser(userDTO);
            User testUser = userService.getUserByUsername(userDTO.getUsername());
            
            userSettingsService.createSettingsforUser(testUser.getUsername());
            
            return userService.getUserByUsername(userDTO.getUsername());
        }
    }
    
    private void resetSettingsForTestUser() {
        User testUser = getTestUserWithCreatedSettings();
        userSettingsService.changeUserThemePreference(testUser.getUsername(), 0L);
        if (userSettingsService.getNotificationStatusForAssigned(testUser.getUsername())) {
            userSettingsService.toggleNotificationsForAssigned(testUser.getUsername());
        }
        if (userSettingsService.getNotificationStatusForPosted(testUser.getUsername())) {
            userSettingsService.toggleNotificationsForPosted(testUser.getUsername());
        }
    }
    
    
    @Test
    public void testCreateSettingsforUser() {
        System.out.println("createSettingsforUser");

        User testUser = getTestUserWithCreatedSettings();
        resetSettingsForTestUser();
        testUser = userService.getUserByUsername(testUser.getUsername());
        
        assertNotNull(testUser.getSettings());
        assertEquals(testUser.getSettings().getTheme(), new Long(0));
        assertTrue(testUser.getSettings().isNotificationsForAssignedIssues().equals(Boolean.FALSE));
        assertTrue(testUser.getSettings().isNotificationsForPostedIssues().equals(Boolean.FALSE));       
    }

    /**
     * Test of toggleNotificationsForPosted method, of class UserSettingsService.
     */
    @Test
    public void testToggleNotificationsForPosted() {
        System.out.println("toggleNotificationsForPosted");
        
        User testUser = getTestUserWithCreatedSettings();
        
        Boolean initial = testUser.getSettings().isNotificationsForPostedIssues();
        
        userSettingsService.toggleNotificationsForPosted(testUser.getUsername());
        
        testUser = userService.getUserByUsername(testUser.getUsername());
        
        assertEquals(testUser.getSettings().isNotificationsForPostedIssues(), !initial);
        
    }

    /**
     * Test of toggleNotificationsForAssigned method, of class UserSettingsService.
     */
    @Test
    public void testToggleNotificationsForAssigned() {
        System.out.println("toggleNotificationsForPosted");
        
        User testUser = getTestUserWithCreatedSettings();
        Boolean initial = testUser.getSettings().isNotificationsForAssignedIssues();
        
        userSettingsService.toggleNotificationsForAssigned(testUser.getUsername());
        
        testUser = userService.getUserByUsername(testUser.getUsername());
        
        assertEquals(testUser.getSettings().isNotificationsForAssignedIssues(), !initial);
    }

    /**
     * Test of getNotificationStatusForPosted method, of class UserSettingsService.
     */
    @Test
    public void testGetNotificationStatusForPosted() {
        System.out.println("getNotificationStatusForPosted");
        
        User testUser = getTestUserWithCreatedSettings();
        Boolean expected = testUser.getSettings().isNotificationsForPostedIssues();
        Boolean result = userSettingsService.getNotificationStatusForPosted(testUser.getUsername());
        
        assertEquals(expected, result);
    }

    /**
     * Test of getNotificationStatusForAssigned method, of class UserSettingsService.
     */
    @Test
    public void testGetNotificationStatusForAssigned() {
        System.out.println("getNotificationStatusForAssigned");
        User testUser = getTestUserWithCreatedSettings();
        Boolean expected = testUser.getSettings().isNotificationsForAssignedIssues();
        Boolean result = userSettingsService.getNotificationStatusForAssigned(testUser.getUsername());
        
        assertEquals(expected, result);
    }

    /**
     * Test of getCurrentThemePreference method, of class UserSettingsService.
     */
    @Test
    public void testGetCurrentThemePreference() {
        System.out.println("getCurrentThemePreference");
        
        User testUser = getTestUserWithCreatedSettings();
        Long expected = testUser.getSettings().getTheme();
        Long result = userSettingsService.getCurrentThemePreference(testUser.getUsername());
        
        assertEquals(expected, result);
    }

    /**
     * Test of changeUserThemePreference method, of class UserSettingsService.
     */
    @Test
    public void testChangeUserThemePreference() {
        System.out.println("changeUserThemePreference");
       
        User testUser = getTestUserWithCreatedSettings();
        Long targetTheme = 2L;
        Boolean result;
        //valid username, valid theme
        result = userSettingsService.changeUserThemePreference(testUser.getUsername(), targetTheme);
        assertEquals(result, Boolean.TRUE);
        
        //invalid username, valid theme
        result = userSettingsService.changeUserThemePreference("invalid username %&#@", targetTheme);
        assertEquals(result, Boolean.FALSE);
        
        //valid username, invalid theme
        result = userSettingsService.changeUserThemePreference(testUser.getUsername(), 22L);
        assertEquals(result, Boolean.FALSE);
    }

    /**
     * Test of getUserService method, of class UserSettingsService.
     */
    @Test
    public void testGetUserService() {
        System.out.println("getUserService");
        
       UserService expected = this.userService;
       UserService result = userSettingsService.getUserService();
       
        assertEquals(expected, result);
    }    
}
