package org.modernbank.frontend.teller.client.access;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@RegisterRestClient(configKey = "auth-server")
public interface AccessProxy {

    @GET
    @Path("/.well-known/openid-configuration")
    @Produces(MediaType.APPLICATION_JSON)
    Response getOpenIdConfig();

    @POST
    @Path("/protocol/openid-connect/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response getAccessToken(
        @FormParam("client_id") String client_id,
        @FormParam("client_secret") String client_secret,
        @FormParam("username") String username,
        @FormParam("password") String password,
        @FormParam("grant_type") String grant_type
    );

    @POST
    @Path("/protocol/openid-connect/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response getAccessTokenWithRefreshToken(
        @FormParam("client_id") String client_id,
        @FormParam("client_secret") String client_secret,
        @FormParam("grant_type") String grant_type,
        @FormParam("refresh_token") String refresh_token
    );

    @POST
    @Path("/protocol/openid-connect/logout")
    Response logOut(
        @FormParam("client_id") String client_id,
        @FormParam("client_secret") String client_secret,
        @FormParam("refresh_token") String refresh_token
    );

    @GET
    @Path("/protocol/openid-connect/userinfo")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserInfo(@HeaderParam("Authorization") String authorization);

}
