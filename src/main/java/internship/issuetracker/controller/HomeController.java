package internship.issuetracker.controller;

import internship.issuetracker.entity.Label;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.IssueService;
import internship.issuetracker.service.UserSettingsService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @Autowired
    private IssueService issueService;
    
    @Autowired
    private UserSettingsService userSettingsService;
    
    @RequestMapping(value = {"/", ""})
    public String home(HttpSession session) {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentThemePreference = userSettingsService.getCurrentThemePreference(currentUser.getUsername());
        
        session.setAttribute("theme", currentThemePreference);
        
        return "home";
    }

    @RequestMapping(value = "/not-found")
    public String notFound() {
        return "not-found";
    }
    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }
    
    @RequestMapping(value = "/manage-labels", method = RequestMethod.GET)
    public String manageLabels(Model model) {
        List<Label> labels = issueService.getAllLabels();
        model.addAttribute("labels", labels);
        return "manageLabels";
    }
}
