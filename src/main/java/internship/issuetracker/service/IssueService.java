package internship.issuetracker.service;

import internship.issuetracker.dto.IssueDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueAttachment;
import internship.issuetracker.entity.IssueLabel;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.UploadedFile;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.FilterResult;
import internship.issuetracker.filter.QueryFilter;
import internship.issuetracker.order.QueryOrder;
import internship.issuetracker.util.IssueEditUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MailService mailService;

    @Autowired
    private FileUploadService fileUploadService;
    

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
        issue.setLastUpdatedBy(owner);
        em.persist(issue);

        for (Long labelId : issueDto.getLabelIdList()) {
            Label label = em.find(Label.class, labelId);
            if (label == null) {
                continue;
            }
            IssueLabel issueLabel = new IssueLabel();
            issueLabel.setIssue(issue);
            issueLabel.setLabel(label);
            em.persist(issueLabel);
        }

        for (Long attachment : issueDto.getAttachments()) {
            UploadedFile uploadedFile = em.find(UploadedFile.class, attachment);
            if (uploadedFile == null) {
                continue;
            }
            IssueAttachment issueAttachment = new IssueAttachment();
            issueAttachment.setIssue(issue);
            issueAttachment.setAttachment(uploadedFile);
            em.persist(issueAttachment);
        }

        return issue.getId();
    }

    public Issue getIssueById(Long id) {
        return em.find(Issue.class, id);
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

    public boolean updateAssignee(long issueId, User assignee, User currentUser) {
        Issue issue = em.find(Issue.class, issueId);

        //in case an issue with this id exists
        if (issue != null) {
            issue.setAssignee(assignee);
            issue.setUpdateDate(new Date());
            issue.setLastUpdatedBy(currentUser);
            em.merge(issue);
            return true;
        }
        //in case it doesn't
        return false;
    }

    public boolean changeStateOfAnIssueToNewState(Issue issue, IssueState newState) {
        if (issue.getState() != newState) {
            issue.setState(newState);
            issue.setUpdateDate(new Date());
            em.merge(issue);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeStateOfIssue(Long issueId, IssueState newState, User currentUser) {
        Issue issue = em.find(Issue.class, issueId);

        if (issue == null || newState == null) {
            return false;
        }
        issue.setLastUpdatedBy(currentUser);
        switch (newState) {
            case CLOSED:
            case OPEN:
                return changeStateOfAnIssueToNewState(issue, newState);
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
        issue.setLastUpdatedBy(author);
        if (comment.getChangeState() != null) {
            this.changeStateOfIssue(issue.getId(), comment.getChangeState(), author);
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
        userQuery.setParameter(Comment.V_ISSUE_PARAMETER, issue);

        List<Comment> resultList = userQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<>();
        }
        return resultList;
    }

    public List<Label> getLabelsByIssueId(Issue issue) {
        TypedQuery<IssueLabel> userQuery = em.createNamedQuery(IssueLabel.FIND_BY_ISSUE_ID, IssueLabel.class);
        userQuery.setParameter(Comment.V_ISSUE_PARAMETER, issue);

        List<IssueLabel> resultList = userQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        }

        List<Label> finalList = new ArrayList<>();

        for (IssueLabel l : resultList) {
            finalList.add(l.getLabel());
        }
        return finalList;
    }

    public List<UploadedFile> getAttachmentsByIssueId(Issue issue) {
        TypedQuery<IssueAttachment> userQuery = em.createNamedQuery(IssueAttachment.FIND_BY_ISSUE_ID, IssueAttachment.class);
        userQuery.setParameter(Comment.V_ISSUE_PARAMETER, issue);

        List<IssueAttachment> resultList = userQuery.getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return null;
        }

        List<UploadedFile> finalList = new ArrayList<>();

        for (IssueAttachment l : resultList) {
            finalList.add(l.getAttachment());
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

    public Label getLabelByName(String labelName) {
        TypedQuery<Label> labelQuery = em.createNamedQuery(Label.FIND_LABEL_BY_NAME, Label.class);
        labelQuery.setParameter(Label.LABEL_NAME_PARAMETER, labelName);
        List<Label> labelList = labelQuery.getResultList();
        return labelList.isEmpty() ? null : labelList.get(0);
    }

    public Label getLabelById(Long labelId) {
        return em.find(Label.class, labelId);
    }

    public boolean labelExists(String labelName) {
        TypedQuery<Label> labelQuery = em.createNamedQuery(Label.FIND_LABEL_BY_NAME, Label.class);
        labelQuery.setParameter(Label.LABEL_NAME_PARAMETER, labelName);
        return !labelQuery.getResultList().isEmpty();
    }

    public void removeIssueLabels(Long labelId) {
        Query issueLabelQuery = em.createNamedQuery(IssueLabel.REMOVE_BY_LABEL_ID);
        issueLabelQuery.setParameter("label_id", labelId);
        issueLabelQuery.executeUpdate();

    }

    public boolean removeLabel(Long labelId) {
        Label label = em.find(Label.class, labelId);
        if (label == null) {
            return false;
        }

        removeIssueLabels(label.getId());
        em.remove(label);
        return true;
    }

    public Label updateLabel(Label label) {
        Label labelToUpdate = em.find(Label.class, label.getId());
        labelToUpdate.setName(label.getName());
        labelToUpdate.setColor(label.getColor());
        return em.merge(labelToUpdate);
    }

    public List<Comment> getMissedComments(long issueId, long commentId) {
        if (commentId < 0) {
            TypedQuery<Comment> commentQuery2 = em.createNamedQuery(Comment.FIND_BY_ISSUE_ID, Comment.class);
            commentQuery2.setParameter(Comment.V_ISSUE_PARAMETER, this.getIssueById(issueId));
            return commentQuery2.getResultList();
        }

        TypedQuery<Comment> commentQuery = em.createNamedQuery(Comment.FIND_BY_ID, Comment.class);
        commentQuery.setParameter("comment_id", commentId);
        List<Comment> commentsList;
        commentsList = commentQuery.getResultList();
        Comment comment = commentsList.isEmpty() ? null : commentsList.get(0);

        if (comment == null) {
            TypedQuery<Comment> commentQuery2 = em.createNamedQuery(Comment.FIND_BY_ISSUE_ID, Comment.class);
            commentQuery2.setParameter(Comment.V_ISSUE_PARAMETER, this.getIssueById(issueId));
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

    public void sendNotificationForComment(Comment comment, User target, String link) {
        if (!comment.getAuthor().getId().equals(target.getId())) {
            Map<String, Object> map = new HashMap<>();
            map.put("link", link + "/issue/" + comment.getIssue().getId());
            map.put("linkText", "Click here to see the issue");
            if (comment.getChangeState() == null) {
                String emailContent = comment.getAuthor().getName() + " commented on the issue with the title ";
                emailContent += comment.getIssue().getTitle();
                map.put("text", emailContent);
                mailService.sendEmail(target.getEmail(), "Issue-Tracker Notification", map);
            } else {
                String emailContent = comment.getAuthor().getName() + " changed the state of the issue with the title ";
                emailContent += comment.getIssue().getTitle() + " to " + comment.getChangeState();
                map.put("text", emailContent);
                mailService.sendEmail(target.getEmail(), "Issue-Tracker Notification", map);
            }
        }
    }

    public void sendNotificationForAssign(Issue issue, User loggedUser, String link) {
        if (!issue.getAssignee().getId().equals(loggedUser.getId())) {
            Map<String, Object> map = new HashMap<>();
            map.put("link", link + "/issue/" + issue.getId());
            map.put("linkText", "Click here to see the issue");
            String emailContent = "You were assigned on the issue with the title ";
            emailContent += issue.getTitle();
            map.put("text", emailContent);
            mailService.sendEmail(issue.getAssignee().getEmail(), "Issue-Tracker Notification", map);
        }
    }

    public void sendNotificationForEdit(Issue issue, String link) {

        Map<String, Boolean> changes = IssueEditUtil.getChanges();
        String text = " The following items has been modified: ";
        if (changes.get("title") == true) {
            text += " title ";
        }
        if (changes.get("content") == true) {
            text += " content ";
        }
        if (changes.get("labels") == true) {
            text += " labels ";
        }

        if (!issue.getOwner().getId().equals(issue.getLastUpdatedBy().getId())) {
            Map<String, Object> map = new HashMap<>();
            map.put("link", link + "/issue/" + issue.getId());
            map.put("linkText", "Click here to see the issue");
            String emailContent = "The issue you created, with the title ";
            emailContent += issue.getTitle() + " has been edited by " + issue.getLastUpdatedBy().getName() + text;

            map.put("text", emailContent);
            mailService.sendEmail(issue.getOwner().getEmail(), "Issue-Tracker Notification", map);
        }
        if (issue.getAssignee() != null) {
            if (!issue.getAssignee().getId().equals(issue.getOwner().getId())) {
                if (!issue.getAssignee().getId().equals(issue.getLastUpdatedBy().getId())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("link", link + "/issue/" + issue.getId());
                    map.put("linkText", "Click here to see the issue");
                    String emailContent = "The issue you are assigned to, with the title ";
                    emailContent += issue.getTitle() + " has been edited by " + issue.getLastUpdatedBy().getName() + text;
                    map.put("text", emailContent);
                    mailService.sendEmail(issue.getAssignee().getEmail(), "Issue-Tracker Notification", map);
                }
            }
        }

    }

    public List<User> findUsersIssuesOwnersByNamePrefix(String usernamePrefix) {
        TypedQuery<User> resultQuery = em.createNamedQuery(Issue.FIND_USERS_ISSUES_OWNERS, User.class);
        resultQuery.setParameter("v_username", usernamePrefix + "%");

        return resultQuery.getResultList();
    }

    public List<User> findUsersAssigneesByNamePrefix(String usernamePrefix) {
        TypedQuery<User> resultQuery = em.createNamedQuery(Issue.FIND_USERS_ASSIGNEES, User.class);
        resultQuery.setParameter("v_username", usernamePrefix + "%");

        return resultQuery.getResultList();
    }

    public Issue editIssueFromIssueDTO(NewIssueDTO issueDTO, User currentUser) {
        Issue issue = em.find(Issue.class, issueDTO.getIssue().getId());

        List<Issue> issueList = new ArrayList<>();
        issueList.add(issue);

        List<IssueDTO> issueDTOList = getDTOsFromIssues(issueList);

        IssueEditUtil.storeChanges(issueDTO, issueDTOList.get(0));

        issue.setTitle(issueDTO.getIssue().getTitle());
        issue.setContent(issueDTO.getIssue().getContent());
        issue.setLastUpdatedBy(currentUser);
        issue.setUpdateDate(new Date());
        em.merge(issue);

        List<UploadedFile> oldAttachments = this.getAttachmentsByIssueId(issue);

        removeAllLabelsFromAnIssue(issue.getId());
        removeAllAttachmentsFromAnIssue(issue.getId());

        for (Long labelId : issueDTO.getLabelIdList()) {
            Label label = em.find(Label.class, labelId);
            if (label == null) {
                continue;
            }
            IssueLabel issueLabel = new IssueLabel();
            issueLabel.setIssue(issue);
            issueLabel.setLabel(label);
            em.persist(issueLabel);
        }

        for (Long attachment : issueDTO.getAttachments()) {
            UploadedFile uploadedFile = em.find(UploadedFile.class, attachment);
            if (uploadedFile == null) {
                continue;
            }
            IssueAttachment issueAttachment = new IssueAttachment();
            issueAttachment.setIssue(issue);
            issueAttachment.setAttachment(uploadedFile);
            em.persist(issueAttachment);
        }

        List<Long> attachmentsId = new ArrayList<>();
        if (oldAttachments != null && !oldAttachments.isEmpty()) {
            for (UploadedFile attachment : oldAttachments) {
                attachmentsId.add(attachment.getId());
            }

            fileUploadService.removeOrphanAttachments(attachmentsId);
        }

        return issue;
    }

    public void removeAllLabelsFromAnIssue(Long issueId) {
        Query query = em.createNamedQuery(IssueLabel.REMOVE_BY_ISSUE_ID);
        query.setParameter("v_issue_id", issueId);
        query.executeUpdate();
    }

    public void removeAllAttachmentsFromAnIssue(Long issueId) {
        Query query = em.createNamedQuery(IssueAttachment.REMOVE_BY_ISSUE_ID);
        query.setParameter("v_issue_id", issueId);
        query.executeUpdate();
    }
}
