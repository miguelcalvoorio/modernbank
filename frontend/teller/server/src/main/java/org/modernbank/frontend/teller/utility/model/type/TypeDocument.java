package org.modernbank.frontend.teller.utility.model.type;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@Schema(
    name = "Type of identification document",
    description = "Type of identification document supported")
@JsonInclude(Include.NON_NULL)
public enum TypeDocument {
    PASSPORT ("Passport"),
    SOCIAL_SECURITY ("Social Security"),
    NATIONAL_ID ("National Id");

    private String description;

    TypeDocument(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}