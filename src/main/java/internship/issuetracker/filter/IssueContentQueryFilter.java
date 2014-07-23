package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.Issue_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dplecan
 */
public class IssueContentQueryFilter implements QueryFilter<Issue>{
    private final String content;
    
    public IssueContentQueryFilter(String content) {
        this.content = content;
    }
    
    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get(Issue_.content)), "%" + content.toLowerCase() + "%");
    }
}