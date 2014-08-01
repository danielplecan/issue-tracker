package internship.issuetracker.controller;

import internship.issuetracker.dto.CommentDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.FilterResult;
import internship.issuetracker.filter.IssueSearchCriteria;
import internship.issuetracker.service.FileUploadService;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.MailService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.service.UserSettingsService;
import internship.issuetracker.util.SerializationUtil;
import internship.issuetracker.validator.CommentValidator;
import internship.issuetracker.validator.LabelForEditValidator;
import internship.issuetracker.validator.LabelValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author scalin
 */
@Controller
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Autowired
    private LabelValidator labelValidator;

    @Autowired
    private LabelForEditValidator labelForEditValidator;

    @Autowired
    private UserSettingsService userSettingsService;

    @Autowired
    private MailService mailService;
    
    @Autowired
    private FileUploadService fileUploadService;

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

    //crw: createIssue and createAnIssue methods might be puzzling, so you may consider renaming them
    //crw: one recommendation is to rename these methods with get<PageName> or setup<PageName>
    //crw: for example, the method bellow does not create an issue, instead it prepares the create-issue page
    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String getCreateIssuePage(Model model) {
        model.addAttribute("issue", new Issue());
        model.addAttribute("labels", issueService.getAllLabels());
        return "create-issue";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-issue")
    @ResponseBody
    public Map<String, Object> setupCreateIssuePage(@RequestBody @Valid NewIssueDTO issueDto,
            BindingResult bindingResult, UriComponentsBuilder builder, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        response.setStatus(HttpServletResponse.SC_OK);

        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            User author = (User) request.getSession().getAttribute("user");
            long newResourceId = issueService.createIssueFromIssueDTO(issueDto, author);

            responseMap.put("success", true);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(
                    builder.path("/issue/{id}")
                    .buildAndExpand(newResourceId).toUri());
            responseMap.put("url", headers.getLocation());
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

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/change-state/open")
    @ResponseBody
    public Map<String, Object> reopenIssue(@PathVariable("id") Long issueId, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();

        result.put("success", issueService.changeStateOfIssue(issueId, IssueState.OPEN));
        response.setStatus(HttpServletResponse.SC_OK);

        return result;
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public String viewAllIssues(Model model) {

        List<Issue> issues = issueService.getIssuesOrderedByDate();
        Map<Long, List<Label>> labelsForIssue = new HashMap<>();
        for (Issue is : issues) {
            List<Label> labels = issueService.getLabelsByIssueId(is);
            labelsForIssue.put(is.getId(), labels);
        }
        model.addAttribute("issues", issues);
        model.addAttribute("labels", labelsForIssue);
        model.addAttribute("allLabels", issueService.getAllLabels());
        return "issues";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/add-comment")
    @ResponseBody
    public Map<String, Object> addComment(@RequestBody @Valid CommentDTO commentDTO, BindingResult bindingResult, UriComponentsBuilder builder, @PathVariable("id") Long issueId, HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();

        CommentValidator validator = new CommentValidator();
        validator.validate(commentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            User currentUser = (User) request.getSession().getAttribute("user");
            long lastKnowCommentId = commentDTO.getId();
            
            Comment comment = commentDTO.getCommentFromDTO();
            comment.setAttachments(fileUploadService.getAttachmentsByIds(commentDTO.getAttachments()));
            issueService.addComment(currentUser, issueId, comment);

            Issue issue = issueService.getIssueById(issueId);

            
            List<User> targets = new ArrayList<>();
            targets.add(issue.getOwner());
            if (issue.getAssignee() != null) {
                targets.add(issue.getAssignee());
            }
            for (User user: targets) {
                if(userSettingsService.getCurrentNotificationStatus(user.getUsername())){
                    issueService.sendNotification(comment, user);
                }
            }

            List<Comment> listComments = issueService.getMissedComments(issueId, lastKnowCommentId);
            responseMap.put("success", true);
            responseMap.put("comments", listComments);
        }

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issues/filter")
    @ResponseBody
    public FilterResult filterIssues(@RequestBody @Valid IssueSearchCriteria searchCriteria, BindingResult bindingResult) {
        return issueService.filterIssues(searchCriteria.getQueryFilters(), searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-label")
    @ResponseBody
    public Map<String, Object> createLabel(@RequestBody @Valid Label label,
            BindingResult bindingResult, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        response.setStatus(HttpServletResponse.SC_OK);

        //crw: there is a subtle issue with this validator, because the check for label existence and its creation are
        //crw: not run in the same transaction. therefore, you might end up with 2 entries with the same name
        //
        //crw: to fix this, you would have to put the validation and label creation into the same transaction
        //crw: that is, call the validator inside issueService.createLabel method
        labelValidator.validate(label, bindingResult);
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {

            Label returnedLabel = issueService.createLabel(label);
            responseMap.put("success", true);
            responseMap.put("label", returnedLabel);
        }
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/label/{id}/remove")
    @ResponseBody
    public Map<String, Object> removeLabel(@PathVariable("id") Long labelId, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        response.setStatus(HttpServletResponse.SC_OK);

        if (issueService.removeLabel(labelId)) {
            responseMap.put("success", true);
        } else {
            responseMap.put("success", false);
        }
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/label/{id}/edit")
    @ResponseBody
    public Map<String, Object> editLabel(@RequestBody @Valid Label label,
            BindingResult bindingResult, @PathVariable("id") Long labelId,
            HttpServletResponse response) {

        Map<String, Object> responseMap = new HashMap<>();
        response.setStatus(HttpServletResponse.SC_OK);

        label.setId(labelId);
        labelForEditValidator.validate(label, bindingResult);
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            issueService.updateLabel(label);
            responseMap.put("success", true);
            responseMap.put("label", label);
        }
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/add-assignee")
    @ResponseBody
    public Map<String, Object> addAssignee(@RequestBody User assignedTo,
            BindingResult bindingResult, @PathVariable("id") Long issueId,
            HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
//            responseMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
//            User assignee = userService.getUserById(assignedTo.getId());
            issueService.updateAssignee(issueId, assignedTo);
            responseMap.put("success", true);
            responseMap.put("assignedTo", assignedTo);
        }
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/issue/{id}/getUsers-assignee")
    @ResponseBody
    public Map<String, Object> getUsersAssignee(@RequestParam(value = "assignedTo") String assignedTo,
            @PathVariable("id") Long issueId, HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();

        List<User> assignees = userService.findUsersWithUsernameStartingWith(assignedTo);
        responseMap.put("success", true);
        responseMap.put("assignees", assignees);
        return responseMap;
    }
    @RequestMapping(value = {"/edit-issue/{id}"}, method = RequestMethod.GET)
    public String getEditIssuePage(@PathVariable("id") Long id, Model model) {
        Issue resultIssue = issueService.getIssueById(id);
        if (resultIssue == null) {
            return "not-found";
        }

        model.addAttribute("issue", resultIssue);
        List<Label> labels = issueService.getLabelsByIssueId(resultIssue);
        model.addAttribute("labels", labels);

        return "edit-issue";
    }

    @RequestMapping(value = {"/getAllLabels"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEditIssuePage(Model model) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("labels", issueService.getAllLabels());
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/issues/get-owners")
    @ResponseBody
    public Map<String, Object> getOwners(@RequestParam(value = "ownedBy") String ownedBy,
            HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();

        List<User> owners = issueService.findUsersIssuesOwnersByNamePrefix(ownedBy);
        responseMap.put("success", true);
        responseMap.put("owners", owners);
        return responseMap;
    }
}
