package com.strazhevich.controller;

import com.strazhevich.entity.User;
import com.strazhevich.service.SecurityService;
import com.strazhevich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationLoginController {

    private SecurityService securityService;
    private UserService userService;
    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.save(user);
        securityService.autoLogin(user.getUsername(),user.getConfirmPassword());
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
