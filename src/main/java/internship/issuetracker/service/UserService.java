/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

//crw: removing methods might help you set @Transactional at class level instead of method level
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
        TypedQuery<User> userQuery = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        userQuery.setParameter("v_username", username);

        List<User> resultList = userQuery.getResultList();

        return resultList != null && !resultList.isEmpty();
    }

    public boolean emailExists(String email) {
        TypedQuery<User> userQuery = entityManager.createNamedQuery(User.FIND_BY_EMAIL, User.class);
        userQuery.setParameter("v_email", email);

        List<User> resultList = userQuery.getResultList();

        return resultList != null && !resultList.isEmpty();
    }

    //crw: this method may be put inside a security util

    //crw: this method may have a better place in UserDTO class

    public boolean loginUser(String username, String password) {
        if (password == null || username == null) {
            return false;
        }
        TypedQuery<User> userQuery = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        userQuery.setParameter("v_username", username);

        List<User> resultList = userQuery.getResultList();

        if(resultList == null || resultList.isEmpty()) {
            return false;
        }

        User currentUser = resultList.get(0);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(password, currentUser.getPasswordHash());
    }
}
