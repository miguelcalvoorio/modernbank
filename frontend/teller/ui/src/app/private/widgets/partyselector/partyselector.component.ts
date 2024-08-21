import { Component, inject } from '@angular/core';

import { PartySelectorService } from './partyselector.service';
import { Party } from '../../modules/parties/party.models';

import { BasicDictionary } from '../../../shared/models';
import { dataCountryDictionary, dataGenderDictionary, dataRolesDictionary, dataStatusDictionary } from '../../data';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'widget-party-selector',
  standalone: false,
  templateUrl: './partyselector.component.html',
  styleUrl: './partyselector.component.scss'
})
export class PartySelectorComponent {
  // Object injection
  private partySelectorService: PartySelectorService = inject(PartySelectorService);
  private datePipe: DatePipe = inject(DatePipe);
  private router = inject(Router);

  // Rest of attributes
  public dataCountryDictionary: BasicDictionary = dataCountryDictionary;
  public dataGenderDictionary: BasicDictionary = dataGenderDictionary;
  public dataRolesDictionary: BasicDictionary = dataRolesDictionary;
  public dataStatusDictionary: BasicDictionary = dataStatusDictionary

  public partiesList: Party[] = [];

  ngOnInit() {
    this.partySelectorService.partyDataEmmited.subscribe(party => {
      // Check if party already exists
      const partyIndex: number = this.partiesList.indexOf(party);

      if (partyIndex >= 0) {
        // Party already exists, let's select it
        const partyItem = this.partiesList[partyIndex];

        // Remove party
        this.partiesList.splice(partyIndex, 1);
        
        // Add party to the top
        this.partiesList.unshift(partyItem);

      } else {
        // New party to the list
        this.partiesList.unshift(party);
      }
    });
  }

  public selectParty(party: Party) {
    this.partySelectorService.addParty(party);

    // Move to party file
    this.router.navigate(['private/parties/partyfile'], { queryParams: {id: party.uuid}});
  }

  public removeParty(partyIndex: number) {
    if (partyIndex === 0) {
      // Party selected
    }

    this.partiesList.splice(partyIndex, 1);
  }
}
