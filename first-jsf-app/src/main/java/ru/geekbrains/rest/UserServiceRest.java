package ru.geekbrains.rest;

import ru.geekbrains.dto.UserDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/user")
public interface UserServiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<UserDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    UserDto findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(UserDto user);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(UserDto user);

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}
