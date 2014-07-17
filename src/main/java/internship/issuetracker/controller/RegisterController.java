/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.controller;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.UserService;
import internship.issuetracker.validator.UserValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserValidator userValidator;
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String logIn(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        userService.registerUser(user);
        return "home";
    }
}
