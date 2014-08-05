package internship.issuetracker.dto;

import internship.issuetracker.entity.User;
import internship.issuetracker.util.SecurityUtil;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author dplecan
 */
public class UserDTO {

    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 5, max = 60, message = "Name must contain between 5 and 60 characters.")
    private String name;

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 5, max = 20, message = "Username must contain between 5 and 20 characters.")
    private String username;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Not a well-formed email adress.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 5, max = 20, message = "Password must contain between 5 and 20 characters.")
    private String password;

    public User getUserFromDTO() {
        User user = new User();

        user.setName(getName());
        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setPasswordHash(SecurityUtil.encryptPassword(getPassword()));

        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
