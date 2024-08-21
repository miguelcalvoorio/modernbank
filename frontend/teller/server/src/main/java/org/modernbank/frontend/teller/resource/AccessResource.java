package org.modernbank.frontend.teller.resource;

import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.modernbank.frontend.teller.client.access.AccessLogOutRequest;
import org.modernbank.frontend.teller.client.access.AccessProxy;
import org.modernbank.frontend.teller.client.access.AccessRefreshRequest;
import org.modernbank.frontend.teller.client.access.AccessTokenRequest;

@Path("/api/v1/access")
public class AccessResource {

    @ConfigProperty(name = "org.modernbank.oidc.client_id") String oidcClientId;
    @ConfigProperty(name = "org.modernbank.oidc.client_secret") String oidcClientSecret;

    @Inject Logger logger;
    @Inject @RestClient AccessProxy accessProxy;

    @Context HttpHeaders headers;

    @Path("/signin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAccessToken(AccessTokenRequest request) {
        try {
            return accessProxy.getAccessToken(
                oidcClientId,
                oidcClientSecret,
                request.getUsername(),
                request.getPassword(),
                "password");
        
        } catch (ClientWebApplicationException ex) {
            logger.error(ex.getMessage());
            return ex.getResponse();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/signout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logOut(AccessLogOutRequest request) {
        try {
            return accessProxy.logOut(
                oidcClientId,
                oidcClientSecret,
                request.getRefresh_token()
            );

        } catch (ClientWebApplicationException ex) {
            logger.error(ex.getMessage());
            return ex.getResponse();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/refresh-access")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAccessTokenWithRefreshToken(AccessRefreshRequest request) {
        try {
            return accessProxy.getAccessTokenWithRefreshToken(
                oidcClientId,
                oidcClientSecret,
                "refresh_token",
                request.getRefresh_token());
        
        } catch (ClientWebApplicationException ex) {
            System.out.println(ex.getMessage());

            // Move to auth page
            return ex.getResponse();

        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/userinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo() {
        try {
            return accessProxy.getUserInfo(headers.getHeaderString("Authorization"));

        } catch (ClientWebApplicationException ex) {
            logger.error(ex.getMessage());
            return ex.getResponse();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
