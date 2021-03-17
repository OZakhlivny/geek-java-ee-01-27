package ru.geekbrains.rest;

import ru.geekbrains.dto.CategoryDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryDto category);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryDto category);

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}
