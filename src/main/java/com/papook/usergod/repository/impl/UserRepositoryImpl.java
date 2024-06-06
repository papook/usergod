package com.papook.usergod.repository.impl;

import java.util.Optional;

import com.papook.usergod.model.User;
import com.papook.usergod.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public User update(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Iterable<User> findAll(String firstName, String lastName, int page) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
