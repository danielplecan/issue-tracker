package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue_;
import internship.issuetracker.entity.Issue;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dplecan
 */
public class IssueTitleQueryFilter implements QueryFilter<Issue>{
    private final String title;
    
    public IssueTitleQueryFilter(String title) {
        this.title = title;
    }
    
    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get(Issue_.title)), "%" + title.toLowerCase() + "%");
    }
    
}
