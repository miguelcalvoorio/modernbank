
package org.modernbank.backend.party.client;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.modernbank.backend.party.utilities.client.PageContainerRest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@Schema(
    name = "Page container for parties",
    description = "Container for list of parties, with page information")
@JsonInclude(Include.NON_NULL)
public class PartyContainerRest extends PageContainerRest<List<PartyRest>>{}
