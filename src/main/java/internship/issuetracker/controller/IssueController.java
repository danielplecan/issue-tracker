package internship.issuetracker.controller;

import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.util.SerializationUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

        List<Label> labels = issueService.getLabelsByIssueId(result);
        model.addAttribute("labels", labels);

        List<Comment> comments = issueService.getCommentsByIssueId(result);
        model.addAttribute("comments", comments);

        return "issue";
    }

    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String createIssue(Model model) {
        model.addAttribute("issue", new Issue());
        return "create-issue";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-issue")
    @ResponseBody
    public Map<String, Object> createAnIssue(@RequestBody @Valid Issue issue,
            BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();
        
        response.setStatus(HttpServletResponse.SC_OK);
        
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
            return responseMap;
        }

        User author = (User) request.getSession().getAttribute("user");
        issueService.createIssue(issue, author);

        responseMap.put("success", true);

        responseMap.put("url", request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/issue/" + issue.getId());

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/change-state/{action}")
    @ResponseBody
    public Map<String, Object> changeStateOfIssue(@PathVariable("id") Long issueId, @PathVariable("action") String stateAction, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", issueService.changeStateOfIssue(issueId, stateAction));

        response.setStatus(HttpServletResponse.SC_OK);
        return result;
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public String viewAllIssues(Model model) {

        List<Issue> issues = issueService.getIssuesOrderedByDate();
        model.addAttribute("issues", issues);

        return "issues";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/add-comment")
    @ResponseBody
    public Map<String, Object> addComment(@RequestBody @Valid Comment comment, BindingResult bindingResult, @PathVariable("id") Long issueId, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        Map<String, Object> response = new HashMap<>();
        comment = issueService.addComment(currentUser, issueId, comment);

        if (comment != null) {
            response.put("success", true);
            response.put("username",currentUser.getName());
            response.put("content", comment.getContent());
            response.put("date",comment.getDateFormat());
        } else {
            response.put("success", false);
        }
        return response;
    }
}
