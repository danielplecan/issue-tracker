/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.controller;

import internship.issuetracker.entity.User;
import internship.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/profile/{username}")
    public String profile(@PathVariable("username") String username, Model model) {
        User target = userService.getUserByUsername(username);
        if (username != null){
            model.addAttribute("user", target);
            return "userProfile";
        }
        return "not-found";
    }
    @RequestMapping(value="/settings")
    public String settingsPageMapping() {
        return "settings";
    }
}
