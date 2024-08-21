package org.modernbank.frontend.teller.client.party;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.modernbank.frontend.teller.client.party.type.TypePartyRole;
import org.modernbank.frontend.teller.utility.model.AuditModel;
import org.modernbank.frontend.teller.utility.model.type.TypeCountry;
import org.modernbank.frontend.teller.utility.model.type.TypeGender;
import org.modernbank.frontend.teller.utility.model.type.TypeStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Party",
    description = "Party schema")
@JsonInclude(Include.NON_NULL)
public class PartyRest {
    private String id;
    private String guid;
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
    private List<PartyRestIdentification> identifications;
    private List<PartyRestAddress> addresses;
    private List<PartyRestContact> contacts;

    public String getDateOfBirth() {
        return (this.dateOfBirth == null) ?  "" : this.dateOfBirth.toString();
    }

    @Override
    public String toString() {
        return "Party {" +
            "id: '" + this.id + "', " +
            "guid: '" + this.guid + "', " +
            "name: '" + this.name + "', " +
            "lastName: '" + this.lastName + "'}";
    }
}
