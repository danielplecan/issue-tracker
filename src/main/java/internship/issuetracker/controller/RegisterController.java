/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.controller;

import internship.issuetracker.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @RequestMapping(value = {"/register"},method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("user",new User());
        return "register";
    }
    	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String logIn(@Validated @ModelAttribute("user") User user,BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			return "register";
//		}
                System.out.println(user.getName());
		return "home";
	}
}
