package internship.issuetracker.controller;

import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.IssueService;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        issueService.createIssue(issue, currentUser);
        
        return "redirect:issue/" + issue.getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/open")
    @ResponseBody
    public Map<String, Object> openIssue(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        if (this.issueService.updateIssueState(id, IssueState.OPEN)) {
            response.put("status", "succes");
        } else {
            response.put("status", "fail");
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/close")
    @ResponseBody
    public Map<String, Object> closeIssue(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        if (this.issueService.updateIssueState(id, IssueState.CLOSED)) {
            response.put("status", "succes");
        } else {
            response.put("status", "fail");
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/reopen")
    @ResponseBody
    public Map<String, Object> reopenIssue(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        if (this.issueService.updateIssueState(id, IssueState.REOPENED)) {
            response.put("status", "succes");
        } else {
            response.put("status", "fail");
        }
        return response;
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public String viewAllIssues(Model model) {

        List<Issue> issues = issueService.getIssues();
        model.addAttribute("issues", issues);

        return "issues";
    }
}
