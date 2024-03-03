package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;


public interface RoleDao {
    Role getRoleById(Long id);
    void addRole(Role role);
}
