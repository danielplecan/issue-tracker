/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.controller;

import internship.issuetracker.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String loginPost(Model model) {
        model.addAttribute("user", new UserDTO());
        return "home";
    }

}
