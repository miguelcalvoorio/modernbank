package org.modernbank.frontend.teller.client.party;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.modernbank.frontend.teller.utility.client.PageContainerRest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Schema(
    name = "Page container for parties",
    description = "Container for list of parties, with page information")
@JsonInclude(Include.NON_NULL)
public class PartyRestContainer extends PageContainerRest<List<PartyRest>>{}