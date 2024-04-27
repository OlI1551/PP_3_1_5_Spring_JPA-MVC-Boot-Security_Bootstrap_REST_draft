package ru.kata.spring.boot_bootstrap.demo.dao;

import ru.kata.spring.boot_bootstrap.demo.models.Role;


public interface RoleDao {
    Role getRoleById(Long id);
    void addRole(Role role);
}
