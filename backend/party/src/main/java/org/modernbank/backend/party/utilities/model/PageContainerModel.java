package org.modernbank.backend.party.utilities.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageContainerModel<T> {
    private PageModel page;
    private T data;

    public PageContainerModel(PageModel page, T data) {
        this.page = page;
        this.data = data;
    }
}
