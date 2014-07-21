package internship.issuetracker.entity;

import java.io.Serializable;
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

/**
 *
 * @author atataru
 */
@NamedQueries({
    @NamedQuery(name = Comment.FIND_BY_ISSUE_ID, query = "SELECT u from Comment u WHERE u.issue = :v_issue")
})
@Entity
@Table(name = "en_comments")
public class Comment implements Serializable{

    public static final String FIND_BY_ISSUE_ID = "findAllCommentsByIssue";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_comments_id_seq", sequenceName = "en_comments_id_seq", allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name ="comment_content")
    @NotBlank(message = "A comment must not be empty.")
    @Size(min = 3, max = 100, message="A comment must contain between 3 and 100 chars")
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "id_author")
    private User author;


    @ManyToOne
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
}
