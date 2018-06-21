package com.strazhevich.controller;

import com.strazhevich.entity.Application;
import com.strazhevich.entity.User;
import com.strazhevich.service.ApplicationService;
import com.strazhevich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
public class ApplicationController {

    private ApplicationService applicationService;
    private UserService userService;
    private String status = "all";
    private int count = 0;
    @Autowired
    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            model.addAttribute("user_request", applicationService.findByUsername(username));
        }
        List<Application> applications = chooseListByStatus(status);
        model.addAttribute("list_request", applications);
        model.addAttribute("count_request",count);
        return "welcome";
    }

    @GetMapping("/add_request")
    public String addRequestPage(){
        return "add_request";
    }

    @PostMapping("/add_request")
    public String addRequest(@RequestParam String request, @RequestParam double bid){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            User user = userService.findByUsername(username);
            applicationService.addApplication(new Application(request,bid,new Date(), user));
        }
        return "redirect:/welcome";
    }

    @GetMapping("/change_status/{status}")
    public String changeStatus(@PathVariable String status){
        this.status = status;
        return "redirect:/welcome";
    }

    @GetMapping("/complete/{id}")
    public String completeById(@PathVariable Integer id){
        applicationService.completeById(id);
        return "redirect:/welcome";
    }

    @GetMapping("/deny/{id}")
    public String denyById(@PathVariable Integer id){
        applicationService.denyById(id);
        return "redirect:/welcome";
    }

    private List<Application> chooseListByStatus(String status){
        List<Application> applications = null;
        switch (status){
            case "all":
                applications = applicationService.findAllByStatusDesc();
                count = applicationService.getAllCount();
                break;
            case "completed":
                applications = applicationService.findByStatus(status);
                count = applicationService.getCountByStatus(status);
                break;
            case "denied":
                applications = applicationService.findByStatus(status);
                count = applicationService.getCountByStatus(status);
                break;
        }
        return applications;
    }
}
