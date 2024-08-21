package org.modernbank.backend.party.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.modernbank.backend.party.model.PartyContact;
import org.modernbank.backend.party.model.type.TypePartyRole;
import org.modernbank.backend.party.utilities.model.AuditModel;
import org.modernbank.backend.party.utilities.model.type.TypeCountry;
import org.modernbank.backend.party.utilities.model.type.TypeGender;
import org.modernbank.backend.party.utilities.model.type.TypeStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;;

@Getter
@Setter
@Schema(
    name = "Party",
    description = "Party schema")
@JsonInclude(Include.NON_NULL)
public class PartyRest {
    private String id;
    private String uuid;
    private String name;
    private String lastName;
    private TypeGender gender;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private TypeCountry nationality;
    private TypeStatus status;
    private List<TypePartyRole> roles;
    private AuditModel createdBy;
    private AuditModel lastUpdatedBy;
    private List<PartyIdentificationRest> identifications;
    private List<PartyAddressRest> addresses;
    private List<PartyContact> contacts;
    
    public String getDateOfBirth() {
        return (this.dateOfBirth == null) ? "" : this.dateOfBirth.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String toString() {
        return "Party {" +
            "id: '" + this.id + "', " +
            "uuid: '" + this.uuid + "', " +
            "name: '" + this.name + "', " +
            "lastName: '" + this.lastName + "'}";
    }
}
