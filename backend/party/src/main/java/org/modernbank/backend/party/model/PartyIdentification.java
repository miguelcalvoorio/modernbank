package org.modernbank.backend.party.model;

import java.time.LocalDate;

import org.modernbank.backend.party.utilities.model.type.TypeCountry;
import org.modernbank.backend.party.utilities.model.type.TypeDocument;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyIdentification {
    private TypeDocument type;
    private String documentId;
    private TypeCountry issuer;
    private LocalDate issueDate;
    private LocalDate expirationDate;
}
