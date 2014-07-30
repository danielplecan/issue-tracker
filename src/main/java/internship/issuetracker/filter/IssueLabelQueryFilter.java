package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueLabels;
import internship.issuetracker.entity.IssueLabels_;
import internship.issuetracker.entity.Issue_;
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

    private Long labelId;

    public IssueLabelQueryFilter(String labelId) {
        if (labelId != null) {
            this.labelId = Long.parseLong(labelId);
        }
    }

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        Subquery<Issue> subquery = criteriaQuery.subquery(Issue.class);
        Root<IssueLabels> issueLabel = subquery.from(IssueLabels.class);
        
        Predicate labelPredicate = criteriaBuilder.equal(issueLabel.get(IssueLabels_.label), labelId);
        subquery.where(labelPredicate);
        subquery.select(issueLabel.get(IssueLabels_.issue));
        
        return root.in(subquery);
    }

}