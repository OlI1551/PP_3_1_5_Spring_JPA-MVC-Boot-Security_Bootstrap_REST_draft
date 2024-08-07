//package ru.kata.spring.boot_bootstrap.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_bootstrap.demo.models.User;
//import ru.kata.spring.boot_bootstrap.demo.services.UserServiceImpl;
//
//import java.util.List;
//
//
//@Controller
//public class AdminController {
//
//    private final UserServiceImpl userService;
//
//    @Autowired
//    public AdminController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/admin")
//    public String showAdminPage(@ModelAttribute("newUser") User newUser,
//                                Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User authenticatedUser = (User) authentication.getPrincipal();
//        model.addAttribute("admin", authenticatedUser);
//        model.addAttribute("user", authenticatedUser);
//
//        List<User> users = userService.listUsers();
//        model.addAttribute("users", users);
//
//        return "admin_page";
//    }
//
//    @PostMapping("/create")
//    public String createUser(@ModelAttribute("newUser") User newUser,
//                             @RequestParam("role") String role) {
//        userService.create(newUser, role);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/edit")
//    public String updateUser(@ModelAttribute("user") User user,
//                             @RequestParam("role") String role) {
//        userService.update(user, role);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/delete")
//    public String deleteUser(@ModelAttribute("user") User user) {
//        userService.delete(user);
//        return "redirect:/admin";
//    }
//}
