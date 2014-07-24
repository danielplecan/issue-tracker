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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author atataru
 */
@NamedQueries({
    @NamedQuery(name = Comment.FIND_BY_ISSUE_ID, query = "SELECT u from Comment u WHERE u.issue = :v_issue"),
    @NamedQuery(name = Comment.FIND_BY_ID, query = "SELECT u from Comment u WHERE u.id = :comment_id"),
    @NamedQuery(name = Comment.FIND_BETWEEN_INTERVAL, 
            query = "FROM Comment AS c WHERE (c.issue.id = :issue_id) AND (c.date > :stDate AND c.date <= :edDate) ")
})
@Entity
@Table(name = "en_comments")
public class Comment implements Serializable{

    public static final String FIND_BY_ISSUE_ID = "findAllCommentsByIssue";
    public static final String FIND_BY_ID = "findCommentById";
    public static final String FIND_BETWEEN_INTERVAL = "findCommentsBetweenInterval";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_comments_id_seq", sequenceName = "en_comments_id_seq", allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name ="comment_content", length = 500)
    @NotBlank(message = "A comment must not be empty.")
    @Size(min = 3, max = 500, message="A comment must contain between 3 and 500 characters")
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "id_author")
    private User author;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_issue")
    private Issue issue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(this.date);
    }
}
