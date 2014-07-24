package internship.issuetracker.entity;

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
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 *
 * @author atataru
 */
@NamedQueries({
    @NamedQuery(name = Issue.FIND_ALL, query = "SELECT u from Issue u"),
    @NamedQuery(name = Issue.ORDERED_ISSUES, query = "SELECT u from Issue u ORDER BY u.updateDate DESC")
})

@Entity
@Table(name = "en_issues")
public class Issue implements Serializable {

    public static final String FIND_ALL = "findAllIssues";
    public static final String ORDERED_ISSUES = "getAllIssuesOrderedByDate";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_issues_id_seq", sequenceName = "en_issues_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "issue_title", length = 50)
    @Size(min = 3, max = 50, message = "The title must have between 3 and 50 characters.")
    @NotBlank(message = "The title must not be empty.")
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

    public Issue() {
        state = IssueState.OPEN;
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
        this.date = date;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(this.date);
    }
    
    public String getDateFormat2() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.date);
    }
    public String getLastUpdateDate() {
        return getFriendlyInterval(updateDate);
    }
    
    public String getTimeInterval() {
        return  getFriendlyInterval(date);
    }
    private String getFriendlyInterval(Date oldDate) {
        DateTime oldTimeInstant = new DateTime(oldDate);
        DateTime newTimeInstant = new DateTime(new Date());
        Interval interval = new Interval(oldTimeInstant, newTimeInstant);
        StringBuilder result = new StringBuilder();

        long days = interval.toDuration().getStandardDays();

        long hours = interval.toDuration().getStandardHours() - days * 24;

        long minutes = interval.toDuration().getStandardMinutes() - days * 24 * 60 - hours * 60 ;

        long seconds = interval.toDuration().getStandardSeconds() - days * 24 * 60 *60 - hours * 60 * 60 - minutes * 60;

        if (days == 1) {
            result.append("1 day ");
        } else if (days > 1 && days < 14) {
            result.append(days).append(" days  ");
        } else if ( days >= 14) {
          result.append(getDateFormat2());
          return result.toString();
        } else if (hours == 1) {
            result.append(" 1 hour ");
        } else if (hours > 1) {
            result.append(hours).append(" hours ");
        } else if (minutes == 1) {
            result.append(" 1 minute ");
        } else if (minutes > 1) {
            result.append(minutes).append(" minutes ");
        } else if (seconds == 1) {
            result.append(" 1 second ");
        } else {
            result.append(seconds).append(" seconds ");
        }
        //result.append("ago");
        return result.toString();
    }
}
