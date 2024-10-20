package ru.kata.spring.boot_bootstrap.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_bootstrap.demo.models.User;
import ru.kata.spring.boot_bootstrap.demo.services.UserService;
import ru.kata.spring.boot_bootstrap.demo.services.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class UserRestController {
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
}
