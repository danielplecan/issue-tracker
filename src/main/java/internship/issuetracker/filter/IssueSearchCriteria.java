package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dplecan
 */
public class IssueSearchCriteria {
    private Map<String, List<String>> filters;
    
    private Integer pageNumber = 0;
    
    private Integer numberOfItemsPerPage = 10;
    
    public List<QueryFilter<Issue>> getQueryFilters() {
        List<QueryFilter<Issue>> queryFilters = new ArrayList<>();
        
        for(String key : filters.keySet()) {
            List<String> values = filters.get(key);
            for(String value : values) {
                QueryFilter<Issue> filter = FilterFactory.<Issue>createFilter(key, value);
                if(filter != null) {
                   queryFilters.add(filter); 
                }
            }
        }
        return queryFilters;
    }

    public Map<String, List<String>> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, List<String>> filters) {
        this.filters = filters;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getNumberOfItemsPerPage() {
        return numberOfItemsPerPage;
    }

    public void setNumberOfItemsPerPage(Integer numberOfItemsPerPage) {
        this.numberOfItemsPerPage = numberOfItemsPerPage;
    }
}
