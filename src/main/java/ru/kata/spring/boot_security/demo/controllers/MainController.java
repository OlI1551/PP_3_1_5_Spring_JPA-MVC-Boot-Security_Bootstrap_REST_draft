package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.print.attribute.HashAttributeSet;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    private final UserService userService;

    private final RoleDao roleDao;

    @Autowired
    public MainController(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/admin")
    public String showAdminPage(@ModelAttribute("newUser") User newUser,
                                Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User) authentication.getPrincipal();
        String roles = authenticatedUser.getRoles().toString();
        if (roles.contains("ROLE_ADMIN")) {
            model.addAttribute("admin", authenticatedUser);
            model.addAttribute("user", authenticatedUser);
            List<User> users = userService.listUsers();
            model.addAttribute("users", users);
        } else {
            model.addAttribute("user", authenticatedUser);
        }
        return "_admin";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("newUser") User newUser,
                             @RequestParam("role") String role) {
        userService.create(newUser, role);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String role) {
        userService.edit(user, role);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.delete(user);
        return "redirect:/admin";
    }
}
