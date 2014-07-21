package internship.issuetracker.service;

import org.springframework.stereotype.Service;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.IssueLabels;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import java.util.ArrayList;

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
        issue.setUpdateDate(new Date());
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
            issue.setDate(new Date());
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
    
    public List<Issue> getIssuesOrderedByDate(){
        TypedQuery<Issue> issueQuery = em.createNamedQuery(Issue.ORDERED_ISSUES, Issue.class);
        return issueQuery.getResultList();
    }
    
    /**
     * Method for creating a comment
     *
     * @param author
     * @param issueId
     * @param comment
     * @return created comment
     */
    public Comment addComment(User author, Long issueId, Comment comment) {
        Issue issue = getIssueById(issueId);

        comment.setAuthor(author);
        comment.setIssue(issue);
        comment.setDate(new Date());

        em.persist(comment);

        return comment;
    }
    
    public List<Comment> getCommentsByIssueId(Issue issue) {
        TypedQuery<Comment> userQuery = em.createNamedQuery(Comment.FIND_BY_ISSUE_ID, Comment.class);
        userQuery.setParameter("v_issue", issue);

        List<Comment> resultList = userQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        }       
        return resultList;
    }
    
    public List<Label> getLabelsByIssueId(Issue issue) {
        TypedQuery<IssueLabels> userQuery = em.createNamedQuery(IssueLabels.FIND_BY_ISSUE_ID, IssueLabels.class);
        userQuery.setParameter("v_issue", issue);

        List<IssueLabels> resultList = userQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        } 
        
        List<Label> finalList = new ArrayList<>();
        
        for (IssueLabels l : resultList){
            finalList.add(l.getLabel());
        }
        return finalList;
    }
    
}
