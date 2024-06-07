package com.papook.usergod.service;

import jakarta.ejb.ApplicationException;

/**
 * Exception thrown when the client tries to modify
 * a user that does not exist.
 * 
 * @author papook
 */
@ApplicationException
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
