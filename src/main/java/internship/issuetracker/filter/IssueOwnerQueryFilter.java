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


public class IssueOwnerQueryFilter implements QueryFilter<Issue> {
    
    private String ownerUsername;

    public IssueOwnerQueryFilter(String ownerUsername) {
         this.ownerUsername = ownerUsername;
    }
    

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        return criteriaBuilder.like(root.get(Issue_.owner).get(User_.username), "%" + ownerUsername + "%");
    }
    
    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String creatorUsername) {
        this.ownerUsername = creatorUsername;
    }
}
