package com.papook.usergod.repository.impl;

import static com.papook.usergod.config.ServerConfig.PAGE_SIZE;

import java.util.Optional;

import com.papook.usergod.model.User;
import com.papook.usergod.repository.IdMismatchException;
import com.papook.usergod.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
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

    /**
     * @throws IdMismatchException      if the ID of the entity representation
     *                                  does not match the id parameter of the
     *                                  method
     * @throws IllegalArgumentException if the id parameter is null
     */
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

    /**
     * @throws IllegalArgumentException if the id parameter is null
     */
    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    /**
     * @param firstName the first name of the user. "%" to not filter by firstName
     * @param lastName  the last name of the user. "%" to not filter by lastName
     */
    @Override
    public Iterable<User> findAll(String firstName, String lastName, int page) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
