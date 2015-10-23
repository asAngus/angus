package com.javachen.resteasy.rest;

import com.javachen.resteasy.domain.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ClientProxy {

    String URL = "http://localhost:8080/resteasy-example-service/api/";

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("getUser")
    User getUser();
}
