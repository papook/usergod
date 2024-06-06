package com.papook.usergod.service;

import java.util.Optional;

import com.papook.usergod.model.User;
import com.papook.usergod.repository.UserRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private UserRepository userRepository;

    /**
     * Create a new user. If a user with the same email already exists, throw an
     * exception.
     * 
     * @param user The user to create
     * @return The created user
     * 
     * @throws EmailTakenException If a user with the same email already exists
     */
    public User createUser(User user) {
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());

        // If a user with the same email already exists, throw an exception
        if (existsByEmail) {
            throw new EmailTakenException();
        }

        return userRepository.save(user);
    }

    public Iterable<User> getUsers(
            final String firstName,
            final String lastName,
            final int page) {
        return userRepository.findAll(firstName, lastName, page);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User modifyUser(Long id, User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new EmailTakenException();
        }

        return userRepository.update(id, user);
    }
}
