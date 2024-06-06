package com.papook.usergod.repository;

import com.papook.usergod.model.ExceptionEntity;

/**
 * Exception thrown when the ID of the entity representation passed as a request
 * body does not match the ID parameter of update the method.
 * 
 * @author papook
 */
public class IdMismatchException extends RuntimeException {
    public static ExceptionEntity getExceptionEntity() {
        return new ExceptionEntity(400, "The ID of the entity representation does not match the ID path parameter.");
    }
}
