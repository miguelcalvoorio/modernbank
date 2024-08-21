package org.modernbank.frontend.teller.utility.model.type;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Schema(
    name = "Gender",
    description = "Male or Female biological attribute")
@JsonInclude(Include.NON_NULL)
public enum TypeGender {
    M ("Male"),
    F ("Female");

    private String description;
    
    TypeGender(String descritpion) {
        this.description = descritpion;
    }

    public String description() {
        return description;
    }
}
