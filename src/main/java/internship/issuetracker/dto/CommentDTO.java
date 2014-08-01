package internship.issuetracker.dto;

import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.IssueState;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dplecan
 */
public class CommentDTO{
    private Long id;
    private String content;
    private IssueState changeState; 
    private Date date;
    private List<Long> attachments;
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IssueState getChangeState() {
        return changeState;
    }

    public void setChangeState(IssueState changeState) {
        this.changeState = changeState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Long> attachments) {
        this.attachments = attachments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getCommentFromDTO() {
        Comment comment = new Comment();
        comment.setChangeState(this.getChangeState());
        comment.setContent(this.getContent());
        comment.setDate(this.getDate());
        
        return comment;
    }
}
