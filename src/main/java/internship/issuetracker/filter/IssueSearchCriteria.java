package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.order.IssueTitleQueryOrder;
import internship.issuetracker.order.IssueUpdateDateQueryOrder;
import internship.issuetracker.order.OrderType;
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

            if (filterValue instanceof String) {
                switch (filter) {
                    case "title":
                        queryFilters.add(new IssueTitleQueryFilter((String) filterValue));
                        break;
                    case "content":
                        queryFilters.add(new IssueContentQueryFilter((String) filterValue));
                        break;
                    case "state":
                        IssueState state = IssueState.fromString((String) filterValue);
                        if (state != null) {
                            queryFilters.add(new IssueStateQueryFilter(state));
                        }
                        break;
                    case "assignee":
                        queryFilters.add(new IssueAssigneeQueryFilter((String) filterValue));
                        break;
                    case "owner":
                        queryFilters.add(new IssueOwnerQueryFilter((String) filterValue));
                        break;
                    default:
                        break;
                }
            } else if (filterValue instanceof List) {
                switch (filter) {
                    case "labels":
                        for (Object value : (List) filterValue) {
                            if (value instanceof String) {
                                queryFilters.add(new IssueLabelQueryFilter(Long.parseLong((String) value)));
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        return queryFilters;
    }

    public QueryOrder<Issue> getQueryOrder() {
        for (String key : getOrders().keySet()) {
            Object orderValue = getOrders().get(key);

            if (orderValue instanceof String) {
                switch (key) {
                    case "title":
                        return new IssueTitleQueryOrder(OrderType.fromString((String) orderValue));
                    case "updateDate":
                        return new IssueUpdateDateQueryOrder(OrderType.fromString((String) orderValue));
                    default:
                        return null;
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
