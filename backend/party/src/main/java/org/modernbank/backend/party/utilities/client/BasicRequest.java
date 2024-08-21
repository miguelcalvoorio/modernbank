package org.modernbank.backend.party.utilities.client;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicRequest {
    @QueryParam("detail")
    @Parameter(
        required = false,
        example = "true",
        description = "If true, provides maximum entity detail on response")
    @DefaultValue("false")
    private boolean detail;
}
