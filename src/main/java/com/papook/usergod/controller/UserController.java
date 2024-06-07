package com.papook.usergod.controller;

import static com.papook.usergod.config.Constants.GET_SINGLE_USER_REL;
import static com.papook.usergod.config.Constants.REGISTER_ENDPOINT;
import static com.papook.usergod.config.Constants.USERS_ENDPOINT;
import static com.papook.usergod.config.Constants.USER_BY_ID_ENDPOINT;

import java.net.URI;

import com.papook.usergod.model.User;
import com.papook.usergod.service.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

	@Inject
	private UserService userService;

	@Context
	UriInfo uriInfo;

	@GET
	@Path(USERS_ENDPOINT)
	public Response getUsers(
			@DefaultValue("") @QueryParam("firstName") String firstName,
			@DefaultValue("") @QueryParam("lastName") String lastName,
			@QueryParam("page") int page) {

		Iterable<User> users = userService.getUsers(firstName, lastName, page);

		return Response.ok(users).build();
	}

	@GET
	@Path(USER_BY_ID_ENDPOINT)
	public Response getUser(@PathParam("id") Long id) {
		return userService.getUser(id)
				.map(user -> Response.ok(user).build())
				.orElse(Response.status(Response.Status.NOT_FOUND).build());
	}

	@PUT
	@Path(USER_BY_ID_ENDPOINT)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") Long id, @Valid User user) {
		User modifyUser = userService.modifyUser(id, user);

		if (modifyUser == null) {
			Link link = Link.fromUri(uriInfo.getBaseUriBuilder()
					.path(USERS_ENDPOINT + id)
					.build())
					.rel(GET_SINGLE_USER_REL)
					.build();

			return Response.noContent()
					.links(link)
					.build();
		} else {
			return Response.created(uriInfo.getRequestUri()).entity(modifyUser).build();
		}

	}

	@POST
	@Path(REGISTER_ENDPOINT)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(@Valid User user) {
		User createdUser = userService.createUser(user);
		String id = String.valueOf(createdUser.getId());

		URI location = uriInfo.getBaseUriBuilder()
				.path(USERS_ENDPOINT)
				.path(id)
				.build();

		return Response.created(location)
				.entity(createdUser)
				.build();
	}
}
