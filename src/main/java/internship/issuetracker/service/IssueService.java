package internship.issuetracker.service;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.User;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
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
     * @param issue
     * @param owner
     */
    public void createIssue(Issue issue, User owner){
        //save issue in database
        
        issue.setDate(new Date());
        issue.setOwner(owner);
        em.persist(issue);
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
