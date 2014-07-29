package internship.issuetracker.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author dplecan
 */
@NamedQueries({
    @NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u from User u WHERE u.username = :v_username"),
    @NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u from User u WHERE u.email = :v_email")
})

@Entity
@Table(name = "en_users")
public class User implements Serializable {

    public static final String FIND_BY_USERNAME = "findByUsername";
    public static final String FIND_BY_EMAIL = "findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_users_id_seq", sequenceName = "en_users_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    private String email;

    @JsonIgnore
    @Column(name = "password_hash", length = 60, nullable = false)
    @NotBlank
    private String passwordHash;
    
    @Column(name = "active", nullable = false)
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
}
