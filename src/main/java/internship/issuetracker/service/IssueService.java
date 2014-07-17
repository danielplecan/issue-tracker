/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import internship.issuetracker.entity.User;
import internship.issuetracker.entity.Issue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author atataru
 */

@Service
public class IssueService {
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Create a new issue and persist it in the database;
     * @param title - the title of post
     * @param content - the content of post(can be null)
     * @param user  - the user who created the post
     */
    public Issue createIssue(String title, String content, User user){
        Issue issue = new Issue();
        issue.setTitle(title);
        issue.setContent(content);
        issue.setOwner(user);
        
        //save issue in database
        em.persist(issue);
        return issue;
    }
    
    public Issue getIssueById(Long id){
        Issue result = em.find(Issue.class, id);
        return result;
    }
     
    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/open")
    public ResponseEntity<Issue> openIssue(@PathVariable("id") int id) {
           System.out.println(id);
           return new ResponseEntity<Issue>(HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/close")
    public ResponseEntity<Issue> closeIssue(@PathVariable("id") int id) {
           System.out.println(id);
           return new ResponseEntity<Issue>(HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/reopen")
    public ResponseEntity<Issue> reopenIssue(@PathVariable("id") int id) {
           System.out.println(id);
           return new ResponseEntity<Issue>(HttpStatus.OK);
    }
}
