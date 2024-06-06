package com.papook.usergod.repository.impl;

import java.util.Optional;

import com.papook.usergod.model.User;
import com.papook.usergod.repository.UserRepository;

/**
 * User repository for CRUD operations
 * on the User entity. This class is
 * an implementation of the UserRepository
 * interface.
 * 
 * @author papook
 */
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User save(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
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
