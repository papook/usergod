package com.papook.usergod.service;

import com.papook.usergod.model.User;
import com.papook.usergod.repository.UserRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private UserRepository userRepository;

    public User createUser(User user) {
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());

        // If a user with the same email already exists, throw an exception
        if (existsByEmail) {
            throw new UserExistsException();
        }

        return userRepository.save(user);
    }

    public User modifyUser(Long id, User user) {
        return userRepository.update(id, user);
    }
}
