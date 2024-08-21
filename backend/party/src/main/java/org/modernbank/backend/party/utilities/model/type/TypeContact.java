package org.modernbank.backend.party.utilities.model.type;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Schema(
    name = "Type of contact",
    description = "Type of contact supported")
@JsonInclude(Include.NON_NULL)
public enum TypeContact {
    EMAIL ("Email"),
    MOBILE ("Mobile"),
    PHONE ("Phone");

    private String description;

    TypeContact(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
