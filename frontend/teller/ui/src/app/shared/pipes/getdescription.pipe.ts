import { Pipe, PipeTransform } from "@angular/core";

import { BasicDictionary } from "../models";

@Pipe({
    standalone: true,
    name: 'getdescription',
    pure: true
})
export class GetDescriptionPipe implements PipeTransform {
    transform(value: string, dictionary: BasicDictionary): string {
        return dictionary[value];
    }
}