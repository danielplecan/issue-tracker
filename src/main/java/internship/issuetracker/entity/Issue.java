package internship.issuetracker.entity;

import internship.issuetracker.util.DateFormatUtil;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author atataru
 */
@NamedQueries({
    @NamedQuery(name = Issue.FIND_ALL, query = "SELECT u from Issue u"),
    @NamedQuery(name = Issue.ORDERED_ISSUES, query = "SELECT u from Issue u ORDER BY u.updateDate DESC"),
    @NamedQuery(name = Issue.FIND_USERS_ISSUES_OWNERS, query = "SELECT DISTINCT u.owner from Issue u WHERE u.owner.username LIKE :v_username "),
    @NamedQuery(name = Issue.FIND_USERS_ASSIGNEES, query = "SELECT DISTINCT u.assignee from Issue u WHERE u.assignee.username LIKE :v_username ")
})

@Entity
@Table(name = "en_issues")
public class Issue implements Serializable {

    public static final String FIND_ALL = "findAllIssues";
    public static final String ORDERED_ISSUES = "getAllIssuesOrderedByDate";
    public static final String FIND_USERS_ISSUES_OWNERS = "findAllIssuesOwners";
    public static final String FIND_USERS_ASSIGNEES= "findAllAssignees";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_issues_id_seq", sequenceName = "en_issues_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "issue_title", length = 50)
    @Size(min = 3, max = 50, message = "The title must have between 3 and 50 characters.")
    private String title;

    @Basic(optional = true)
    @Column(name = "issue_content", length = 500)
    @Size(max = 500, message = "The content must have a maximum of 500 characters.")
    private String content;

    @Basic(optional = false)
    @Column(name = "issue_state")
    private IssueState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "last_updated_by")
    private User lastUpdatedBy;
    
    public Issue() {
        state = IssueState.OPEN;
    }

    public User getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(User lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User user) {
        this.assignee = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IssueState getState() {
        return state;
    }

    public void setState(IssueState state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = new Date(updateDate.getTime());
    }

    public String getDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(this.date);
    }

    public String getLastUpdateDate() {
        return DateFormatUtil.getFriendlyInterval(updateDate);
    }

    public String getTimeInterval() {
        return DateFormatUtil.getFriendlyInterval(date);
    }
}
