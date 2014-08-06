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
    public String recoveryMsssage() {
        return "recovery-message";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/recover-password")
    @ResponseBody
    public Map<String, Object> recoverPassword(@RequestParam(value = "username") String username, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        User user = userService.getUserByUsername(username);
        
        if(user==null){
            result.put("success",false);
            return result;
        }
        String recoveryHash = recoverPasswordService.createRecovery(userService.getUserByUsername(user.getUsername()));
        map.put("link", "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/recover-password/" + recoveryHash);
        map.put("linkText", "Password recoverry hash ");
        map.put("text", "");
        mailService.sendEmail(user.getEmail(), "Password recovery", map);
        result.put("success",true);
        return result;
    }

    @RequestMapping(value = "/recover-password/{hash}", method = RequestMethod.GET)
    public String verifieRecoveryPassword(@PathVariable("hash") String hash, Model model,HttpServletRequest request) {
        User user = recoverPasswordService.getUserByRecoveryHash(hash);
        if (user != null) {
            model.addAttribute("success", "true");
            request.getSession().setAttribute("userToChangePassword", user);
        } else {
            model.addAttribute("success", "false");
        }
        return "change-password";
    }
    @RequestMapping(method = RequestMethod.POST,value = "/change-password")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam(value = "password") String password,HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user =  (User) request.getSession().getAttribute("userToChangePassword");
        user.setPasswordHash(SecurityUtil.encryptPassword(password));
        userService.updateUser(user);
        return result;
    }
}
