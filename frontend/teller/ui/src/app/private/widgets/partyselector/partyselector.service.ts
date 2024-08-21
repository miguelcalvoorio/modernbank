import { Injectable } from "@angular/core";
import { Party } from "../../modules/parties/party.models";
import { Subject } from "rxjs";


@Injectable({
    providedIn: 'root'
})
export class PartySelectorService {
    private partyMessageBus = new Subject<Party>();

    public partyDataEmmited = this.partyMessageBus.asObservable()

    public addParty(party: Party): void {
        this.partyMessageBus.next(party);
    }
}