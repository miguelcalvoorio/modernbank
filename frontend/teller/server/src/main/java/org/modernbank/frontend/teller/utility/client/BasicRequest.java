package org.modernbank.frontend.teller.utility.client;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicRequest {
    @QueryParam("page")
    @Parameter(
        required = false,
        example = "0",
        description = "Page number, starting pagination with index 0, ending at total pages minus one")
    @DefaultValue("0")
    @PositiveOrZero
    private int page;

    @QueryParam("size")
    @Parameter(
        required = false,
        example = "10",
        description = "Page size; max number of items to be included on each page")
    @DefaultValue("10")
    @Positive
    private int size;

    @QueryParam("detail")
    @Parameter(
        required = false,
        example = "true",
        description = "If true, provides maximum entity detail on response")
    @DefaultValue("false")
    private boolean detail;

    @QueryParam("sortBy")
    @Parameter(
        required = false,
        example = "lastName",
        description = "Field on which to sort from the output model. Field name can a hyphen '-' prefix to sort in descending order.")
    @DefaultValue("")
    private String sortBy;

    public String toString() {
        return "PartySearchRequest {" +
        "page: '" + this.page + "', " +
        "size: '" + this.size + "', " +
        "detail: '" + this.detail + "', " +
        "sortBy: '" + this.sortBy + "'}";
    }
}
