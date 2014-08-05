/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.controller;

import internship.issuetracker.dto.EditUserDto;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.UserService;
import internship.issuetracker.service.UserSettingsService;
import internship.issuetracker.util.SerializationUtil;
import internship.issuetracker.validator.EditUserValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private EditUserValidator editUserValidator;
    @Autowired
    UserService userService;
    @Autowired
    UserSettingsService userSettingsService;

    @RequestMapping(value = "/profile/{username}")
    public String profile(@PathVariable("username") String username, Model model) {
        User target = userService.getUserByUsername(username);
        if (target != null) {
            model.addAttribute("user", target);
            return "userProfile";
        }
        return "not-found";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsPageMapping(Model model,HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("initialNotificationCheckbox", userSettingsService.getCurrentNotificationStatus(user.getUsername()));
        model.addAttribute("initialTheme", userSettingsService.getCurrentThemePreference(user.getUsername()));

        return "settings";
    }

    @RequestMapping(value = "/settings/toggleNotifications", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> toggleNotifications(HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();
        User currentUser = (User) request.getSession().getAttribute("user");

        responseMap.put("success", true);
        responseMap.put("value", userSettingsService.toggleNotifications(currentUser.getUsername()));
        return responseMap;
    }

    @RequestMapping(value = "/settings/changeTheme/{theme}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeTheme(HttpSession session, @PathVariable Long theme,HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<>();
        User currentUser = (User) request.getSession().getAttribute("user");

        session.setAttribute("theme", theme);

        responseMap.put("success", userSettingsService.changeUserThemePreference(currentUser.getUsername(), theme));
        return responseMap;
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editProfile(Model model,HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("name", user.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "edit-profile";
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editProfile(@RequestBody @Valid EditUserDto editedUser, BindingResult bindingResult,HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user =  (User) request.getSession().getAttribute("user");
        if (editedUser.getPassword().isEmpty()) {
            editedUser.setPassword(editedUser.getOldPassword());
        }

        editUserValidator.setOldUser(user);
        editUserValidator.validate(editedUser, bindingResult);
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("errors", SerializationUtil.extractFieldErrors(bindingResult));
        } else {

            user = userService.loginUser(user.getUsername(), editedUser.getOldPassword());

            if (user == null) {
                result.put("success", false);
                Map<String, Object> passwordError = new HashMap<>();
                passwordError.put("oldPassword", "wrong password");
                result.put("errors", passwordError);
                return result;
            }

            user = userService.updateUser(user, editedUser.getUserFromDTO());
            request.getSession().setAttribute("user", user);
            result.put("success", true);
            result.put("username",user.getUsername());
        }
        return result;
    }
}
