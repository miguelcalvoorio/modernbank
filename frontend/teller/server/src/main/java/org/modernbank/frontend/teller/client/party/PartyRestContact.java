package org.modernbank.frontend.teller.client.party;

import org.modernbank.frontend.teller.client.party.type.TypePartyRole;
import org.modernbank.frontend.teller.utility.model.type.TypeContact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyRestContact {
    public Boolean preferred;
    public TypeContact type;
    public String contact;
    private TypePartyRole[] roles;
}
