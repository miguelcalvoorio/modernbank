package org.modernbank.backend.party.resource;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.NoCache;

import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import org.modernbank.backend.party.client.PartyContainerRest;
import org.modernbank.backend.party.client.PartyRest;
import org.modernbank.backend.party.client.PartySearchRequest;
import org.modernbank.backend.party.mapper.PartyMapper;
import org.modernbank.backend.party.model.Party;
import org.modernbank.backend.party.model.PartyRepository;
import org.modernbank.backend.party.utilities.client.BasicPagingRequest;
import org.modernbank.backend.party.utilities.client.BasicRequest;
import org.modernbank.backend.party.utilities.client.PageRest;
import org.modernbank.backend.party.utilities.model.AuditModel;

@Path("/")
@Tag(name = "Party")
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
@ApplicationScoped
public class PartyResource {
    @Inject Logger logger;
    @Inject PartyRepository repository;
    @Inject PartyMapper partyMapper;

    @Path("/")
    @GET
    @RolesAllowed("party")
    @NoCache
    @Operation(summary = "Returns all parties from the platform")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PartyContainerRest.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "There is NOT parties")
    public Uni<Response> getAllParties(@BeanParam @Valid BasicPagingRequest request) {
        try {
            logger.info("getAllParties()");
            logger.info("Request: " + request);

            return repository.findAllParties(request.getPage(), request.getSize(), request.getSortBy())
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().transform(pagePartyModel -> {
                    logger.debug("Delivering parties list");

                    if (pagePartyModel.getPage().getTotal() == 0 || pagePartyModel.getPage().getPageCount() <= request.getPage()) {
                        return Response.noContent().build();
                    }

                    PageRest paginationRest = new PageRest();
                    paginationRest.setPage(request.getPage());
                    paginationRest.setSize(request.getSize());
                    paginationRest.setTotal(pagePartyModel.getPage().getTotal());
                    paginationRest.setPageCount(pagePartyModel.getPage().getPageCount());

                    PartyContainerRest partyContainerRest = new PartyContainerRest();
                    partyContainerRest.setPage(paginationRest);
                    partyContainerRest.setData(
                        pagePartyModel.getData()
                            .stream()
                            .map(party -> mapPartyEntityToRest(party, request.isDetail()))
                            .collect(Collectors.toList())
                    );
                    
                    return Response.ok(partyContainerRest).build();
                });
        
        } catch(Exception ex) {
            logger.info(ex.getMessage());
            return Uni.createFrom().item(Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }
    }

    @Path("/{id}")
    @GET
    @RolesAllowed("party")
    @Operation(summary = "Retrieves a party for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PartyRest.class)))
    @APIResponse(responseCode = "204", description = "Party is not found for a given identifier")
    public Uni<Response> getParty(@PathParam("id") String id, @BeanParam @Valid BasicRequest request) {
        try {
            return repository.findPartyById(id)
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().transform(party -> Response.ok(mapPartyEntityToRest(party, request.isDetail())).build());
        
        } catch (Exception ex) {
            logger.error("Trying to get party with uuid: " + id + " -> " + ex.getMessage());
            return Uni.createFrom().item(Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }
    }

    @Path("/search")
    @POST
    @RolesAllowed("party")
    @Operation(summary = "Search parties from the platform")
    @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PartyContainerRest.class)))
    @APIResponse(responseCode = "204", description = "There is NOT parties")
    public Uni<Response> searchParties(@BeanParam @Valid BasicPagingRequest request, @Valid PartySearchRequest searchRequest) {
        try {
            return repository.searchParties(request.getPage(), request.getSize(), searchRequest, request.getSortBy())
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().transform(pagePartyModel -> {
                    logger.debug("Delivering party's search results");

                    if (pagePartyModel.getPage().getTotal() == 0 || pagePartyModel.getPage().getPageCount() <= request.getPage()) {
                        return Response.noContent().build();
                    }
                    
                    PageRest paginationRest = new PageRest();
                    paginationRest.setPage(request.getPage());
                    paginationRest.setSize(request.getSize());
                    paginationRest.setTotal(pagePartyModel.getPage().getTotal());
                    paginationRest.setPageCount(pagePartyModel.getPage().getPageCount());

                    PartyContainerRest partyContainerRest = new PartyContainerRest();
                    partyContainerRest.setPage(paginationRest);
                    partyContainerRest.setData(
                        pagePartyModel.getData()
                            .stream()
                            .map(party -> mapPartyEntityToRest(party, request.isDetail()))
                            .collect(Collectors.toList())
                    );

                    return Response.ok(partyContainerRest).build();
                });
        
        } catch (Exception ex) {
            logger.error("Trying to search parties -> " + ex.getMessage());
            return Uni.createFrom().item(Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }
    }

    @Path("/")
    @POST
    @RolesAllowed("party")
    @Operation(summary = "Creates a new valid party")
    @APIResponse(responseCode = "201", description = "The URI of the created party", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    public Uni<Response> newParty(@Valid PartyRest party, @Context UriInfo uriInfo) {
        logger.info("Creating a new party");
        try {
            return repository.persistParty(partyMapper.toEntity(completePartyData(party)))
                .map(p -> {
                    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(p.getUuid());
                    logger.debug("New party created with URI: " + builder.build().toString());
                    return Response.created(builder.build()).build();
                });
        } catch (Exception ex) {
            logger.error("Trying to create a new party -> " + ex.getMessage());
            logger.error("------> " + ex.getClass().getCanonicalName());

            return Uni.createFrom().item(Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }
    }

    @Path("/{id}")
    @PUT
    @RolesAllowed("party")
    @Operation(summary = "Updates an exiting  party")
    @APIResponse(responseCode = "200", description = "The updated party", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PartyRest.class)))
    public Uni<Response> updateParty(@Valid PartyRest party, @PathParam("id") String id) {
        try {
            return repository.findPartyById(id)
                .onItem().ifNull().failWith(NotFoundException::new)
                .call(p -> {
                    p.setName(party.getName());
                    p.setLastName(party.getLastName());
                    p.setUuid(party.getUuid());
                    return repository.updateParty(p);
                }).map(output -> {
                    logger.debug("Updated party " + output);
                    return Response.ok(partyMapper.toRestData(output)).build();
                });
        } catch (Exception ex) {
            logger.error("Trying to update party wity uuid: " + id + " -> " + ex.getMessage());
            return Uni.createFrom().item(Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }
    }

    @Path("/{id}")
    @DELETE
    @RolesAllowed("party")
    @Operation(summary = "Deletes an exiting party")
    @APIResponse(responseCode = "204")
    public Uni<Response> deleteParty(@PathParam("id") String id) {
        try {
            return repository.findPartyById(id)
                .onItem().ifNull().failWith(NotFoundException::new)
                .call(p -> {
                    logger.debug("Trying to delete party with uuid " +  id + " (id: " + p.getId() + ")");
                    return repository.deleteParty(p.getId());
                }).map(output -> {
                    logger.debug("Party deleted with id: " + id);
                    return Response.noContent().build();
                });
        } catch (Exception ex) {
            logger.error("Trying to delete party wity uuid: " + id + " -> " + ex.getMessage());
            return Uni.createFrom().item(Response.status(Status.INTERNAL_SERVER_ERROR).build());
        }
    }

    // Mapping utility
    private PartyRest mapPartyEntityToRest(Party party, boolean detail) {
        if (detail) {
            return partyMapper.toDetailedRestData(party);
        } else {
            return partyMapper.toRestData(party);
        }
    }

    // Automatic field completion
    private PartyRest completePartyData(PartyRest party) {
        // Create new UUID if needed
        if (party.getUuid() == null) party.setUuid(UUID.randomUUID().toString());

        // Set creation dates
        LocalDateTime currentDateTime = LocalDateTime.now();

        AuditModel createdBy = new AuditModel();
        createdBy.setUuid("");
        createdBy.setFullName("");
        createdBy.setTimestamp(currentDateTime);
        party.setCreatedBy(createdBy);

        AuditModel lastUpdateddBy = new AuditModel();
        lastUpdateddBy.setUuid("");
        lastUpdateddBy.setFullName("");
        lastUpdateddBy.setTimestamp(currentDateTime);
        party.setCreatedBy(lastUpdateddBy);

        return party;
    }
}
