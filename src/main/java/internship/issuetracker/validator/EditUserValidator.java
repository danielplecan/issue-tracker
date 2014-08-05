package internship.issuetracker.validator;

import internship.issuetracker.dto.EditUserDto;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author inistor
 */
@Service
public class EditUserValidator implements Validator {

    @Autowired
    private UserService userService;

    private User oldUser;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditUserDto user = (EditUserDto) target;

        
        if (!oldUser.getUsername().equals(user.getUsername())&&userService.usernameExists(user.getUsername())) {
            errors.rejectValue("username", "username.exists", "Username is already taken.");
        }

        if (!oldUser.getEmail().equals(user.getEmail())&&userService.emailExists(user.getEmail())) {
            errors.rejectValue("email", "email.exists", "Email is already taken.");
        }
        if (!user.getPassword().isEmpty()&&user.getPassword().length() < 5) {
            errors.rejectValue("password", "password.tooshort", "Password must be at least 5 characters long.");
        }

    }

    public User getOldUser() {
        return oldUser;
    }

    public void setOldUser(User oldUser) {
        this.oldUser = oldUser;
    }
}
