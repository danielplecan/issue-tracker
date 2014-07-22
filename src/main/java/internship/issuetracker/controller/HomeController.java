package internship.issuetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", ""})
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/not-found")
    public String notFound() {
        return "not-found";
    }
}
