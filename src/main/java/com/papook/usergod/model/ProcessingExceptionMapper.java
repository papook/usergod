package com.papook.usergod.model;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {

    @Override
    public Response toResponse(ProcessingException exception) {
        int status = Response.Status.BAD_REQUEST.getStatusCode();

        ErrorMessage errorMessage = new ErrorMessage(
                status,
                "Invalid request. Please check the request and try again.");

        return Response.status(status)
                .entity(errorMessage)
                .build();
    }

}
