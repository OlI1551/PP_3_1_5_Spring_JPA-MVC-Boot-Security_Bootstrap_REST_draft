package ru.kata.spring.boot_bootstrap.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_bootstrap.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    List<User> listUsers();
    User getUserById(Long userId);
    void create(User user, String role);
    void update(User user, String role);
    void delete(User user);
}
