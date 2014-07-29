package internship.issuetracker.service;

import internship.issuetracker.entity.UserSettings;
import internship.issuetracker.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author scalin
 */

@Service
@Transactional
public class UserSettingsService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private UserService userService;
    
    
    
    public void createSettingsforUser(String username) {
        User targetUser = userService.getUserByUsername(username);
        
        if (targetUser != null) {
            UserSettings userSettings = new UserSettings();
            userSettings.setUser(targetUser);
            userSettings.setNotifications(Boolean.FALSE);
            userSettings.setTheme(1l);
            em.persist(userSettings);
        }
    }
    
    public boolean toggleNotifications(String username) {
        
        User currentUser = userService.getUserByUsername(username);
        
        if (currentUser.getSettings() == null) {
            createSettingsforUser(currentUser.getUsername());
            return false;
        }
        
        UserSettings currentUserSettings = currentUser.getSettings() ;
        currentUserSettings.setNotifications(!currentUserSettings.isOn());
        
        em.merge(currentUserSettings);
        return currentUserSettings.isOn();
    }
    
    public boolean getCurrentNotificationStatus(String username) {
        User currentUser = userService.getUserByUsername(username);
        return currentUser.getSettings().isNotifications();
    }
    
    public Long getCurrentThemePreference(String username) {
        User currentUser = userService.getUserByUsername(username);
        return currentUser.getSettings().getTheme();
    }
    
    public boolean changeUserThemePreference(String username, Long theme) {
        User currentUser = userService.getUserByUsername(username);
        
        UserSettings currentUserSettings = currentUser.getSettings();
        currentUserSettings.setTheme(theme);
            
        em.merge(currentUserSettings);
        return true;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    
}
