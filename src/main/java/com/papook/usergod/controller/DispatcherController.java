package com.papook.usergod.controller;

import static com.papook.usergod.config.Constants.GET_USER_COLLECTION_REL;
import static com.papook.usergod.config.Constants.REGISTER_ENDPOINT;
import static com.papook.usergod.config.Constants.REGISTER_REL;
import static com.papook.usergod.config.Constants.USERS_ENDPOINT;

import java.net.URI;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
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
				.path(REGISTER_ENDPOINT)
				.build();

		URI getUsersUri = uriInfo.getAbsolutePathBuilder()
				.path(USERS_ENDPOINT)
				.build();

		URI tutorialUri = UriBuilder.fromUri("https://www.youtube.com/watch?v=dQw4w9WgXcQ").build();

		return Response.ok()
				.link(registerUserUri, REGISTER_REL)
				.link(getUsersUri, GET_USER_COLLECTION_REL)
				.link(tutorialUri, "tutorial")
				.build();
	}
}
