package internship.issuetracker.order;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.Issue_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class IssueTitleQueryOrder implements QueryOrder<Issue>{
    private final String orderType;
    
    public IssueTitleQueryOrder(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public Order buildOrder(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        if(orderType == null) {
            return null;
        }
        switch(orderType.toUpperCase()) {
            case "ASC" :
                return criteriaBuilder.asc(root.get(Issue_.title));
            case "DESC":
                return criteriaBuilder.desc(root.get(Issue_.title));
            default:
                return null;
        }
    }
}
