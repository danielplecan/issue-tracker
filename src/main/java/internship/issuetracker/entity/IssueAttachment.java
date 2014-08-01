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
 * @author dplecan
 */

@NamedQueries({
    @NamedQuery(name = IssueAttachment.FIND_BY_ISSUE_ID, query = "SELECT u from IssueAttachment u WHERE u.issue = :v_issue"),
    @NamedQuery(name = IssueAttachment.REMOVE_BY_ATTACHMENT_ID, query = "DELETE from IssueAttachment where attachment.id = :attachment_id"),
    @NamedQuery(name = IssueAttachment.REMOVE_BY_ISSUE_ID, query = "DELETE from IssueAttachment u WHERE u.issue.id = :v_issue_id")
})

@Entity
@Table(name="en_issue_attachments")
public class IssueAttachment implements Serializable{
    
    public static final String FIND_BY_ISSUE_ID = "findAllIssueAttachmentsByIssue";
    
    public static final String REMOVE_BY_ATTACHMENT_ID = "removeIssueAttachmentsByAttachmentId";
    
    public static final String REMOVE_BY_ISSUE_ID = "removeIssueAttachmentsByIssueId";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_issue_attachments_id_seq", sequenceName = "en_issue_attachments_id_seq", allocationSize = 1)
    private Long id;
       
    @ManyToOne
    @JoinColumn(name = "id_issue")
    private Issue issue;
    
    @ManyToOne
    @JoinColumn(name = "id_attachment")
    private UploadedFile attachment;
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }    

    public UploadedFile getAttachment() {
        return attachment;
    }

    public void setAttachment(UploadedFile attachment) {
        this.attachment = attachment;
    }
}
