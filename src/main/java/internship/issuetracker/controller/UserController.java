/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.controller;

import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.User;
import internship.issuetracker.service.UserService;
import internship.issuetracker.service.UserSettingsService;
import internship.issuetracker.util.SerializationUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String settingsPageMapping(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("initialNotificationCheckbox", userSettingsService.getCurrentNotificationStatus(user.getUsername()));
        model.addAttribute("initialTheme", userSettingsService.getCurrentThemePreference(user.getUsername()));

        return "settings";
    }

    @RequestMapping(value = "/settings/toggleNotifications", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> toggleNotifications() {

        Map<String, Object> responseMap = new HashMap<>();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        responseMap.put("success", true);
        responseMap.put("value", userSettingsService.toggleNotifications(currentUser.getUsername()));
        return responseMap;
    }

    @RequestMapping(value = "/settings/changeTheme/{theme}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeTheme(HttpSession session, @PathVariable Long theme) {

        Map<String, Object> responseMap = new HashMap<>();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        session.setAttribute("theme", theme);

        responseMap.put("success", userSettingsService.changeUserThemePreference(currentUser.getUsername(), theme));
        return responseMap;
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editProfile(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", user.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "editProfile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public Map<Stirng,Object> editProfile(@RequestBody @Valid UserDTO editedUser, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updateUser(user, editedUser);
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("errors", SerializationUtil.extractFieldErrors(bindingResult));   
        }
        else{
        }
        return result;
    }
