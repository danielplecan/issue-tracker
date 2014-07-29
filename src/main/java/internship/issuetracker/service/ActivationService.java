/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.service;

import internship.issuetracker.entity.Activation;
import internship.issuetracker.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivationService {

    @PersistenceContext
    private EntityManager entityManager;

    public String createActivation(User newUser) {
        Activation activation = new Activation();
        activation.setUser(newUser);
        activation.setActivationHash(KeyGenerators.string().generateKey());
        entityManager.persist(activation);
        
        return activation.getActivationHash();
    }
    public User getUserByActivationHash(String activationHash){
        TypedQuery<Activation> activationQuery = entityManager.createNamedQuery(Activation.FIND_BY_ACTIVATION_HASH, Activation.class);
        activationQuery.setParameter("v_activationHash", activationHash);

        List<Activation> resultList = activationQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0).getUser();   
    }
}
