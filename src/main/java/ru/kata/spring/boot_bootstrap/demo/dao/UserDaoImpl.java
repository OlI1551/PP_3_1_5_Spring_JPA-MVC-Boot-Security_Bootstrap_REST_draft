package ru.kata.spring.boot_bootstrap.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_bootstrap.demo.models.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public User findUserByEmail(String email) {
      TypedQuery<User> query = entityManager.createQuery(
              "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email =: email", User.class); // запрос JPQL
      query.setParameter("email", email);
      try {
         return query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   public List<User> getUsersList() {
      return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
   }

   @Override
   public User getUserById(Long id) {
      if (entityManager.find(User.class, id) == null) {
         throw new EntityNotFoundException(String.format("User with ID '%s'  not found", id));
      }
      return entityManager.find(User.class, id);
   }

   @Override
   public void addUser(User user) {
      entityManager.persist(user);
   }

   @Override
   public void updateUser(User user) {
      if (entityManager.find(User.class, user.getId()) == null) {
         throw new EntityNotFoundException(String.format("User with ID '%s'  not found", user.getId()));
      }
      entityManager.merge(user);
   }

   @Override
   public void deleteUser(User user) {
      if (entityManager.find(User.class, user.getId()) == null) {
         throw new EntityNotFoundException(String.format("User with ID '%s'  not found", user.getId()));
      }
      entityManager.remove(user);
   }
}
