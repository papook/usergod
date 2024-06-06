package com.papook.usergod.repository;

import com.papook.usergod.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IdMismatchExceptionMapper implements ExceptionMapper<IdMismatchException> {

    @Override
    public Response toResponse(IdMismatchException exception) {
        int status = 400;

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                "The ID in the request body does not match the ID in the URL.");

        return Response.status(status)
                .entity(errorMessage)
                .build();
    }

}