package org.modernbank.frontend.teller.resource;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.modernbank.frontend.teller.client.party.PartyRestContainer;
import org.modernbank.frontend.teller.client.party.PartyProxy;
import org.modernbank.frontend.teller.client.party.PartyRest;
import org.modernbank.frontend.teller.client.party.PartyRequestSearch;
import org.modernbank.frontend.teller.utility.client.BasicPageRequest;
import org.modernbank.frontend.teller.utility.client.BasicRequest;

@Path("/api/v1/party")
public class PartyResource {
    @Inject Logger logger;
    @Inject @RestClient PartyProxy partyProxy;

    @Context HttpHeaders headers;

    @Path("/{id}")
    @GET
    @Operation(summary = "Retrieves a party for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PartyRest.class)))
    @APIResponse(responseCode = "204", description = "Party is not found for a given identifier")
    public Response getParty(@PathParam("id") String id, @BeanParam @Valid BasicRequest request) {
        try {
            return partyProxy.getParty(
                headers.getHeaderString("Authorization"),
                id
            );
            
        } catch (ClientWebApplicationException ex) {
            logger.error(ex.getMessage());
            return ex.getResponse();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Search parties from the platform")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PartyRestContainer.class)))
    @APIResponse(responseCode = "204", description = "There is NOT parties")
    public Response searchParties(@BeanParam @Valid BasicPageRequest pageRequest, @Valid PartyRequestSearch searchRequest) {
        try {
            return partyProxy.searchParty(
                headers.getHeaderString("Authorization"),
                pageRequest,
                searchRequest
            );
            
        } catch (ClientWebApplicationException ex) {
            logger.error(ex.getMessage());
            return ex.getResponse();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
