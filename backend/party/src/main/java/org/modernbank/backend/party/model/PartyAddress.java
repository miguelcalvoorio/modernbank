package org.modernbank.backend.party.model;

import org.modernbank.backend.party.model.type.TypePartyRole;
import org.modernbank.backend.party.utilities.model.type.TypeCountry;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyAddress {
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private TypeCountry country;
    private List<TypePartyRole> roles;
}
