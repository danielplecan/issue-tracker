package internship.issuetracker.dto;

import internship.issuetracker.entity.User;
import internship.issuetracker.util.SecurityUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author dplecan
 */
public class UserDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
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
