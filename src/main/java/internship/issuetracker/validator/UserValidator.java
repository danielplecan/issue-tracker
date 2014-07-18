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

        //crw: consider renaming 'checkUsernameExistence' to 'usernameExists' and remove ' == true'
        //crw: if (userService.usernameExists(...)){...}
        if(userService.usernameExists(user.getUsername())) {
            errors.rejectValue("username", "username.exists", "Username is already taken.");
        }

        if(userService.emailExists(user.getEmail())) {
            errors.rejectValue("email", "email.exists", "Email is already taken.");
        }

        if(user.getPassword().length() < 5) {
            errors.rejectValue("password", "password.tooshort", "Password must be at least 5 characters long.");
        }

    }

}
