package org.modernbank.frontend.teller.client.party;

import java.time.LocalDate;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.modernbank.frontend.teller.utility.model.type.TypeCountry;
import org.modernbank.frontend.teller.utility.model.type.TypeDocument;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Identification",
    description = "Identification documents schema")
@JsonInclude(Include.NON_NULL)
public class PartyRestIdentification {
    private TypeDocument type;
    private String documentId;
    private TypeCountry issuer;
    private LocalDate issueDate;
    private LocalDate expirationDate;

    public String getIssueDate() {
        return (this.issueDate == null) ? "" : this.issueDate.toString();
    }

    public String getExpirationDate() {
        return (this.expirationDate == null) ? "" : this.expirationDate.toString();
    }
}
