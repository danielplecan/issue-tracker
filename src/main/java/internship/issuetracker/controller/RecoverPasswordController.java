/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship.issuetracker.controller;

import internship.issuetracker.entity.User;
import internship.issuetracker.service.MailService;
import internship.issuetracker.service.RecoverPasswordService;
import internship.issuetracker.service.UserService;
import internship.issuetracker.util.SecurityUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecoverPasswordController {
    private static final String USER_TO_CHANGE_PASSWORD = "userToChangePassword";
    private static final String RECOVERY_MESSAGE_PAGE = "recovery-message";
    private static final String SUCCESS = "success";

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    RecoverPasswordService recoverPasswordService;

    @RequestMapping(value = {"/recover-password"}, method = RequestMethod.GET)
    public String recoverPassword(HttpServletRequest request, Model map) {
        return "recover-password";
    }

    @RequestMapping(value = {"/recovery-message"}, method = RequestMethod.GET)
    public String recoveryMsssage(Model model) {
        model.addAttribute("text", "Check your email in order to get the recovery hash");
        return RECOVERY_MESSAGE_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recover-password")
    @ResponseBody
    public Map<String, Object> recoverPassword(@RequestParam(value = "information") String information, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        User user = userService.getUserByUsername(information);
        if (user == null) {
            user = userService.getUserByEmail(information);
        }

        if (user == null) {
            result.put(SUCCESS, false);
            return result;
        }
        recoverPasswordService.deleteRecoveryByUser(user);
        String recoveryHash = recoverPasswordService.createRecovery(userService.getUserByUsername(user.getUsername()));
        map.put("link", "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/recover-password/" + recoveryHash);
        map.put("linkText", "Password recoverry hash ");
        map.put("text", "");
        mailService.sendEmail(user.getEmail(), "Password recovery", map);
        result.put(SUCCESS, true);
        return result;
    }
    

    @RequestMapping(value = "/recover-password/{hash}", method = RequestMethod.GET)
    public String verifieRecoveryPassword(@PathVariable("hash") String hash, Model model, HttpServletRequest request) {
        User user = recoverPasswordService.getUserByRecoveryHash(hash);
        if (user != null) {
            request.getSession().setAttribute(USER_TO_CHANGE_PASSWORD, user);
        } else {
            model.addAttribute("text", "Recovery hash is not valid");
            return RECOVERY_MESSAGE_PAGE;
        }
        model.addAttribute("user", user.getUsername());
        return "change-password";
    }
    

    @RequestMapping(method = RequestMethod.POST, value = "/change-password")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam(value = "password") String password, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute(USER_TO_CHANGE_PASSWORD);
        user.setPasswordHash(SecurityUtil.encryptPassword(password));
        recoverPasswordService.deleteRecoveryByUser(user);
        userService.updateUser(user);
        return result;
    }
}
