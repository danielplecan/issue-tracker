package internship.issuetracker.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author inistor
 */
public class EditUserDto extends UserDTO {
    @NotBlank(message = "Password cannot be empty.")
    protected String oldPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
