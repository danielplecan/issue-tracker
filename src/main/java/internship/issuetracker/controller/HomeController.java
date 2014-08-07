package internship.issuetracker.controller;

import internship.issuetracker.entity.Label;
import internship.issuetracker.service.IssueService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @Autowired
    private IssueService issueService;
    
    @RequestMapping(value = {"/", ""})
    public String home(HttpSession session) {
        return "redirect:/issues";
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
