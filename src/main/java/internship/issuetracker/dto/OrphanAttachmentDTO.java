package internship.issuetracker.dto;

import java.util.List;

public class OrphanAttachmentDTO {
    private List<Long> attachments;

    public List<Long> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Long> attachments) {
        this.attachments = attachments;
    }
}
