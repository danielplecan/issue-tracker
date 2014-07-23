package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Issue_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dplecan
 */
public class IssueStateQueryFilter implements QueryFilter<Issue>{
    private final IssueState state;
    
    public IssueStateQueryFilter(String state) {
        switch(state) {
            case "open":
                this.state = IssueState.OPEN;
                break;
            case "closed":
                this.state = IssueState.CLOSED;
                break;
            case "reopened":
                this.state = IssueState.REOPENED;
                break;
            default:
                this.state = null;
        }
    }
    
    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        if(this.state != null) {
            return criteriaBuilder.equal(root.get(Issue_.state), state);
        } else {
            return null;
        }
    }
}
