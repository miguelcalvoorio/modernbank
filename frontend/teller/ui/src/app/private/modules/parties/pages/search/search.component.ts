import { Component, DestroyRef, inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { catchError, of, tap } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

import { format } from 'date-fns'

import { PartyService } from '../../party.service';
import { Party, PartyContainer, ResponseError, RequestPartySearch } from '../../party.models';
import { DataGridConfiguration } from '../../../../../shared/components/datagrid/datagrid.component';
import { dataCountryDictionary, dataGenderDictionary, dataRolesDictionary, dataStatusDictionary } from '../../../../data';
import { BasicDictionary, TypeOfData } from '../../../../../shared/models';
import { PartySelectorService } from '../../../../widgets/partyselector/partyselector.service';

@Component({
  selector: 'page-search',
  standalone: false,
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {
  // Object injection
  private partyService = inject(PartyService);
  private partySelectorService: PartySelectorService = inject(PartySelectorService);

  // Rest of attributes
  private savedSearch?: RequestPartySearch;
  private destroyRef: DestroyRef = inject(DestroyRef);

  public partyContainer?: PartyContainer; 
  public dataGridConfiguration: DataGridConfiguration;

  public dataCountryDictionary: BasicDictionary = dataCountryDictionary;
  public dataStatusDictionary: BasicDictionary = dataStatusDictionary;
  public dataGenderDictionary: BasicDictionary = dataGenderDictionary;

  constructor() {
    this.dataGridConfiguration = this.configDataGrid();
  }

  // Form definition
  partySearchForm!: FormGroup;
  MAX_FIRSTNAME_LENGHT = 20;
  MAX_LASTNAME_LENGHT = 20;

  ngOnInit(): void {
    // Form configuration
    this.partySearchForm = new FormGroup({
      firstName: new FormControl('', [
        Validators.maxLength(this.MAX_FIRSTNAME_LENGHT)
      ]),
      lastName: new FormControl('', [
        Validators.maxLength(this.MAX_LASTNAME_LENGHT)
      ]),
      dateOfBirth: new FormControl(null, []),
      status: new FormControl('', []),
      email: new FormControl('', [
        Validators.maxLength(50)
      ]),
      documentId: new FormControl('', []),
      nationality: new FormControl('', []),
    });
  }

  get firstName() {
    return this.partySearchForm.get('firstName');
  }

  get lastName() {
    return this.partySearchForm.get('lastName');
  }

  get dateOfBirth() {
    return this.partySearchForm.get('dateOfBirth');
  }

  get status() {
    return this.partySearchForm.get('status');
  }

  get email() {
    return this.partySearchForm.get('email');
  }

  get documentId() {
    return this.partySearchForm.get('documentId');
  }

  get nationality() {
    return this.partySearchForm.get('nationality');
  }

  private configDataGrid(): DataGridConfiguration {
    var dataGridConfiguration: DataGridConfiguration = new DataGridConfiguration();
    dataGridConfiguration.addColumnConfiguration("name", "First name");
    dataGridConfiguration.addColumnConfiguration("lastName", "Last name");
    dataGridConfiguration.addColumnConfiguration("gender", "Gender", TypeOfData.DICTIONARY, dataGenderDictionary);
    dataGridConfiguration.addColumnConfiguration("dateOfBirth", "Date of birth", TypeOfData.DATE);
    dataGridConfiguration.addColumnConfiguration("placeOfBirth", "Place of birth");
    dataGridConfiguration.addColumnConfiguration("nationality", "Nationality", TypeOfData.DICTIONARY, dataCountryDictionary);
    dataGridConfiguration.addColumnConfiguration("status", "Status", TypeOfData.DICTIONARY, dataStatusDictionary);

    return dataGridConfiguration;
  }

  public submitPartySearchForm(): void {
    if (this.partySearchForm.valid) {
      const date = this.partySearchForm.value.dateOfBirth

      // Save search criteria for future pagination purposes
      this.savedSearch = {
        name: this.partySearchForm.value.firstName ?? '',
        lastName: this.partySearchForm.value.lastName ?? '',
        email: this.partySearchForm.value.email ?? '',
        dateOfBirth: date ? format(new Date(date.year, date.month - 1, date.day), 'yyyy-MM-dd') : '',
        nationality: this.partySearchForm.value.nationality ?? '',
        status: this.partySearchForm.value.status ?? '',
        documentId: this.partySearchForm.value.documentId ?? ''
      };

      this.serviceSearch(this.savedSearch, 0);

    } else {
      // Mark all controls as touched to ensure showing all validation errors
      Object.keys(this.partySearchForm.controls).forEach(field => { 
        const control = this.partySearchForm.get(field);
        control?.markAsTouched({ onlySelf: true });
      });
    }
  }

  private serviceSearch(search: RequestPartySearch, page: number): void {
    // Reset previous response if any
    this.partyContainer = undefined;

    this.partyService.searchParties(search, page)
      .pipe(
        catchError(error => {
          const responseError = error as ResponseError;
          console.log("ERRORR");

          return of();
        }),
        takeUntilDestroyed(this.destroyRef),
        tap(data => {
          if (data) {
            this.partyContainer = data as PartyContainer;
          } 
        })
      )
      .subscribe();
  }

  public requestPage(page: number): void {
    if (this.savedSearch) this.serviceSearch(this.savedSearch, page);
  }

  public addParty(party: Party): void {
    this.partySelectorService.addParty(party);
  }

  public checkPartyType(data: Party): Party {
    return data;
  }
}
