package internship.issuetracker.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dplecan
 */
public interface QueryFilter<T> {
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> root);
}
