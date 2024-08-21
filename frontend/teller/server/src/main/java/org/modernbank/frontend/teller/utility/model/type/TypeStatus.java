package org.modernbank.frontend.teller.utility.model.type;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@Schema(
    name = "Party status",
    description = "Status of a party")
@JsonInclude(Include.NON_NULL)
public enum TypeStatus {
    ACTIVE ("Active"),
    UNVERIFIED ("Unverified"),
    LIMITED ("Limited"),
    INACTIVE ("Inactive"),
    SUSPENDED ("Suspended"),
    CLOSED ("Closed"),;

    private String description;

    TypeStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
