package com.papook.usergod.service;

/**
 * Exception thrown when the ID is null.
 * 
 * @author papook
 */
public class NullIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String message = "The ID cannot be null.";

    public NullIdException() {
        super(message);
    }
}
