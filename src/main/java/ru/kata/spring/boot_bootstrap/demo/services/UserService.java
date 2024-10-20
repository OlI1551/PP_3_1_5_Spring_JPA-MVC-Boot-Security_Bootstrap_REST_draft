package ru.kata.spring.boot_bootstrap.demo.services;

import ru.kata.spring.boot_bootstrap.demo.models.User;
import java.util.List;


public interface UserService {
    List<User> listUsers();
    User getUserById(Long userId);
    void create(User user, String role);
    void update(User user, String role);
    void delete(User user);
}
