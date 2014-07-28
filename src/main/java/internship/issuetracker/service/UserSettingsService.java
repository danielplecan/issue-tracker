package internship.issuetracker.service;

import internship.issuetracker.entity.Comment;
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
    
    public boolean toggleNotifications(String username) {
        
        User currentUser = userService.getUserByUsername(username);
        TypedQuery<UserSettings> settingsQuerry = em.createNamedQuery(UserSettings.FIND_SETTINGS_BY_USER_ID, UserSettings.class);
        settingsQuerry.setParameter("v_user", currentUser);

        List<UserSettings> resultList = settingsQuerry.getResultList();
        
        if (resultList.isEmpty()) {
            UserSettings userSettings = new UserSettings();
            userSettings.setUser(currentUser);
            userSettings.setNotifications(Boolean.FALSE);
            em.persist(userSettings);
            return false;
        }
        else {
            UserSettings currentUserSettings = em.find(UserSettings.class, resultList.get(0).getId()) ;
            currentUserSettings.setNotifications(!currentUserSettings.isOn());
            
            em.merge(currentUserSettings);
            return currentUserSettings.isOn();
        }
        
    }
    
    public boolean getCurrentNotificationStatus(String username) {
        User currentUser = userService.getUserByUsername(username);
        TypedQuery<UserSettings> settingsQuerry = em.createNamedQuery(UserSettings.FIND_SETTINGS_BY_USER_ID, UserSettings.class);
        settingsQuerry.setParameter("v_user", currentUser);

        List<UserSettings> resultList = settingsQuerry.getResultList();
        
        if (resultList.isEmpty()) {
            return false;
        }
        else {
            UserSettings currentUserSettings = em.find(UserSettings.class, resultList.get(0).getId());
            return currentUserSettings.isOn();
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    
}
