package com.papook.usergod.repository;

import java.util.Optional;

import com.papook.usergod.config.ServerConfig;

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
     * Updates an entity in the database
     * and returns the updated entity.
     * 
     * @param entity Entity to update
     * @return Updated entity
     */
    T update(T entity);

    /**
     * Finds an entity by its ID.
     * If the entity is found, it is
     * returned as an Optional. Otherwise,
     * an empty Optional is returned.
     * 
     * @param id Entity ID, must not be null
     * @return Optional of the entity
     */
    Optional<T> findById(ID id);

    /**
     * Finds all entities with the given
     * first name and last name. The result
     * is paginated. Default page size is
     * {@value ServerConfig#PAGE_SIZE}, set
     * in the {@link ServerConfig} class and
     * cannot be changed.
     * 
     * @param firstName First name to search for
     * @param lastName  Last name to search for
     * @param page      Page number
     * @return Iterable of entities
     * 
     * @see ServerConfig
     */
    Iterable<T> findAll(String firstName, String lastName, int page);

    /**
     * Deletes an entity by its ID.
     * 
     * @param id Entity ID, must not be null
     */
    void deleteById(ID id);

}
