package com.papook.usergod.service;

import com.papook.usergod.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmailTakenExceptionMapper implements ExceptionMapper<EmailTakenException> {

    @Override
    public Response toResponse(EmailTakenException exception) {
        int status = 400;

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                "The email is already taken by another user.");

        return Response.status(status)
                .entity(errorMessage)
                .build();
    }

}
