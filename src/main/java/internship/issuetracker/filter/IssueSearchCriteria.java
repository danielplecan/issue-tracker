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

    private Map<String, Object> filters;

    private Integer pageNumber = 0;

    private Integer numberOfItemsPerPage = 10;

    public List<QueryFilter<Issue>> getQueryFilters() {
        List<QueryFilter<Issue>> queryFilters = new ArrayList<>();

        for (String key : filters.keySet()) {
            Object filterValue = filters.get(key);
            if (filterValue instanceof List) {
                for (Object value : (List) filterValue) {
                    if (value instanceof String) {
                        QueryFilter<Issue> filter = FilterFactory.<Issue>createFilter(key, (String) value);
                        if (filter != null) {
                            queryFilters.add(filter);
                        }
                    }
                }
            } else if (filterValue instanceof String) {
                QueryFilter<Issue> filter = FilterFactory.<Issue>createFilter(key, (String) filterValue);
                if (filter != null) {
                    queryFilters.add(filter);
                }
            }
        }
        return queryFilters;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
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
