package com.javachen.resteasy.controller;

import com.javachen.resteasy.domain.User;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.GregorianCalendar;


@Controller
@Path("/api")
public class UserController {

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("getUser")
    public User getUser() {
        return new User("Gates", "Bill", new GregorianCalendar(1955, 9, 28),
                true);
    }
}
