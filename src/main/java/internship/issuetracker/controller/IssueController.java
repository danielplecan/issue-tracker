package internship.issuetracker.controller;

import internship.issuetracker.dto.CommentDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.entity.Comment;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.UploadedFile;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.FilterResult;
import internship.issuetracker.filter.IssueSearchCriteria;
import internship.issuetracker.service.FileUploadService;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserService;
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

    private static final String LABELS = "labels";
    private static final String ISSUE = "issue";
    private static final String NOT_FOUND = "not-found";
    private static final String COMMENTS = "comments";
    private static final String RESULT = "result";
    private static final String ISSUES = "issues";
    private static final String HTTP = "http://";
    private static final String ERRORS = "errors";
    private static final String ATTACHMENTS = "attachments";
    private static final String SUCCESS = "success";
    private static final String LABEL = "label";

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Autowired
    private LabelValidator labelValidator;

    @Autowired
    private LabelForEditValidator labelForEditValidator;

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
    public String viewIssue(@PathVariable("id") Long id, Model model) {
        Issue result = issueService.getIssueById(id);
        if (result == null) {
            return NOT_FOUND;
        }

        model.addAttribute(ISSUE, result);

        List<Label> labels = issueService.getLabelsByIssueId(result);
        model.addAttribute(LABELS, labels);

        List<Comment> comments = issueService.getCommentsByIssueId(result);
        model.addAttribute(COMMENTS, comments);

        List<UploadedFile> attachments = issueService.getAttachmentsByIssueId(result);
        model.addAttribute(ATTACHMENTS, attachments);
        return ISSUE;
    }
    

    //crw: createIssue and createAnIssue methods might be puzzling, so you may consider renaming them
    //crw: one recommendation is to rename these methods with get<PageName> or setup<PageName>
    //crw: for example, the method bellow does not create an issue, instead it prepares the create-issue page
    @RequestMapping(value = {"/create-issue"}, method = RequestMethod.GET)
    public String getCreateIssuePage(Model model) {
        model.addAttribute(ISSUE, new Issue());
        model.addAttribute(LABELS, issueService.getAllLabels());
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
            responseMap.put(SUCCESS, false);
            responseMap.put(ERRORS, SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            User author = (User) request.getSession().getAttribute("user");
            long newResourceId = issueService.createIssueFromIssueDTO(issueDto, author);

            responseMap.put(SUCCESS, true);

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
    public Map<String, Object> closeIssue(@PathVariable("id") Long issueId, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        User currentUser = (User) request.getSession().getAttribute("user");
        result.put(SUCCESS, issueService.changeStateOfIssue(issueId, IssueState.CLOSED, currentUser));
        response.setStatus(HttpServletResponse.SC_OK);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/change-state/open")
    @ResponseBody
    public Map<String, Object> reopenIssue(@PathVariable("id") Long issueId, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        User currentUser = (User) request.getSession().getAttribute("user");
        result.put(SUCCESS, issueService.changeStateOfIssue(issueId, IssueState.OPEN, currentUser));
        response.setStatus(HttpServletResponse.SC_OK);

        return result;
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public String viewAllIssues(Model model) {
        Map<Long, List<Label>> labelsForIssue = new HashMap<>();
        model.addAttribute("allLabels", issueService.getAllLabels());
        return ISSUES;
    }
    

    @RequestMapping(method = RequestMethod.POST, value = "/issue/{id}/add-comment")
    @ResponseBody
    public Map<String, Object> addComment(@RequestBody @Valid CommentDTO commentDTO, BindingResult bindingResult, UriComponentsBuilder builder, @PathVariable("id") Long issueId, HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();

        CommentValidator validator = new CommentValidator();
        validator.validate(commentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            responseMap.put(SUCCESS, false);
            responseMap.put(ERRORS, SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            User currentUser = (User) request.getSession().getAttribute("user");
            long lastKnowCommentId = commentDTO.getId();

            Comment comment = commentDTO.getCommentFromDTO();
            comment.setAttachments(fileUploadService.getAttachmentsByIds(commentDTO.getAttachments()));
            issueService.addComment(currentUser, issueId, comment);
            Issue issue = issueService.getIssueById(issueId);

            List<User> targets = new ArrayList<>();
            targets.add(issue.getOwner());
            issueService.sendNotificationForComment(comment, targets.get(0), HTTP + request.getLocalAddr() + ":" + request.getLocalPort());
            if (issue.getAssignee() != null) {
                targets.add(issue.getAssignee());
                issueService.sendNotificationForComment(comment, targets.get(1), HTTP + request.getLocalAddr() + ":" + request.getLocalPort());
            }

            List<Comment> listComments = issueService.getMissedComments(issueId, lastKnowCommentId);
            responseMap.put(SUCCESS, true);
            responseMap.put(COMMENTS, listComments);
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
            responseMap.put(SUCCESS, false);
            responseMap.put(ERRORS, SerializationUtil.extractFieldErrors(bindingResult));
        } else {

            Label returnedLabel = issueService.createLabel(label);
            responseMap.put(SUCCESS, true);
            responseMap.put(LABEL, returnedLabel);
        }
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/label/{id}/remove")
    @ResponseBody
    public Map<String, Object> removeLabel(@PathVariable("id") Long labelId, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        response.setStatus(HttpServletResponse.SC_OK);

        if (issueService.removeLabel(labelId)) {
            responseMap.put(SUCCESS, true);
        } else {
            responseMap.put(SUCCESS, false);
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
            responseMap.put(SUCCESS, false);
            responseMap.put(ERRORS, SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            issueService.updateLabel(label);
            responseMap.put(SUCCESS, true);
            responseMap.put(LABEL, label);
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
            responseMap.put(SUCCESS, false);
        } else {
            User currentUser = (User) request.getSession().getAttribute("user");
            issueService.updateAssignee(issueId, assignedTo, currentUser);

            Issue targetIssue = issueService.getIssueById(issueId);
            issueService.sendNotificationForAssign(targetIssue, currentUser, HTTP + request.getLocalAddr() + ":" + request.getLocalPort());

            responseMap.put(SUCCESS, true);
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
        responseMap.put(SUCCESS, true);
        responseMap.put(RESULT, assignees);
        return responseMap;
    }
    

    @RequestMapping(value = {"/edit-issue/{id}"}, method = RequestMethod.GET)
    public String getEditIssuePage(@PathVariable("id") Long id, Model model) {
        Issue resultIssue = issueService.getIssueById(id);
        if (resultIssue == null) {
            return NOT_FOUND;
        }

        model.addAttribute(ISSUE, resultIssue);
        List<Label> labels = issueService.getLabelsByIssueId(resultIssue);
        model.addAttribute(LABELS, labels);

        return "edit-issue";
    }

    @RequestMapping(value = {"/getAllLabels"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEditIssuePage(Model model) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put(SUCCESS, true);
        responseMap.put(LABELS, issueService.getAllLabels());
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/issues/get-owners")
    @ResponseBody
    public Map<String, Object> getOwners(@RequestParam(value = "ownedBy") String ownedBy,
            HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();

        List<User> owners = issueService.findUsersIssuesOwnersByNamePrefix(ownedBy);
        responseMap.put(SUCCESS, true);
        responseMap.put(RESULT, owners);
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/issues/getFilterAssignees")
    @ResponseBody
    public Map<String, Object> getFilterAssignees(@RequestParam(value = "assignedTo") String assignedTo,
            HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        List<User> assignees = issueService.findUsersAssigneesByNamePrefix(assignedTo);
        responseMap.put(SUCCESS, true);
        responseMap.put(RESULT, assignees);
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit-issue")
    @ResponseBody
    public Map<String, Object> editIssue(@RequestBody @Valid NewIssueDTO issueDto, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        response.setStatus(HttpServletResponse.SC_OK);

        if (bindingResult.hasErrors()) {
            responseMap.put(SUCCESS, false);
            responseMap.put(ERRORS, SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            User currentUser = (User) request.getSession().getAttribute("user");
            Issue editedIssue = issueService.editIssueFromIssueDTO(issueDto, currentUser);

            responseMap.put(SUCCESS, true);
            responseMap.put("editedIssue", editedIssue);
            responseMap.put("editedLabels", issueService.getLabelsByIssueId(editedIssue));
            responseMap.put("editedAttachments", issueService.getAttachmentsByIssueId(editedIssue));

            issueService.sendNotificationForEdit(editedIssue, HTTP + request.getLocalAddr() + ":" + request.getLocalPort());
        }
        return responseMap;
    }

    @RequestMapping(value = "/issue/{id}/get-attachments", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAttachmentsForIssue(@PathVariable("id") Long issueId) {
        Map<String, Object> responseMap = new HashMap<>();
        Issue issue = issueService.getIssueById(issueId);

        if (issue == null) {
            responseMap.put(SUCCESS, false);
        } else {
            List<UploadedFile> attachments = issueService.getAttachmentsByIssueId(issue);
            if (attachments == null) {
                responseMap.put(SUCCESS, false);
            } else {
                responseMap.put(SUCCESS, true);
                responseMap.put(ATTACHMENTS, attachments);
            }
        }

        return responseMap;
    }

    @RequestMapping(value = "/issue/{id}/get-all-data", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDataForAnIssue(@PathVariable("id") Long issueId) {
        Map<String, Object> responseMap = new HashMap<>();

        Issue issue = issueService.getIssueById(issueId);

        if (issue == null) {
            responseMap.put(SUCCESS, false);
        } else {
            List<Comment> comments = issueService.getCommentsByIssueId(issue);
            if (comments.isEmpty()) {
                responseMap.put(SUCCESS, false);
            } else {
                responseMap.put(SUCCESS, true);
                responseMap.put(COMMENTS, comments);
            }

            responseMap.put(ISSUE, issue);
        }

        return responseMap;
    }

}
