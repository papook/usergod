package com.papook.usergod.repository.impl;

import static com.papook.usergod.config.Constants.PAGE_SIZE;

import java.util.Optional;

import com.papook.usergod.model.User;
import com.papook.usergod.repository.IdMismatchException;
import com.papook.usergod.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

/**
 * User repository for CRUD operations
 * on the User entity. This class is
 * an implementation of the UserRepository
 * interface.
 * 
 * @author papook
 */
@ApplicationScoped
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "user_pu")
    private EntityManager entityManager;

    @Override
    public User save(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User update(Long id, User entity) {
        // Check if the ID is null
        if (id == null) {
            throw new IllegalArgumentException();
        }

        // Check if the ID of the entity representation matches the id parameter
        if (!id.equals(entity.getId())) {
            throw new IdMismatchException();
        }

        boolean exists = entityManager.find(User.class, id) != null;

        if (exists) {
            entityManager.merge(entity);
            return null;
        } else {
            entityManager.persist(entity);
            return entity;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Iterable<User> findAll(String firstName, String lastName, int page) {
        // Replace empty strings with % to match all
        firstName = firstName.isEmpty() ? "%" : firstName;
        lastName = lastName.isEmpty() ? "%" : lastName;

        // Ensure that the page number is not less than 1
        page = Math.max(1, page);
        // Calculate the start position of the query
        int startPosition = (page - 1) * PAGE_SIZE;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        cq.select(root).where(
                cb.like(root.get("firstName"), firstName),
                cb.like(root.get("lastName"), lastName));

        TypedQuery<User> query = entityManager.createQuery(cq)
                .setFirstResult(startPosition)
                .setMaxResults(PAGE_SIZE);

        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<User> root = cq.from(User.class);

            cq.select(cb.count(root)).where(cb.equal(root.get("email"), email));

            TypedQuery<Long> query = entityManager.createQuery(cq);

            return query.getSingleResult() > 0;
        } catch (NoResultException e) {
            return false; // If no result is found, return false
        }
    }

}
