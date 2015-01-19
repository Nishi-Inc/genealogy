package com.nishionline.genealogy.service;

import com.nishionline.genealogy.dto.UserResponseDTO;
import com.nishionline.genealogy.model.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * This is an interface to the outer world hence maintaining version here is most important
 * @author Alok
 * @since 03-11-2014
 * @version 0.1.0
 */
@WebService
@Path("/user")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface UserService {
    @GET
    @Path("/{id}")
    public User find(@PathParam("id") String id);

    @GET
    public UserResponseDTO find(MultivaluedMap<String, String> params);

    @POST
    public void create(MultivaluedMap<String, String> params);

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, MultivaluedMap<String, String> params);

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id);
}
