package internship.issuetracker.service;

import internship.issuetracker.entity.UserSettings;
import internship.issuetracker.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        Query query = em.createNamedQuery(UserSettings.FIND_SETTINGS_BY_USER_ID, UserSettings.class);
        query.setParameter("v_user", targetUser);
        
        List<UserSettings> results = query.getResultList();
        
        if (results.isEmpty()) {
            createSettingsforUser(targetUser.getUsername());
            return false;
        }
             
        
        UserSettings currentUserSettings = results.get(0);
        currentUserSettings.setNotifications(!currentUserSettings.isOn());
        
        em.merge(currentUserSettings);
        return currentUserSettings.isOn();
    }
    
    public boolean getCurrentNotificationStatus(String username) {
        User targetUser = userService.getUserByUsername(username);
        Query query = em.createNamedQuery(UserSettings.FIND_SETTINGS_BY_USER_ID, UserSettings.class);
        query.setParameter("v_user", targetUser);
        
        List<UserSettings> results = query.getResultList();
        
        if (results.isEmpty()) {
            return false;
        }
       
        return results.get(0).isNotifications();
    }
    
    public Long getCurrentThemePreference(String username) {
         User targetUser = userService.getUserByUsername(username);
        Query query = em.createNamedQuery(UserSettings.FIND_SETTINGS_BY_USER_ID, UserSettings.class);
        query.setParameter("v_user", targetUser);
        
        List<UserSettings> results = query.getResultList();
        
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0).getTheme();
    }
    
    public boolean changeUserThemePreference(String username, Long theme) {
         User targetUser = userService.getUserByUsername(username);
        Query query = em.createNamedQuery(UserSettings.FIND_SETTINGS_BY_USER_ID, UserSettings.class);
        query.setParameter("v_user", targetUser);
        
        List<UserSettings> results = query.getResultList();
        
        if (results.isEmpty()) {
            return false;
        }
        UserSettings currentUserSettings = results.get(0);
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
