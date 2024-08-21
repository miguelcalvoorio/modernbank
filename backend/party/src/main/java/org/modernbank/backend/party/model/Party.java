package org.modernbank.backend.party.model;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.modernbank.backend.party.model.type.TypePartyRole;
import org.modernbank.backend.party.utilities.model.AuditModel;
import org.modernbank.backend.party.utilities.model.type.TypeCountry;
import org.modernbank.backend.party.utilities.model.type.TypeGender;
import org.modernbank.backend.party.utilities.model.type.TypeStatus;

import io.quarkus.mongodb.panache.common.MongoEntity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "parties", database = "modernbank")
public class Party {
    private ObjectId id; // used by MongoDB for the _id field

    @NotNull
    @Size(min = 36, max = 36)
    private String uuid;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    private String lastName;

    private TypeGender gender;

    private LocalDate dateOfBirth;

    private String placeOfBirth;

    private TypeCountry nationality;

    private TypeStatus status;

    private List<TypePartyRole> roles;

    private AuditModel createdBy;

    private AuditModel lastUpdatedBy;

    private List<PartyIdentification> identifications;

    private List<PartyAddress> addresses;

    private List<PartyContact> contacts;

    @Override
    public String toString() {
        return "Party {" +
            "id: '" + this.id + "'," +
            "uuid: '" + this.uuid + "'," +
            "name: '" + this.name + "'," +
            "lastName: '" + this.lastName + "'}";
    }
}
