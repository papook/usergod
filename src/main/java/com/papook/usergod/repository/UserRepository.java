package com.papook.usergod.repository;

import com.papook.usergod.model.User;

/**
 * User repository interface for CRUD operations
 * on the User entity.
 * 
 * @author papook
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Count the number of users with the given first name and last name.
     * 
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @return The number of users with the given first name and last name
     */
    long countByFirstNameAndLastName(String firstName, String lastName);

}
