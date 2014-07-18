package internship.issuetracker.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author scalin
 */

@Entity
@Table(name="en_issue_labels")
public class IssueLabels implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_issue_labels_id_seq", sequenceName = "en_issue_labels_id_seq", allocationSize = 1)
    private Long id;
       
    @ManyToOne
    @JoinColumn(name = "id_issue")
    private Issue issue;
    
    @ManyToOne(fetch = FetchType.EAGER)
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
