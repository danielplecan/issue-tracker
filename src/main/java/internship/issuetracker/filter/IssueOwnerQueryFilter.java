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
    
    private Long ownerId;

    public IssueOwnerQueryFilter(Long ownerId) {
         this.ownerId = ownerId;
    }
    

    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, CriteriaQuery<Issue> criteriaQuery, Root<Issue> root) {
        return criteriaBuilder.equal(root.get(Issue_.owner).get(User_.id), ownerId);
    }
    
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
