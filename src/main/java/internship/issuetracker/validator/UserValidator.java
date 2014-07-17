/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.validator;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 *
 * @author dplecan
 */
@Service
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;

        if(userService.checkUsernameExistence(user.getUsername()) == true) {
            errors.rejectValue("username", "username.exists", "Username is already taken.");
        }

        if(userService.checkEmailExistence(user.getEmail()) == true) {
            errors.rejectValue("email", "email.exists", "Email is already taken.");
        }

        if(user.getPassword().length() < 5) {
            errors.rejectValue("password", "password.tooshort", "Password must be at least 5 characters long.");
        }

    }

}
