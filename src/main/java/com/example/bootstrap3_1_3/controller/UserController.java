package com.example.bootstrap3_1_3.controller;

import com.example.bootstrap3_1_3.model.User;
import com.example.bootstrap3_1_3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final AdminService adminService;

    @Autowired
    public UserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/user")
    public String showUserInfo(Principal principal, Model model) {
        model.addAttribute("activeUser", adminService.loadUserByUsername(principal.getName()));
        return "userInterface";
    }
}
