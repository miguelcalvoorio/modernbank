package org.modernbank.backend.party.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Tag(name = "Party")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BasicResource {
    @Inject Logger logger;

    @Path("/health")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "Parties API is up and running";
    }
}
