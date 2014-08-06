package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueLabel;
import internship.issuetracker.entity.IssueLabel_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author iapavaloaie
 */
public class IssueLabelQueryFilter implements QueryFilter<Issue> {

    private final Long labelId;

    public IssueLabelQueryFilter(Long labelId) {
        this.labelId = labelId;
    }

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        Subquery<Issue> subquery = criteriaQuery.subquery(Issue.class);
        Root<IssueLabel> issueLabel = subquery.from(IssueLabel.class);

        Predicate labelPredicate = criteriaBuilder.equal(issueLabel.get(IssueLabel_.label), labelId);
        subquery.where(labelPredicate);
        subquery.select(issueLabel.get(IssueLabel_.issue));

        return root.in(subquery);
    }
}
