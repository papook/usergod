package com.papook.usergod.service;

import jakarta.ejb.ApplicationException;

/**
 * Exception thrown when a user with the same email
 * already exists in the database.
 * 
 * @author papook
 */
@ApplicationException
public class EmailTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
