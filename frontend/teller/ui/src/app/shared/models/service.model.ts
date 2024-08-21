export interface DataContainer {
    page: Page;
    data: any[];
}
export interface Page {
    page: number;
    size: number;
    total: number;
    pageCount: number;
}

export function initializePage(): Page {
    const emptyPage: Page = {
        page: 0,
        size: 0,
        total: 0,
        pageCount: 0
    }
    return emptyPage;
}
