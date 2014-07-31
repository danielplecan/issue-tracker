package internship.issuetracker.service;

import internship.issuetracker.entity.UserSettings;
import internship.issuetracker.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        User targetUser = userService.getUserByUsername(username);
        
        UserSettings currentUserSettings = targetUser.getSettings();
        currentUserSettings.setNotifications(!currentUserSettings.isOn());
        
        em.merge(currentUserSettings);
        return currentUserSettings.isOn();
    }
    
    public boolean getCurrentNotificationStatus(String username) {
        User targetUser = userService.getUserByUsername(username);
        UserSettings currentUserSettings = targetUser.getSettings();
        
        return currentUserSettings.isNotifications();
    }
    
    public Long getCurrentThemePreference(String username) {
        User targetUser = userService.getUserByUsername(username);
        UserSettings currentUserSettings = targetUser.getSettings();
        
        return currentUserSettings.getTheme();
    }
    
    public boolean changeUserThemePreference(String username, Long theme) {
        User targetUser = userService.getUserByUsername(username);
        
        UserSettings currentUserSettings = targetUser.getSettings();
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
