/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.filter;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.Issue_;
import internship.issuetracker.entity.User;
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
    
    
    private String assigneeUsername;
    
    public IssueAssigneeQueryFilter(String assigneeUsername) {
        this.assigneeUsername = assigneeUsername;
    }

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        return criteriaBuilder.like(root.get(Issue_.assignee).get(User_.username), "%" + assigneeUsername + "%");
    }

    public String getAssignee() {
        return assigneeUsername;
    }

    public void setAssignee(String assigneeUsername) {
        this.assigneeUsername = assigneeUsername;
    }
    
}
