package com.papook.usergod.repository;

import java.util.Optional;

import com.papook.usergod.config.Constants;
import com.papook.usergod.model.User;

/**
 * Generic CRUD repository
 * 
 * @param <T>  Entity type
 * @param <ID> Entity ID type
 * @author papook
 */
public interface CrudRepository<T, ID> {

    /**
     * Saves an entity to the database
     * and returns the saved entity
     * with the generated ID.
     * 
     * @param entity Entity to save
     * @return Saved entity
     */
    T save(T entity);

    /**
     * Updates (or creates if it doesn't exist) an entity in the database.
     * If the entity with the given ID exists,
     * it is updated. Otherwise, a new entity is
     * created with the given ID.
     * 
     * @param id     Entity ID, should match the ID of the entity
     *               passed in the entity parameter. Must not be null
     * @param entity Entity representation
     * @return User representation of the entity
     *         if the update was successful
     * 
     * @throws IdMismatchException      if the ID of the entity representation
     *                                  does not match the id parameter of the
     *                                  method
     * @throws IllegalArgumentException if the id parameter is null
     * 
     */
    User update(ID id, T entity);

    /**
     * Finds an entity by its ID.
     * If the entity is found, it is
     * returned as an Optional. Otherwise,
     * an empty Optional is returned.
     * 
     * @param id Entity ID, must not be null
     * @return Optional of the entity
     * 
     * @throws IllegalArgumentException if the id parameter is null
     * 
     */
    Optional<T> findById(ID id);

    /**
     * Finds all entities with the given
     * first name and last name. The result
     * is paginated. Default page size is
     * {@value Constants#PAGE_SIZE}, set
     * in the {@link Constants} class and
     * cannot be changed.
     * 
     * @param firstName First name to search for
     * @param lastName  Last name to search for
     * @param page      Page number
     * @return Iterable of entities
     * 
     * @see Constants
     */
    Iterable<T> findAll(String firstName, String lastName, int page);

    /**
     * Deletes an entity by its ID.
     * 
     * @param id Entity ID, must not be null
     */
    void deleteById(ID id);

    /**
     * Checks if an entity with the given ID exists.
     * 
     * @param id ID to check
     * @return true if an entity with the given ID exists,
     *         false otherwise
     */
    boolean existsById(ID id);
    
    /**
     * Checks if a user with the given email exists.
     * 
     * @param email Email to check
     * @return true if a user with the given email exists,
     *         false otherwise
     */
    boolean existsByEmail(String email);
}
