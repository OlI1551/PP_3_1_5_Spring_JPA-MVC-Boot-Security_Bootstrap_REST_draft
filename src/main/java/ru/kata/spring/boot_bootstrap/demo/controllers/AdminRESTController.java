package ru.kata.spring.boot_bootstrap.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_bootstrap.demo.models.User;
import ru.kata.spring.boot_bootstrap.demo.services.UserService;
import ru.kata.spring.boot_bootstrap.demo.services.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminRESTController {

    private final UserService userService;

    @Autowired
    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public List<User> showAllUsers() {

//        List<User> users = userService.listUsers();
//        System.out.println(users);
//
//        return users.toString();
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

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
}
