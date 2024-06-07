package com.papook.usergod.repository;

import jakarta.ejb.ApplicationException;

/**
 * Exception thrown when the ID of the entity representation passed as a request
 * body does not match the ID parameter of update the method.
 * 
 * @author papook
 */
@ApplicationException
public class IdMismatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
