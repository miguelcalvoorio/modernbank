package org.modernbank.backend.party.model.type;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Schema(
    name = "Type of contact",
    description = "Type of contact supported")
@JsonInclude(Include.NON_NULL)
public enum TypePartyRole {
    DEFAULT ("By default"),
    NO_CLIENT ("No client"),
    CLIENT ("Client"),
    AUTHORIZED_REPRESENTATIVE ("Authorized representative"),
    BENEFICIARY ("Beneficiary");

    private String description;

    TypePartyRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
