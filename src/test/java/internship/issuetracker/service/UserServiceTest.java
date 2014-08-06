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
 * @author vmiron
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
public class UserServiceTest {
    
    @Autowired
    UserService userService;
    
    public UserServiceTest() {
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
     * Test of registerUser method, of class UserService.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        UserDTO userDTO = new UserDTO();
        userDTO.setName("user1");
        userDTO.setEmail("user1@email.com");
        userDTO.setUsername("username1");
        userDTO.setPassword("12345");
        
        userService.registerUser(userDTO);
        User result = userService.getUserByEmail(userDTO.getEmail());
        String [] expecteds = new String [3];
        String [] actuals = new String [3];
        
        expecteds[0]=userDTO.getName();
        expecteds[1]=userDTO.getUsername();
        expecteds[2]=userDTO.getEmail();
        
        actuals[0]=result.getName();
        actuals[1]=result.getUsername();
        actuals[2]=result.getEmail();
        
        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of usernameExists method, of class UserService.
     */
    @Test
    public void testUsernameExists() {
        System.out.println("usernameExists");
        
        UserDTO userDTO = new UserDTO();
        userDTO.setName("user2");
        userDTO.setEmail("user2@email.com");
        userDTO.setUsername("username2");
        userDTO.setPassword("12345");
        
        userService.registerUser(userDTO);
        boolean expResult = true;
        boolean result = userService.usernameExists(userDTO.getUsername());
        assertEquals(expResult, result);
    }

    /**
     * Test of emailExists method, of class UserService.
     */
    @Test
    public void testEmailExists() {
        System.out.println("emailExists");
        
        UserDTO userDTO = new UserDTO();
        userDTO.setName("user3");
        userDTO.setEmail("user3@email.com");
        userDTO.setUsername("username3");
        userDTO.setPassword("12345");
        
        userService.registerUser(userDTO);
        boolean expResult = true;
        boolean result = userService.emailExists(userDTO.getEmail());
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserByUsername method, of class UserService.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        UserDTO userDTO = new UserDTO();
        userDTO.setName("user4");
        userDTO.setEmail("user4@email.com");
        userDTO.setUsername("username4");
        userDTO.setPassword("12345");
        
        userService.registerUser(userDTO);
        User result = userService.getUserByUsername(userDTO.getUsername());
        String [] actuals = new String [3];
        String [] expecteds = new String [3];
        
        expecteds[0]=userDTO.getName();
        expecteds[1]=userDTO.getUsername();
        expecteds[2]=userDTO.getEmail();
        
        actuals[0]=result.getName();
        actuals[1]=result.getUsername();
        actuals[2]=result.getEmail();
        
        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of getUserByEmail method, of class UserService.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        UserDTO userDTO = new UserDTO();
        userDTO.setName("user5");
        userDTO.setEmail("user5@email.com");
        userDTO.setUsername("username5");
        userDTO.setPassword("12345");
        
        userService.registerUser(userDTO);
        User result = userService.getUserByEmail(userDTO.getEmail());
        String [] actuals = new String [3];
        String [] expecteds = new String [3];
        
        expecteds[0]=userDTO.getName();
        expecteds[1]=userDTO.getUsername();
        expecteds[2]=userDTO.getEmail();
        
        actuals[0]=result.getName();
        actuals[1]=result.getUsername();
        actuals[2]=result.getEmail();
        
        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of loginUser method, of class UserService.
     */
    @Test
    public void testLoginUserNullUsername() {
        System.out.println("loginUserNullUsername");
        String username = null;
        String password = "123456";
 
        User expResult = null;
        
        User result = userService.loginUser(username, password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of loginUser method, of class UserService.
     */
    @Test
    public void testLoginUserNullPassword() {
        System.out.println("loginUserNullPassword");
        String username = "user1";
        String password = null;
 
        User expResult = null;
        
        User result = userService.loginUser(username, password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of loginUser method, of class UserService.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
 
        UserDTO userDTO = new UserDTO();
        userDTO.setName("user6");
        userDTO.setEmail("user6@email.com");
        userDTO.setUsername("username6");
        userDTO.setPassword("123456");
        userService.registerUser(userDTO);
        
        User expResult = userService.getUserByUsername(userDTO.getUsername());
        
        User result = userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
        
        String [] actuals = new String [3];
        String [] expecteds = new String [3];
        
        expecteds[0]=expResult.getName();
        expecteds[1]=expResult.getUsername();
        expecteds[2]=expResult.getEmail();
        
        actuals[0]=result.getName();
        actuals[1]=result.getUsername();
        actuals[2]=result.getEmail();
        
        assertArrayEquals(expecteds, actuals);
    }
}
