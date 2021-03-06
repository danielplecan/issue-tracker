package internship.issuetracker.order;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.Issue_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class IssueUpdateDateQueryOrder implements QueryOrder<Issue>{
    private final OrderType orderType;
    
    public IssueUpdateDateQueryOrder(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public Order buildOrder(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        if(orderType == null) {
            return null;
        }
        
        switch(orderType) {
            case ASCENDENT:
                return criteriaBuilder.asc(root.get(Issue_.updateDate));
            case DESCENDENT:
                return criteriaBuilder.desc(root.get(Issue_.updateDate));
            default:
                return null;
        }
    }
}
