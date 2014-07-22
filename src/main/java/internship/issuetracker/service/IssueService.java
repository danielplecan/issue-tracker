package internship.issuetracker.service;

import internship.issuetracker.dto.IssueDTO;
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
     *
     * @param issue
     * @param owner
     */
    public void createIssue(Issue issue, User owner) {
        //save issue in database

        issue.setDate(new Date());
        issue.setUpdateDate(new Date());
        issue.setOwner(owner);
        em.persist(issue);
    }

    /**
     * 
     * @param issueDto encapsulates an issue, and a list of id for existing labels
     * @param owner 
     */
    public void createIssueFromIssueDTO(IssueDTO issueDto, User owner) {
        Issue issue = issueDto.getIssue();
        Date currentDate = new Date();
        issue.setDate(currentDate);
        issue.setUpdateDate(currentDate);
        issue.setOwner(owner);
        em.persist(issue);
        
        for (Long labelId : issueDto.getLabelIdList()) {
            Label label = em.find(Label.class, labelId);
            if( label == null)
                continue;
            IssueLabels issueLabel = new IssueLabels();
            issueLabel.setIssue(issue);
            issueLabel.setLabel(label); 
            em.persist(issueLabel);
        }
    }
    
    public Issue getIssueById(Long id) {
        Issue result = em.find(Issue.class, id);
        return result;
    }

    public boolean updateIssueState(long issueId, IssueState newState) {
        Issue issue = em.find(Issue.class, issueId);

        //in case an issue with this id exists
        if (issue != null) {
            issue.setState(newState);
            issue.setUpdateDate(new Date());
            em.merge(issue);
            return true;
        }

        //in case it doesn't
        return false;
    }

    public boolean changeStateOfIssue(Long issueId, String stateAction) {
        Issue issue = em.find(Issue.class, issueId);

        if (issue == null) {
            return false;
        }

        switch (stateAction) {
            case "close":
                issue.setState(IssueState.CLOSED);
                return true;

            case "reopen":
                if (issue.getState() == IssueState.CLOSED) {
                    issue.setState(IssueState.REOPENED);
                    return true;
                }

                return true;
                
            default:
                return false;
        }
    }

    /**
     * Get all issues present in the database.
     *
     * @return a list containing all the issues
     */
    public List<Issue> getIssues() {
        TypedQuery<Issue> issueQuery = em.createNamedQuery(Issue.FIND_ALL, Issue.class);
        return issueQuery.getResultList();
    }

    public List<Issue> getIssuesOrderedByDate() {
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
        Issue issue = em.find(Issue.class, issueId);
        issue.setUpdateDate(new Date());

        comment.setAuthor(author);
        comment.setIssue(issue);
        comment.setDate(new Date());

        em.persist(comment);
        em.merge(issue);
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

        for (IssueLabels l : resultList) {
            finalList.add(l.getLabel());
        }
        return finalList;
    }

}
