package internship.issuetracker.dto;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.Label;
import java.util.List;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/**
 *
 * @author dplecan
 */
public class IssueDTO {
    @JsonUnwrapped
    private Issue issue;
    
    private List<Label> labels;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
