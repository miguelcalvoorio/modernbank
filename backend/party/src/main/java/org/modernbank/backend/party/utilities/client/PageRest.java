package org.modernbank.backend.party.utilities.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;;

@Getter
@Setter
@Schema(
    name = "Page",
    description = "Page information")
@JsonInclude(Include.NON_NULL)
public class PageRest {
    private int page;
    private int size;
    private long total;
    private long pageCount;
}
