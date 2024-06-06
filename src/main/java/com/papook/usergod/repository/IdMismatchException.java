package com.papook.usergod.repository;

/**
 * Exception thrown when the ID of the entity representation passed as a request
 * body does not match the ID parameter of update the method.
 * 
 * @author papook
 */
public class IdMismatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String message = "The ID in the request body does not match the ID in the URL.";

    public IdMismatchException() {
        super(message);
    }

}
