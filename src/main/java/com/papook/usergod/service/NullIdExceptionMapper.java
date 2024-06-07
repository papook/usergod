package com.papook.usergod.service;

import com.papook.usergod.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NullIdExceptionMapper implements ExceptionMapper<NullIdException> {

    @Override
    public Response toResponse(NullIdException exception) {
        int status = 400;

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                "The ID cannot be null.");

        return Response.status(status)
                .entity(errorMessage)
                .build();
    }

}
