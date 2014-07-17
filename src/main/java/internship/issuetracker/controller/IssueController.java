/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package internship.issuetracker.controller;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author scalin
 */

//@Service
@Controller
public class IssueController {
    
    @Autowired
    IssueService issueService;
    
    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
    public String viewIssue(@PathVariable String id, Model model){
        
        Long issueId = Long.parseLong(id);
        Issue result = issueService.getIssueById(issueId);
        model.addAttribute("issue", result);
        
        return "issue";
    }
    
    @RequestMapping(value = {"/create-issue"},method = RequestMethod.GET)
    public String createIssue() {
        return "create-issue";
    }
    
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    public IssueService getIssueService() {
        return issueService;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/open")
    public ResponseEntity<Issue> openIssue(@PathVariable("id") int id) {
           System.out.println(id);
           return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/close")
    public ResponseEntity<Issue> closeIssue(@PathVariable("id") int id) {
           System.out.println(id);
           return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/reopen")
    public ResponseEntity<Issue> reopenIssue(@PathVariable("id") int id) {
           System.out.println(id);
           return new ResponseEntity<>(HttpStatus.OK);
    }
}
