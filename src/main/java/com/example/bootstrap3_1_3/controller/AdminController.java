package com.example.bootstrap3_1_3.controller;

import com.example.bootstrap3_1_3.model.Role;
import com.example.bootstrap3_1_3.model.User;
import com.example.bootstrap3_1_3.service.AdminService;
import com.example.bootstrap3_1_3.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping()
public class AdminController {

    private AdminService adminService;
    private RoleService roleService;


    @Autowired
    public AdminController(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @RequestMapping("/admin")
    public String showAllUsers(Model model, Principal principal) {
        List<User> list = adminService.getAllUsers();

        model.addAttribute("attribute", list);
        model.addAttribute("userToAdd", new User());
        model.addAttribute("activeUser", adminService.loadUserByUsername(principal.getName()));
        return "adminInterface";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("userToAdd") User user,
                          @RequestParam(required = false) String roleToAdd) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole("USER"));
        if (roleToAdd != null && roleToAdd.equals("ADMIN")) {
            roles.add(roleService.getRole("ADMIN"));
        }

        user.setRoles(roles);
        adminService.add(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/update", method={RequestMethod.PUT, RequestMethod.GET})
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(required = false) String roleAdmin) {

        Set<Role> roles = new HashSet<>();
        Role role =roleService.getRole("USER");
        roles.add(role);
        if (roleAdmin != null && roleAdmin.equals("ADMIN")) {
            roles.add(roleService.getRole("ADMIN"));
        }
        user.setRoles(roles);

        adminService.updateUser(user.getId(),user);
        return "redirect:/admin";
    }

    @RequestMapping("/delete")
    public String delete(User user) {
        adminService.delete(user.getId());
        return "redirect:/admin";
    }

    @RequestMapping("/myInfo")
    public String showUserInfo(Principal principal, Model model) {
        model.addAttribute("activeUser", adminService.loadUserByUsername(principal.getName()));

        return "userInterface";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public User getOne(int id) {
        return adminService.getUser(id);
    }
}

