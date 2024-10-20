package ru.kata.spring.boot_bootstrap.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_bootstrap.demo.dao.UserDao;
import ru.kata.spring.boot_bootstrap.demo.models.Role;
import ru.kata.spring.boot_bootstrap.demo.models.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.getUsersList();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @Transactional
    public void create(User user, String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        if (role.equals("ROLE_ADMIN")) {
            roles.add(new Role(2L, "ROLE_ADMIN"));
        }
        roles.add(new Role(1L, "ROLE_USER"));
        user.setRoles(roles);
        userDao.addUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @Transactional
    public void update(User user, String role) {
        if (!user.getPassword().equals(userDao.getUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        Set<Role> roles = new HashSet<>();
        if (role.equals("ROLE_ADMIN")) {
            roles.add(new Role(2L, "ROLE_ADMIN"));
        }
        roles.add(new Role(1L, "ROLE_USER"));
        user.setRoles(roles);
        userDao.updateUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @Transactional
    public void delete(User user) {
        userDao.deleteUser(userDao.getUserById(user.getId()));
    }
}
