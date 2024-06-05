package com.papook.usergod.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("")
public class DispatcherController {

    @GET
    public Response dispatcher() {
        // TODO: Implement dispatcher and make body empty
        return Response.ok()
                .entity("Hello! This is UserGod API, which is under construction.")
                .link("LINKS", "REL")
                .build();
    }

}
