/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.controller;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.service.UserService;
import internship.issuetracker.util.SerializationUtil;
import internship.issuetracker.validator.UserValidator;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    @ResponseBody
    public Map<String, Object> createUser(@RequestBody @Valid UserDTO user, BindingResult bindingResult, HttpServletResponse response) {

        Map<String, Object> returnMap = new HashMap<>();

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            returnMap.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
            return returnMap;
        }

        userService.registerUser(user);
        
        response.setStatus(HttpServletResponse.SC_CREATED);

        return returnMap;
    }
}
