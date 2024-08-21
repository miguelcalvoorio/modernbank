import { DataContainer, Page } from "../../../shared/models/service.model";

export interface ResponseError {
    code: number;
    message: string;
}

export interface Party {
    uuid: string;
    name: string;
    lastName: string;
    gender: string;
    dateOfBirth: string;
    placeOfBirth: string;
    nationality: string;
    status: string;
    roles: string[];
    createdBy: Audit;
    lastUpdatedBy: Audit;
    identifications: Identification[];
    addresses: Address[];
    contacts: Contact[];
}

export interface Identification {
    uuid: string;
    type: string;
    documentId: string;
    issuer: string;
    issueDate: string;
    expirationDate: string;
}

export interface Address {
    uuid: string;
    address: string;
    city: string;
    state: string;
    postalCode: string;
    country: string;
    roles: string[];
}

export interface Contact {
    uuid: string;
    preferred: boolean;
    type: string;
    contact: string;
    roles: string[];
}

export interface Audit {
    uuid: string;
    fullName: string;
    timestamp: string;
}

export interface PartyContainer extends DataContainer {
    page: Page;
    data: Party[];
}

export interface RequestPartySearch {
    name: string | undefined;
    lastName: string | undefined;
    email: string | undefined;
    dateOfBirth: string | undefined;
    nationality: string | undefined;
    status: string | undefined;
    documentId: string | undefined;
}