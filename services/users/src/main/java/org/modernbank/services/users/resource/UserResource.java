package org.modernbank.services.users.resource;

import java.util.stream.Collectors;

import org.modernbank.services.users.service.UserService;
import org.modernbank.services.users.utility.UserMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/user")
public class UserResource {
    @Inject UserService userService;
    @Inject UserMapper userMapper;

    @Path("/")
    @GET
    public Response getAllUsers() {
        try {
            return Response.ok(
                userService.getAllUsers()
                    .stream()
                    .map(user -> userMapper.toRestData(user))
                    .collect(Collectors.toList())                    
            ).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
