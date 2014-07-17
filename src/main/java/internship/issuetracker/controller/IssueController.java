package internship.issuetracker.controller;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.IssueService;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

/**
 *
 * @author scalin
 */
//@Service
@Controller
public class IssueController {

    @Autowired
    IssueService issueService;

    @PersistenceContext
    EntityManager em;

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
    public String viewIssue(@PathVariable String id, Model model) {

        Long issueId = Long.parseLong(id);
        Issue result = issueService.getIssueById(issueId);
        model.addAttribute("issue", result);

        return "issue";
    }

    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String createIssue(Model model) {
        model.addAttribute("issue", new Issue());
        return "create-issue";
    }

    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.POST)
    public String createIssue(@ModelAttribute("issue") Issue issue, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-issue";
        }
        String a = issue.getTitle();
        System.out.println(issue.getTitle());

        //until we can actually have a current user
        User u = em.find(User.class, (long) 1);
        issue.setOwner(u);
        Date data = new Date();
        issue.setDate(data);
        //set state open, by default
        issue.setState(IssueState.OPEN);
        issueService.createIssue(issue);
        return "home";
    }

    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    public IssueService getIssueService() {
        return issueService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/open")
    public ResponseEntity<Issue> openIssue(@PathVariable("id") int id) {
        System.out.println(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/close")
    public ResponseEntity<Issue> closeIssue(@PathVariable("id") int id) {
        System.out.println(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/reopen")
    public ResponseEntity<Issue> reopenIssue(@PathVariable("id") int id) {
        System.out.println(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public String viewAllIssues(Model model) {

        List<Issue> issues = issueService.getIssues();
        model.addAttribute("issues", issues);

        return "issues";
    }
}
