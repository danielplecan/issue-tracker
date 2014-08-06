package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.order.OrderFactory;
import internship.issuetracker.order.QueryOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dplecan
 */
public class IssueSearchCriteria {

    private Map<String, Object> filters;

    private Map<String, Object> orders;

    private Integer pageNumber = 0;

    private Integer numberOfItemsPerPage = 10;

    public List<QueryFilter<Issue>> getQueryFilters() {
        List<QueryFilter<Issue>> queryFilters = new ArrayList<>();

        for (String filter : filters.keySet()) {
            Object filterValue = filters.get(filter);

            switch (filter.toLowerCase()) {
                case "title":
                    if (filterValue instanceof String) {
                        queryFilters.add(new IssueTitleQueryFilter((String) filterValue));
                    }
                    break;

                case "content":
                    if (filterValue instanceof String) {
                        queryFilters.add(new IssueContentQueryFilter((String) filterValue));
                    }
                    break;

                case "state":
                    if (filterValue instanceof String) {
                        IssueState state = IssueState.fromString((String) filterValue);
                        if (state != null) {
                            queryFilters.add(new IssueStateQueryFilter(state));
                        }
                    }
                    break;

                case "assignee":
                    if (filterValue instanceof String) {
                        queryFilters.add(new IssueAssigneeQueryFilter((String) filterValue));
                    }
                    break;

                case "owner":
                    if (filterValue instanceof String) {
                        queryFilters.add(new IssueOwnerQueryFilter((String) filterValue));
                    }
                    break;

                case "labels":
                    if (filterValue instanceof List) {
                        for (Object value : (List) filterValue) {
                            if (value instanceof String) {
                                queryFilters.add(new IssueLabelQueryFilter(Long.parseLong((String) value)));
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        
        return queryFilters;
    }

    public QueryOrder<Issue> getQueryOrder() {

        for (String key : getOrders().keySet()) {
            Object orderValue = getOrders().get(key);
            if (orderValue instanceof String) {
                QueryOrder<Issue> order = OrderFactory.<Issue>createFilter(key, (String) orderValue);
                if (order != null) {
                    return order;
                }
            }
        }
        return null;
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

    public Map<String, Object> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Object> orders) {
        this.orders = orders;
    }
}
