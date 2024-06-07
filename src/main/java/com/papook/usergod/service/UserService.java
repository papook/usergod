package com.papook.usergod.service;

import static com.papook.usergod.config.Constants.PAGE_SIZE;

import java.util.Optional;

import com.papook.usergod.model.ChangePassword;
import com.papook.usergod.model.User;
import com.papook.usergod.repository.UserRepository;
import com.papook.usergod.repository.WrongPasswordException;
import com.papook.usergod.utils.PasswordTool;

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

        // Hash the password before saving it
        String hashedPassword = PasswordTool.hash(user.getPassword());
        user.setPassword(hashedPassword);

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
        boolean userExists = userRepository.existsById(id);

        // If the user does not exist, throw an exception
        if (!userExists) {
            throw new UserNotFoundException();
        }

        User existingUser = userRepository.findById(id).get();
        String providedPassword = user.getPassword();
        String currentPasswordHash = existingUser.getPassword();

        boolean passwordMatches = PasswordTool.verify(providedPassword, currentPasswordHash);

        // If the provided password does not match the current one, throw an exception
        if (!passwordMatches) {
            throw new WrongPasswordException();
        }

        // Hash the password before saving it
        String hashedPassword = PasswordTool.hash(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.update(id, user);
    }

    public void changePassword(Long id, ChangePassword changePassword) {
        boolean userExists = userRepository.existsById(id);

        // If the user does not exist, return null
        if (!userExists) {
            throw new UserNotFoundException();
        }

        User existingUser = userRepository.findById(id).get();
        String providedPassword = changePassword.getOldPassword();
        String currentPasswordHash = existingUser.getPassword();

        boolean passwordMatches = PasswordTool.verify(providedPassword, currentPasswordHash);

        // If the provided password does not match the current one, throw an exception
        if (!passwordMatches) {
            throw new WrongPasswordException();
        }

        // Hash the new password before saving it
        String hashedPassword = PasswordTool.hash(changePassword.getNewPassword());
        existingUser.setPassword(hashedPassword);

        userRepository.update(id, existingUser);
    }

    public boolean previousPageAvailable(String firstName, String lastName, int page) {
        return page > 1;
    }

    public boolean nextPageAvailable(String firstName, String lastName, int page) {
        page = Math.max(1, page);

        long pageCount = getPageCount(firstName, lastName);
        return pageCount > page;
    }

    public long getNextPageNumber(int page) {
        return page + 1;
    }

    public long getPreviousPageNumber(String firstName, String lastName, int page) {
        // Get the number of pages available
        long pageCounter = getPageCount(firstName, lastName);

        // If the page is greater than the page counter, return the page counter
        if (page - pageCounter > 0) {
            return Math.max(1, pageCounter);
        } else {
            return Math.max(1, page - 1);
        }
    }

    private long getPageCount(String firstName, String lastName) {
        long count = userRepository.countByFirstNameAndLastName(firstName, lastName);
        return (count + PAGE_SIZE - 1) / PAGE_SIZE;
    }

}
