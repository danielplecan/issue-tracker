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
import org.apache.log4j.Logger;

/**
 *
 * @author dplecan
 */
public class IssueSearchCriteria {
    private static final Logger LOGGER = Logger.getLogger(IssueSearchCriteria.class);

    private Map<String, Object> filters;

    private Map<String, Object> orders;

    private Integer pageNumber = 0;

    private Integer numberOfItemsPerPage = 10;

    public List<QueryFilter<Issue>> getQueryFilters() {
        List<QueryFilter<Issue>> queryFilters = new ArrayList<>();

        for (String filter : filters.keySet()) {
            Object filterValue = filters.get(filter);
            try {
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
                    case "labels":
                        for (Object value : (List) filterValue) {
                            queryFilters.add(new IssueLabelQueryFilter(((Integer) value).longValue()));
                        }
                        break;
                    case "assignee":
                        queryFilters.add(new IssueAssigneeQueryFilter(((Integer) filterValue).longValue()));
                        break;
                    case "owner":
                        queryFilters.add(new IssueOwnerQueryFilter(((Integer) filterValue).longValue()));
                        break;
                    default:
                        break;
                }
            } catch (ClassCastException exception) {
                LOGGER.log(org.apache.log4j.Level.ERROR, "Invalid value provided to filter API", exception);
            }
        }
        return queryFilters;
    }

    public QueryOrder<Issue> getQueryOrder() {
        for (String key : getOrders().keySet()) {
            Object orderValue = getOrders().get(key);
            try {
                switch (key) {
                    case "title":
                        return new IssueTitleQueryOrder(OrderType.fromString((String) orderValue));
                    case "updateDate":
                        return new IssueUpdateDateQueryOrder(OrderType.fromString((String) orderValue));
                    default:
                        return null;
                }
            } catch (ClassCastException exception) {
                LOGGER.log(org.apache.log4j.Level.ERROR, "Invalid value provided to filter API", exception);
                return null;
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
