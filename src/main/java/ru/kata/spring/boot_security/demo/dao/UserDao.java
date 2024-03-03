package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;


public interface UserDao {
   User findByEmail(String email);
   List<User> getUsersList();
   User getUserById(Long id);
   void addUser(User user);
   void updateUser(User user);
   void deleteUser(User user);
}
