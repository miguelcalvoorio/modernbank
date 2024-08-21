export enum TypeOfData {
    STRING,
    DICTIONARY,
    DATE,
    DATETIME
}

export type BasicDictionary = {
    [key: string]: string
}

export type DataCustomObject = {  // Not used yed
    default: string,
    value: number,
    other: string
}

export type AdvancedDictionary = { // Not used yed
    [key: string]: DataCustomObject
}