package internship.issuetracker.entity;

import java.io.Serializable;
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

/**
 *
 * @author scalin
 */

@NamedQueries({
    @NamedQuery(name = IssueLabels.FIND_BY_ISSUE_ID, query = "SELECT u from IssueLabels u WHERE u.issue = :v_issue"),
    @NamedQuery(name = IssueLabels.REMOVE_BY_LABEL_ID, query = "delete from IssueLabels where label.id = :label_id"),
    @NamedQuery(name = IssueLabels.REMOVE_BY_ISSUE_ID, query = "DELETE from IssueLabels u WHERE u.issue.id = :v_issue_id")
})

@Entity
@Table(name="en_issue_labels")
public class IssueLabels implements Serializable{
    
    public static final String FIND_BY_ISSUE_ID = "findAllIssueLabelsByIssue";
    
    public static final String REMOVE_BY_LABEL_ID = "removeIssueLabelsByLabelId";
    
    public static final String REMOVE_BY_ISSUE_ID = "removeIssueLabelsByIssueId";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_issue_labels_id_seq", sequenceName = "en_issue_labels_id_seq", allocationSize = 1)
    private Long id;
       
    @ManyToOne
    @JoinColumn(name = "id_issue")
    private Issue issue;
    
    @ManyToOne
    @JoinColumn(name = "id_label")
    private Label label;
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }    
}
