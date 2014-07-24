package internship.issuetracker.filter;

import internship.issuetracker.dto.IssueDTO;
import java.util.List;

/**
 *
 * @author dplecan
 */
public class FilterResult {
    private List<IssueDTO> issues;
    private Long numberOfPages;
    private Long totalResultCount;
    private Long currentPage;
    private Long numberOfItemsPerPage;

    public List<IssueDTO> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueDTO> issues) {
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

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getNumberOfItemsPerPage() {
        return numberOfItemsPerPage;
    }

    public void setNumberOfItemsPerPage(Long numberOfItemsPerPage) {
        this.numberOfItemsPerPage = numberOfItemsPerPage;
    }
}
