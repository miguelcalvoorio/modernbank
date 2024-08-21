package org.modernbank.backend.party.utilities.client;

public class PageContainerRest<E> {
    private PageRest page;
    private E data;

    public void setPage(PageRest page) {
        this.page = page;
    }

    public PageRest getPage() {
        return this.page;
    }

    public void setData(E data) {
        this.data = data;
    }

    public E getData() {
        return this.data;
    }
}
