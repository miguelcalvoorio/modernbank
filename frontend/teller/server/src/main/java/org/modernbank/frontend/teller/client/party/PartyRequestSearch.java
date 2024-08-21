package org.modernbank.frontend.teller.client.party;

import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.modernbank.frontend.teller.utility.model.type.TypeCountry;
import org.modernbank.frontend.teller.utility.model.type.TypeStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Party search criteria",
    description = "Allowed criteria for party look up")
@JsonInclude(Include.NON_NULL)
public class PartyRequestSearch {
    @Schema(
        required = false,
        example = "John",
        description = "Performs a non-case-sensitive match on the party's name. Matching is partial on the beginning of the name")
    @Size(min = 0, max = 40)
    private String name = null;
    
    @Schema(
        required = false,
        example = "Smith",
        description = "Performs a non-case-sensitive match on the party's last name. Matching is partial on the beginning of the last name")
    @Size(min = 0, max = 40)
    private String lastName = null;

    @Schema(
        required = false,
        example = "john.smith@example.com",
        description = "Performs non-case-sensitive and exact match on the party's email address")
    @Size(min = 0, max = 255)
    private String email = null;
    
    private Date dateOfBirth;
    private TypeCountry nationality;
    private TypeStatus status;
    
    @Schema(
        required = false,
        example = "1234567890E",
        description = "Performs non-case-sensitive and exact match on the party's identification document")
    @Size(min = 0, max = 40)
    private String documentId = null;

    public String toString() {
        return "Party {" +
            "name: '" + this.name + "', " +
            "lastName: '" + this.lastName + "', " +
            "email: '" + this.email + "', " +
            "documentId: '" + this.documentId + "'}";
    }
}

