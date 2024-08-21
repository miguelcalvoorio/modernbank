package org.modernbank.backend.party.utilities.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageModel {
    private int page;
    private int size;
    private long total;
    private int pageCount;

    public PageModel() {}

    public PageModel(long total, int pageCount) {
        this.total = total;
        this.pageCount = pageCount;
    }
}
