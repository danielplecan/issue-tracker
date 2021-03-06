/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.controller;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.ActivationService;
import internship.issuetracker.service.MailService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.service.UserSettingsService;
import internship.issuetracker.util.SerializationUtil;
import internship.issuetracker.validator.UserValidator;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ActivationService activationService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserSettingsService userSettingsService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String registerUser(Model model) {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    @ResponseBody
    public Map<String, Object> createUser(@RequestBody @Valid UserDTO user, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        userValidator.validate(user, bindingResult);
        response.setStatus(HttpServletResponse.SC_OK);
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {
            result.put("success", true);
            userService.registerUser(user);
            String activationHash = activationService.createActivation(userService.getUserByUsername(user.getUsername()));
            Map< String, Object> map = new HashMap<>();
            map.put("link", "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/activation/" + activationHash);
            map.put("linkText", "Activate your acount");
            map.put("text", "");
            mailService.sendEmail(user.getEmail(), "Activation hash", map);
            userSettingsService.createSettingsforUser(user.getUsername());
        }

        return result;
    }

    @RequestMapping(value = "/registerMessage", method = RequestMethod.GET)
    public String verifieActivation() {
        return "registerMessage";
    }

    @RequestMapping(value = "/activation/{hash}", method = RequestMethod.GET)
    public String verifieActivation(@PathVariable("hash") String hash, Model model) {
        User user = activationService.getUserByActivationHash(hash);
        if (user != null) {
            userService.activateUserAccount(user);
            model.addAttribute("success", "true");
        } else {
            model.addAttribute("success", "false");
        }
        return "activation";
    }

    @RequestMapping(value = "/userExistance/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkUsernameExistence(@PathVariable("username") String username,
            HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();
        response.setStatus(HttpServletResponse.SC_OK);
        if (userService.usernameExists(username)) {
            responseMap.put("success", true);
        } else {
            responseMap.put("success", false);
        }
        return responseMap;
    }
}
