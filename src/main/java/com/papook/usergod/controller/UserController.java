package com.papook.usergod.controller;

import static com.papook.usergod.config.Constants.CHANGE_PASSWORD_ENDPOINT;
import static com.papook.usergod.config.Constants.CHANGE_PASSWORD_REL;
import static com.papook.usergod.config.Constants.GET_SINGLE_USER_REL;
import static com.papook.usergod.config.Constants.REGISTER_ENDPOINT;
import static com.papook.usergod.config.Constants.UPDATE_USER_REL;
import static com.papook.usergod.config.Constants.USERS_ENDPOINT;
import static com.papook.usergod.config.Constants.USER_BY_ID_ENDPOINT;

import java.net.URI;

import com.papook.usergod.model.ChangePassword;
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
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

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

		page = Math.max(1, page);

		Link getSingeUser = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
				.path("id"))
				.rel(GET_SINGLE_USER_REL)
				.type(MediaType.APPLICATION_JSON)
				.build();

		Iterable<User> users = userService.getUsers(firstName, lastName, page);

		ResponseBuilder response = Response.ok(users).links(getSingeUser);

		// Add pagination links if necessary
		if (userService.nextPageAvailable(firstName, lastName, page)) {
			long nextPageNumber = userService.getNextPageNumber(page);

			UriBuilder uriBuilder = uriInfo.getRequestUriBuilder()
					.replaceQueryParam("page", nextPageNumber);

			Link nextPage = Link.fromUriBuilder(
					uriBuilder)
					.rel("next")
					.type(MediaType.APPLICATION_JSON)
					.build();

			response.links(nextPage);
		}
		if (userService.previousPageAvailable(firstName, lastName, page)) {
			long previousPageNumber = userService.getPreviousPageNumber(firstName, lastName, page);

			UriBuilder uriBuilder = uriInfo.getRequestUriBuilder()
					.replaceQueryParam("page", previousPageNumber);

			Link previousPage = Link.fromUriBuilder(uriBuilder)
					.rel("prev")
					.type(MediaType.APPLICATION_JSON)
					.build();

			response.links(previousPage);
		}

		return response.build();
	}

	@GET
	@Path(USER_BY_ID_ENDPOINT)
	public Response getUser(@PathParam("id") Long id) {
		Link getUsers = Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
				.path(USERS_ENDPOINT))
				.rel(GET_SINGLE_USER_REL)
				.type(MediaType.APPLICATION_JSON)
				.build();

		Link updateUser = Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
				.path(USERS_ENDPOINT)
				.path(String.valueOf(id)))
				.rel(UPDATE_USER_REL)
				.type(MediaType.APPLICATION_JSON)
				.build();

		Link changePassword = Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
				.path(USERS_ENDPOINT)
				.path(String.valueOf(id))
				.path("password"))
				.rel(CHANGE_PASSWORD_REL)
				.type(MediaType.APPLICATION_JSON)
				.build();

		return userService.getUser(id)
				.map(user -> Response.ok(user)
						.links(getUsers, updateUser, changePassword)
						.build())
				.orElse(Response.status(Response.Status.NOT_FOUND)
						.links(getUsers)
						.build());
	}

	@PUT
	@Path(USER_BY_ID_ENDPOINT)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") Long id, @Valid User user) {
		boolean exists = userService.getUser(id).isPresent();
		User modifyUser = userService.modifyUser(id, user);

		if (exists) {
			Link link = Link.fromUri(uriInfo.getBaseUriBuilder()
					.path(USERS_ENDPOINT + id)
					.build())
					.rel(GET_SINGLE_USER_REL)
					.type(MediaType.APPLICATION_JSON)
					.build();

			return Response.noContent()
					.links(link)
					.build();
		} else {
			return Response.created(uriInfo.getRequestUri()).entity(modifyUser).build();
		}
	}

	@PUT
	@Path(CHANGE_PASSWORD_ENDPOINT)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePassword(
			@PathParam("id") Long id,
			@Valid ChangePassword user) {
		userService.changePassword(id, user);

		Link getUserLink = Link.fromUri(uriInfo.getBaseUriBuilder()
				.path(USERS_ENDPOINT)
				.path(String.valueOf(id))
				.build())
				.rel(GET_SINGLE_USER_REL)
				.type(MediaType.APPLICATION_JSON)
				.build();

		return Response.noContent()
				.links(getUserLink)
				.build();
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
