package org.modernbank.frontend.teller.client.party;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.modernbank.frontend.teller.utility.client.BasicPageRequest;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;

@Path("/")
@RegisterRestClient(configKey = "party-server")
public interface PartyProxy {

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response searchParty(
        @HeaderParam("Authorization") String authorization,
        @BeanParam BasicPageRequest pageRequest,
        PartyRequestSearch searchRequest
    );

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllParties(
        @HeaderParam("Authorization") String authorization
    );

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getParty(
        @HeaderParam("Authorization") String authorization,
        @PathParam("id") String id
    );
}
