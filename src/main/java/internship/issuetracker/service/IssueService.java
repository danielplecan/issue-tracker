/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import internship.issuetracker.entity.Issue;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author atataru
 */

@Service
@Transactional
public class IssueService {
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Create a new issue and persist it in the database;
     * @param title - the title of post
     * @param content - the content of post(can be null)
     * @param user  - the user who created the post
     */
    public Issue createIssue(Issue issue){
        //save issue in database
        em.persist(issue);
        return issue;
    }
    
    public Issue getIssueById(Long id){
        Issue result = em.find(Issue.class, id);
        return result;
    }
}
