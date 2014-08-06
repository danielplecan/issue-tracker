/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.Issue_;
import internship.issuetracker.entity.User_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author scalin
 */
public class IssueAssigneeQueryFilter implements QueryFilter<Issue> {
    
    
    private Long assigneeId;
    
    public IssueAssigneeQueryFilter(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        if(assigneeId == null) {
            return criteriaBuilder.and();
        }
        return criteriaBuilder.equal(root.get(Issue_.assignee).get(User_.id), assigneeId);
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
    
}
