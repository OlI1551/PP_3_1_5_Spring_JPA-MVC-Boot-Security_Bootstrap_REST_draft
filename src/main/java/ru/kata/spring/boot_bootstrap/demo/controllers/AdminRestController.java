package ru.kata.spring.boot_bootstrap.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_bootstrap.demo.models.User;
import ru.kata.spring.boot_bootstrap.demo.services.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @GetMapping("/new")
//    public ResponseEntity<User> getNewUser() {
//        User newUser = new User();
//        return new ResponseEntity<>(newUser, HttpStatus.OK);
//    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User newUser) {
        String role;
        if (newUser.getRoles().toString().contains("ROLE_ADMIN")) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }
        userService.create(newUser, role);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read")
    public ResponseEntity<User> getUserById(@RequestParam ("id") Long id) {
        User userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<HttpStatus> editUser(@Valid @RequestBody User editedUser) {
        String role;
        if (editedUser.getRoles().toString().contains("ROLE_ADMIN")) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }
        userService.update(editedUser, role);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(@Valid @RequestBody User deletedUser) {
        userService.delete(deletedUser);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/users")
//    public List<User> showAllUsers() {
//
////        List<User> users = userService.listUsers();
////        System.out.println(users);
////
////        return users.toString();
//        return userService.listUsers();
//    }

//    @GetMapping("/{id}")
//    public User getUser(@PathVariable("id") Long id){
//        return userService.getUserById(id);
//    }
}
