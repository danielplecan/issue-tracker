/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.service;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.User;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author inistor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
public class ActivationServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ActivationService activationService;

    User user;

    public ActivationServiceTest() {
    }

    @Before
    public void setUp() throws Exception {
        UserDTO userDto = new UserDTO();
        userDto.setName("userACtive");
        userDto.setUsername("userActive");
        userDto.setEmail("userActive@email");
        userDto.setPassword("12345");
        
         userService.registerUser(userDto);
         user= userService.getUserByUsername(userDto.getUsername());        
    }

    @Test
    public void testCreateActivation() {
       
        String activationHash = activationService.createActivation(user);
        User result = activationService.getUserByActivationHash(activationHash);
        
        User expectation = userService.getUserByUsername(user.getUsername());
        
        String [] expecteds = new String [3];
        String [] actuals = new String [3];
        
        expecteds[0]=expectation.getName();
        expecteds[1]=expectation.getUsername();
        expecteds[2]=expectation.getEmail();
        
        actuals[0]=result.getName();
        actuals[1]=result.getUsername();
        actuals[2]=result.getEmail();

        assertArrayEquals(actuals, expecteds);
    }
}
