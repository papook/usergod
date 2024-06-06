package com.papook.usergod.repository;

import com.papook.usergod.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class WrongPasswordExceptionMapper implements ExceptionMapper<WrongPasswordException> {

    @Override
    public Response toResponse(WrongPasswordException exception) {
        int status = Response.Status.FORBIDDEN.getStatusCode();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                "Password is incorrect. Please try again.");

        return Response.status(status)
                .entity(errorMessage)
                .build();
    }

}
