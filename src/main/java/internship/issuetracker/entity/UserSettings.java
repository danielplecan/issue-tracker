package internship.issuetracker.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author scalin
 */

@NamedQuery(name = UserSettings.FIND_SETTINGS_BY_USER_ID, query = "SELECT u from UserSettings u WHERE u.user = :v_user")

@Entity
@Table(name="en_user_settings")
public class UserSettings implements Serializable{
    
    public static final String FIND_SETTINGS_BY_USER_ID = "findSettingsByUser";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_settings_id_seq", sequenceName = "en_settings_id_seq", allocationSize = 1)
    Long id;
    
    @OneToOne
    @Basic(optional = false)
    @JoinColumn(name="user_id")
    User user;

    @Basic(optional=false)
    @Column(name="notifications")
    Boolean notifications;

    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOn(Boolean notifications) {
        this.notifications = notifications;
    }
    
    public Boolean isOn() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }
    
    public Boolean isNotifications() {
        return notifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
