package internship.issuetracker.service;

import internship.issuetracker.dto.IssueDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueLabels;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Issue_;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.FilterResult;
import internship.issuetracker.filter.QueryFilter;
import internship.issuetracker.order.QueryOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
     *
     * @param issueDto encapsulates an issue, and a list of id for existing
     * labels
     * @param owner
     * @return the id of the created issue
     */
    public long createIssueFromIssueDTO(NewIssueDTO issueDto, User owner) {
        Issue issue = issueDto.getIssue();
        Date currentDate = new Date();
        issue.setDate(currentDate);
        issue.setUpdateDate(currentDate);
        issue.setOwner(owner);
        em.persist(issue);

        for (Long labelId : issueDto.getLabelIdList()) {
            Label label = em.find(Label.class, labelId);
            if (label == null) {
                continue;
            }
            IssueLabels issueLabel = new IssueLabels();
            issueLabel.setIssue(issue);
            issueLabel.setLabel(label);
            em.persist(issueLabel);
        }
        return issue.getId();
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

    public boolean updateAssignee(long issueId, User assignee) {
        Issue issue = em.find(Issue.class, issueId);

        //in case an issue with this id exists
        if (issue != null) {
            issue.setAssignee(assignee);
            issue.setUpdateDate(new Date());
            em.merge(issue);
            return true;
        }
        //in case it doesn't
        return false;
    }

    public boolean changeStateOfIssue(Long issueId, IssueState newState) {
        Issue issue = em.find(Issue.class, issueId);

        if (issue == null || newState == null) {
            return false;
        }

        switch (newState) {
            case CLOSED:
                if (issue.getState() != IssueState.CLOSED) {
                    issue.setState(newState);
                    issue.setUpdateDate(new Date());
                    em.merge(issue);
                    return true;
                } else {
                    return false;
                }
            case OPEN:
                if (issue.getState() != IssueState.OPEN) {
                    issue.setState(newState);
                    issue.setUpdateDate(new Date());
                    em.merge(issue);
                    return true;
                } else {
                    return false;
                }
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
        if (comment.getChangeState() != null) {
            this.changeStateOfIssue(issue.getId(), comment.getChangeState());
        }

        comment.setId(null);
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

    public List<Label> getAllLabels() {
        TypedQuery<Label> labelQuery;
        labelQuery = em.createNamedQuery(Label.FIND_ALL_LABELS, Label.class);
        return labelQuery.getResultList();
    }

    public FilterResult filterIssues(List<QueryFilter<Issue>> filters, QueryOrder order, Integer pageNumber, Integer itemsPerPage) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Issue> root = criteriaQuery.from(Issue.class);

        Predicate[] predicatesArray = buildPredicatesFromFilters(filters, criteriaBuilder, criteriaQuery, root);

        criteriaQuery.where(predicatesArray);
        criteriaQuery.select(root);
        criteriaQuery.orderBy(order.buildOrder(criteriaBuilder, criteriaQuery, root));

        countQuery.select(criteriaBuilder.count(countQuery.from(Issue.class)));
        countQuery.where(predicatesArray);

        TypedQuery<Issue> resultQuery = em.createQuery(criteriaQuery);
        resultQuery.setMaxResults(itemsPerPage);
        resultQuery.setFirstResult((pageNumber - 1) * itemsPerPage);

        TypedQuery<Long> countResultQuery = em.createQuery(countQuery);

        Long totalResultCount = countResultQuery.getSingleResult();
        FilterResult filterResult = new FilterResult();
        filterResult.setIssues(getDTOsFromIssues(resultQuery.getResultList()));
        filterResult.setTotalResultCount(totalResultCount);
        filterResult.setNumberOfPages((long) Math.ceil((double) totalResultCount / itemsPerPage));
        filterResult.setCurrentPage(pageNumber.longValue());
        filterResult.setNumberOfItemsPerPage(itemsPerPage.longValue());

        return filterResult;
    }

    public List<IssueDTO> getDTOsFromIssues(List<Issue> issues) {
        List<IssueDTO> issueDTOs = new ArrayList<>();
        for (Issue issue : issues) {
            List<Label> labels = getLabelsByIssueId(issue);
            IssueDTO issueDTO = new IssueDTO();
            issueDTO.setIssue(issue);
            issueDTO.setLabels(labels);
            issueDTOs.add(issueDTO);
        }
        return issueDTOs;
    }

    private Predicate[] buildPredicatesFromFilters(List<QueryFilter<Issue>> filters, CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        List<Predicate> predicates = new ArrayList<>();
        for (QueryFilter<Issue> filter : filters) {
            Predicate predicate = filter.buildPredicate(criteriaBuilder, criteriaQuery, root);
            if (predicate != null) {
                predicates.add(predicate);
            }
        }
        Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
        return predicatesArray;
    }

    public Label createLabel(Label label) {
        em.persist(label);
        return label;
    }

    public boolean labelExists(String labelName) {
        TypedQuery<Label> labelQuery = em.createNamedQuery(Label.FIND_LABEL_BY_NAME, Label.class);
        labelQuery.setParameter("label_name", labelName);
        return !labelQuery.getResultList().isEmpty();
    }

    public List<Comment> getMissedComments(long issueId, long commentId) {
        if (commentId < 0) {
            TypedQuery<Comment> commentQuery2 = em.createNamedQuery(Comment.FIND_BY_ISSUE_ID, Comment.class);
            commentQuery2.setParameter("v_issue", this.getIssueById(issueId));
            return commentQuery2.getResultList();
        }

        TypedQuery<Comment> commentQuery = em.createNamedQuery(Comment.FIND_BY_ID, Comment.class);
        commentQuery.setParameter("comment_id", commentId);
        List<Comment> commentsList;
        commentsList = commentQuery.getResultList();
        Comment comment = commentsList.isEmpty() ? null : commentsList.get(0);

        if (comment == null) {
            TypedQuery<Comment> commentQuery2 = em.createNamedQuery(Comment.FIND_BY_ISSUE_ID, Comment.class);
            commentQuery2.setParameter("v_issue", this.getIssueById(issueId));
            return commentQuery2.getResultList();
        } else if (comment.getIssue().getId() != issueId) {
            return new ArrayList<>();
        } else {
            TypedQuery<Comment> commentQuery3 = em.createNamedQuery(Comment.FIND_BETWEEN_INTERVAL, Comment.class);
            commentQuery3.setParameter("issue_id", issueId).
                    setParameter("stDate", comment.getDate()).
                    setParameter("edDate", new Date());
            return commentQuery3.getResultList();
        }
    }
}
