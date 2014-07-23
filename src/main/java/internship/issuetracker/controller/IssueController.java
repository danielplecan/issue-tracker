package internship.issuetracker.controller;

import internship.issuetracker.dto.IssueDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.IssueSearchCriteria;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.util.SerializationUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (result == null) {
            return "not-found";
        }

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
        model.addAttribute("labels", issueService.getAllLabels());
        return "create-issue";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-issue")
    @ResponseBody
    public Map<String, Object> createAnIssue(@RequestBody @Valid IssueDTO issueDto,
            BindingResult bindingResult, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        response.setStatus(HttpServletResponse.SC_OK);

        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            User author = (User) request.getSession().getAttribute("user");
            issueService.createIssueFromIssueDTO(issueDto, author);

            responseMap.put("success", true);

            responseMap.put("url", request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/issue/" + issueDto.getIssue().getId());
        }

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/change-state/close")
    @ResponseBody
    public Map<String, Object> closeIssue(@PathVariable("id") Long issueId, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();

        result.put("success", issueService.changeStateOfIssue(issueId, IssueState.CLOSED));
        response.setStatus(HttpServletResponse.SC_OK);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/change-state/reopen")
    @ResponseBody
    public Map<String, Object> reopenIssue(@PathVariable("id") Long issueId, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();

        result.put("success", issueService.changeStateOfIssue(issueId, IssueState.REOPENED));
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

        Map<String, Object> responseMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            User currentUser = (User) request.getSession().getAttribute("user");

            issueService.addComment(currentUser, issueId, comment);

            responseMap.put("success", true);
            responseMap.put("username", currentUser.getName());
            responseMap.put("content", comment.getContent());
            responseMap.put("date", comment.getDateFormat());
        }

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issues/filter")
    @ResponseBody
    public List<Issue> filterIssues(@RequestBody @Valid IssueSearchCriteria searchCriteria, BindingResult bindingResult) {
        return issueService.filterIssues(searchCriteria);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/create-label")
    @ResponseBody
    public Map<String, Object> createLabel(@RequestBody @Valid Label label,
            BindingResult bindingResult, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        response.setStatus(HttpServletResponse.SC_OK);

        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            
            Label returnedLabel  = issueService.createLabel(label);
            if(returnedLabel != null) {
                responseMap.put("success", true);
                responseMap.put("label", label);
            } else {
                responseMap.put("success", false);
                bindingResult.rejectValue("name", "labelNameExist", "A label with this name already exists.");
                responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
            }
        }
        return responseMap;
    }
}
