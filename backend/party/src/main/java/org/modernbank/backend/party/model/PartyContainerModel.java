package org.modernbank.backend.party.model;

import java.util.List;

import org.modernbank.backend.party.utilities.model.PageContainerModel;
import org.modernbank.backend.party.utilities.model.PageModel;

public class PartyContainerModel extends PageContainerModel<List<Party>> {
    public PartyContainerModel(PageModel page, List<Party> data) {
        super(page, data);
    }
}
