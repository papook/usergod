package com.papook.usergod.service;

import jakarta.ejb.ApplicationException;

/**
 * Exception thrown when the ID is null.
 * 
 * @author papook
 */
@ApplicationException
public class NullIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
