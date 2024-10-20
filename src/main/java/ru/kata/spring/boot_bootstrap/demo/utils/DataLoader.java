package ru.kata.spring.boot_bootstrap.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_bootstrap.demo.models.Role;
import ru.kata.spring.boot_bootstrap.demo.models.User;
import ru.kata.spring.boot_bootstrap.demo.services.RoleService;
import ru.kata.spring.boot_bootstrap.demo.services.UserService;


@Component
public class DataLoader implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;


    @Autowired
    public DataLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        roleService.addRole(new Role("ROLE_USER"));
        roleService.addRole(new Role("ROLE_ADMIN"));
        userService.create(new User("admin",
                "admin", 35, "admin@mail.ru",
                "test_admin"), "ROLE_ADMIN");
        userService.create(new User("user",
                "user", 30, "user@mail.ru",
                "test_user"), "ROLE_USER");
        userService.create(new User("Ann",
                "Brown", 22, "Ann@mail.ru",
                "test_Ann"), "ROLE_USER");
        userService.create(new User("Nick",
                "White", 23, "Nick@mail.ru",
                "test_Nick"), "ROLE_USER");
        userService.create(new User("Bob",
                "Black", 25, "Bob@mail.ru",
                "test_Bob"), "ROLE_USER");
    }
}
