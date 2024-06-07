package com.papook.usergod.service;

import com.papook.usergod.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException exception) {
        int status = 404;

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                "The user does not exist. Check the ID and try again.");

        return Response.status(status)
                .entity(errorMessage)
                .build();
    }

}
