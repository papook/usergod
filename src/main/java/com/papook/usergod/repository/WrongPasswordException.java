package com.papook.usergod.repository;

import jakarta.ejb.ApplicationException;

/**
 * Exception thrown when a user tries to modify their user information with an
 * invalid password passed in request body.
 * 
 * @author papook
 */
@ApplicationException
public class WrongPasswordException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}
