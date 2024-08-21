package org.modernbank.backend.party.model;

import org.modernbank.backend.party.model.type.TypePartyRole;
import org.modernbank.backend.party.utilities.model.type.TypeContact;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyContact {
    public Boolean preferred;
    public TypeContact type;
    public String contact;
    private List<TypePartyRole> roles;
}
