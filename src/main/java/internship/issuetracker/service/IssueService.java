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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import internship.issuetracker.entity.IssueState;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author atataru
 */

@Service
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
    
    public boolean updateIssueState(long issueId, IssueState newState) {
        Issue issue = em.find(Issue.class, issueId);
        
        //in case an issue with this id exists
        if(issue != null) {
            issue.setState(newState);
            em.merge(issue);
            return true;
        }
        
        //in case it doesn't
        return false;
    }
    
    /**
     * Get all issues present in the database.
     * @return  a list containing all the issues
     */
    public List<Issue> getIssues() {
        TypedQuery<Issue> issueQuery = em.createNamedQuery(Issue.FIND_ALL, Issue.class);
        return issueQuery.getResultList();
    }
}
