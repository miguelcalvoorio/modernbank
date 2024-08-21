package org.modernbank.frontend.teller.client.party;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.modernbank.frontend.teller.client.party.type.TypePartyRole;
import org.modernbank.frontend.teller.utility.model.type.TypeCountry;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Address",
    description = "Party address schema")
@JsonInclude(Include.NON_NULL)
public class PartyRestAddress {
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private TypeCountry country;
    private TypePartyRole[] roles;
}