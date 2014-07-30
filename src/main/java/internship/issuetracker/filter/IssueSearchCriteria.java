package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
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

        for (String key : filters.keySet()) {
            Object filterValue = filters.get(key);
            if (filterValue instanceof List) {
                for (Object value : (List) filterValue) {
                    if (value instanceof String) {
                        QueryFilter<Issue> filter = FilterFactory.<Issue>createFilter(key, (String) value);
                        if (filter != null) {
                            queryFilters.add(filter);
                        }
                    } else if(value instanceof Integer) {
                        QueryFilter<Issue> filter = FilterFactory.<Issue>createFilter(key, ((Integer) value).toString());
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

    /**
     * @return the orders
     */
    public Map<String, Object> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Map<String, Object> orders) {
        this.orders = orders;
    }
}
