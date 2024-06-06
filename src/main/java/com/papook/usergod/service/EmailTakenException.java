package com.papook.usergod.service;

import com.papook.usergod.model.ExceptionEntity;

/**
 * Exception thrown when a user with the same email
 * already exists in the database.
 * 
 * @author papook
 */
public class EmailTakenException extends RuntimeException {
    public static ExceptionEntity getExceptionEntity() {
        return new ExceptionEntity(400, "A user with the same email already exists.");
    }
}
