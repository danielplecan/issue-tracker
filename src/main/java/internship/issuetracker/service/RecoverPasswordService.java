/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.service;

import internship.issuetracker.entity.RecoverPassword;
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
public class RecoverPasswordService {
    
    
    @PersistenceContext
    private EntityManager entityManager;

    public String createRecovery(User newUser) {
        RecoverPassword recovery = new RecoverPassword();
        recovery.setUser(newUser);
        recovery.setRecoverPasswordHash(KeyGenerators.string().generateKey());
        entityManager.persist(recovery);
        
        return recovery.getRecoverPasswordHash();
    }
    public User getUserByRecoveryHash(String recoveryHash){
        TypedQuery<RecoverPassword> recoveryQuery = entityManager.createNamedQuery(RecoverPassword.FIND_BY_RECOVERY_HASH,RecoverPassword.class);
        recoveryQuery.setParameter("v_recoverPasswordHash", recoveryHash);

        List<RecoverPassword> resultList = recoveryQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0).getUser();   
    }
}
