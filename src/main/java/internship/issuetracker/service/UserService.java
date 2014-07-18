package internship.issuetracker.service;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dplecan
 */
@Service
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public void registerUser(UserDTO userDTO) {
        User user = userDTO.getUserFromDTO();

        entityManager.persist(user);
    }

    public boolean usernameExists(String username) {
        User user = getUserByUsername(username);

        return user == null;
    }

    public boolean emailExists(String email) {
        User user = getUserByEmail(email);

        return user == null;
    }

    public User getUserByUsername(String username) {
        TypedQuery<User> userQuery = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        userQuery.setParameter("v_username", username);

        List<User> resultList = userQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }

    public User getUserByEmail(String email) {
        TypedQuery<User> userQuery = entityManager.createNamedQuery(User.FIND_BY_EMAIL, User.class);
        userQuery.setParameter("v_email", email);

        List<User> resultList = userQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }

    public User loginUser(String username, String password) {
        if (password == null || username == null) {
            return null;
        }
        
        User currentUser = getUserByUsername(username);

        if (currentUser == null) {
            return null;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, currentUser.getPasswordHash()) ? currentUser : null;
    }
}
