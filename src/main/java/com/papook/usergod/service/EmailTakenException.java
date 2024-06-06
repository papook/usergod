package com.papook.usergod.service;

/**
 * Exception thrown when a user with the same email
 * already exists in the database.
 * 
 * @author papook
 */
public class EmailTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String message = "A user with the same email already exists.";

    public EmailTakenException() {
        super(message);
    }

}
