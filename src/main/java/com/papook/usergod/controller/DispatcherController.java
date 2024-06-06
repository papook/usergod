package com.papook.usergod.controller;

import static com.papook.usergod.config.ServerConfig.USERS_ENDPOINT;

import java.net.URI;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DispatcherController {

	@Context
	UriInfo uriInfo;

	@GET
	public Response dispatcher() {
		// Create URIs for the register and getUsers endpoints
		URI registerUserUri = uriInfo.getAbsolutePathBuilder()
				.path("register")
				.build();

		URI getUsersUri = uriInfo.getAbsolutePathBuilder()
				.path(USERS_ENDPOINT)
				.build();

		return Response.ok()
				.link(registerUserUri, "register")
				.link(getUsersUri, "getUsersCollection")
				.build();
	}
}
