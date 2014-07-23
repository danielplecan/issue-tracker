package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import java.util.List;

/**
 *
 * @author dplecan
 */
public class FilterResult {
    private List<Issue> issues;
    private Long numberOfPages;
    private Long totalResultCount;

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Long getTotalResultCount() {
        return totalResultCount;
    }

    public void setTotalResultCount(Long totalResultCount) {
        this.totalResultCount = totalResultCount;
    }
}
