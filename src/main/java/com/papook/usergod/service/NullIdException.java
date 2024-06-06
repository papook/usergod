package com.papook.usergod.service;

import com.papook.usergod.model.ExceptionEntity;

/**
 * Exception thrown when the ID is null.
 * 
 * @author papook
 */
public class NullIdException extends RuntimeException {
    public static ExceptionEntity getExceptionEntity() {
        return new ExceptionEntity(400, "The ID of the entity representation must not be null.");
    }
}
