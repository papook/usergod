package com.papook.usergod.controller;

import static com.papook.usergod.config.ServerConfig.REGISTER_ENDPOINT;
import static com.papook.usergod.config.ServerConfig.USERS_ENDPOINT;

import java.net.URI;

import com.papook.usergod.model.User;
import com.papook.usergod.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("")
public class UserController {

    @Inject
    private UserService userService;

    @Context
    UriInfo uriInfo;

    @GET
    @Path(USERS_ENDPOINT)
    public Response getUsers(
            @DefaultValue("%") @QueryParam("firstName") String firstName,
            @DefaultValue("%") @QueryParam("lastName") String lastName,
            @DefaultValue("1") @QueryParam("page") int page) {

        Iterable<User> users = userService.getUsers(firstName, lastName, page);

        return Response.ok(users)
                .build();
    }

    @GET
    @Path(USERS_ENDPOINT + "/{id: \\d+}")
    public Response getUser(@PathParam("id") Long id) {
        return userService.getUser(id)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path(REGISTER_ENDPOINT)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        User createdUser = userService.createUser(user);
        String id = String.valueOf(createdUser.getId());

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(id)
                .build();

        return Response.created(location).build();
    }
}
