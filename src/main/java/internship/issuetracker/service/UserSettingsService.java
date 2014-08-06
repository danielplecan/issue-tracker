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
            userSettings.setNotificationsForPostedIssues(Boolean.FALSE);
            userSettings.setNotificationsForAssignedIssues(Boolean.FALSE);
            userSettings.setTheme(1l);
            em.persist(userSettings);
        }
    }

    public boolean toggleNotificationsForPosted(String username) {
        User targetUser = userService.getUserByUsername(username);

        UserSettings currentUserSettings = targetUser.getSettings();
        currentUserSettings.setNotificationsForPostedIssues(!currentUserSettings.isNotificationsForPostedIssues());

        em.merge(currentUserSettings);
        return currentUserSettings.isNotificationsForPostedIssues();
    }
    
    public boolean toggleNotificationsForAssigned(String username) {
        User targetUser = userService.getUserByUsername(username);

        UserSettings currentUserSettings = targetUser.getSettings();
        currentUserSettings.setNotificationsForAssignedIssues(!currentUserSettings.isNotificationsForAssignedIssues());

        em.merge(currentUserSettings);
        return currentUserSettings.isNotificationsForAssignedIssues();
    }

    public boolean getNotificationStatusForPosted(String username) {
        User targetUser = userService.getUserByUsername(username);
        UserSettings currentUserSettings = targetUser.getSettings();
        return currentUserSettings.isNotificationsForPostedIssues();
    }

    public boolean getNotificationStatusForAssigned(String username) {
        User targetUser = userService.getUserByUsername(username);
        UserSettings currentUserSettings = targetUser.getSettings();
        return currentUserSettings.isNotificationsForAssignedIssues();
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
