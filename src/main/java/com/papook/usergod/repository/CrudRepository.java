package com.papook.usergod.repository;

/**
 * Generic CRUD repository
 * @param <T> Entity type
 * @param <ID> Entity ID type
 * @author papook
 */
public interface CrudRepository<T, ID> {

    T save(T entity);

    T update(T entity);

    T findById(ID id);

    Iterable<T> findAll(
            int page
    // TODO: add filtering parameters
    );

    void deleteById(ID id);

}
