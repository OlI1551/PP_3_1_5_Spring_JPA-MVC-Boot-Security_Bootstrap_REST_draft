package ru.kata.spring.boot_bootstrap.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_bootstrap.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
