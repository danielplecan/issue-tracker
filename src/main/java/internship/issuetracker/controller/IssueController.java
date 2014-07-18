package internship.issuetracker.controller;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.IssueService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author scalin
 */
@Controller
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
    public String viewIssue(@PathVariable("id") Long id, Model model) {
        Issue result = issueService.getIssueById(id);
        model.addAttribute("issue", result);

        return "issue";
    }

    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String createIssue(Model model) {
        model.addAttribute("issue", new Issue());
        return "create-issue";
    }

    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.POST)
    public String createIssue(@Valid @ModelAttribute("issue") Issue issue, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-issue";
        }
        String a = issue.getTitle();
        System.out.println(issue.getTitle());

        //until we can actually have a current user
        User u = em.find(User.class, (long) 1);
        issue.setOwner(u);
        
        //set state open, by default
        
        issueService.createIssue(issue);
        
        return "redirect:issue/" + issue.getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/open")
    @ResponseBody
    public ResponseEntity<Issue> openIssue(@PathVariable("id") int id) {
        ResponseEntity<Issue> response;
        if (this.issueService.updateIssueState(id, IssueState.OPEN)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/close")
    @ResponseBody
    public ResponseEntity<Issue> closeIssue(@PathVariable("id") int id) {
        ResponseEntity<Issue> response;
        if (this.issueService.updateIssueState(id, IssueState.CLOSED)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/reopen")
    @ResponseBody
    public ResponseEntity<Issue> reopenIssue(@PathVariable("id") int id) {
        ResponseEntity<Issue> response;
        if (this.issueService.updateIssueState(id, IssueState.REOPENED)) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public String viewAllIssues(Model model) {

        List<Issue> issues = issueService.getIssuesOrderedByDate();
        model.addAttribute("issues", issues);

        return "issues";
    }
}
