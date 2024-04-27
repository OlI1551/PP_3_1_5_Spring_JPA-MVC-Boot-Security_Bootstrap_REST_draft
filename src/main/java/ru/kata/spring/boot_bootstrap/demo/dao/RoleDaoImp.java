package ru.kata.spring.boot_bootstrap.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_bootstrap.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
