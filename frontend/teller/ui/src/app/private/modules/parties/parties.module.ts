import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { PartyRoutingModule } from './parties-routing.module';
import { OnboardingComponent, PartyfileComponent, SearchComponent } from './pages';
import { ReactiveFormsModule } from '@angular/forms';
import { PaginationComponent } from "../../../shared/components/pagination/pagination.component";
import { DatagridComponent } from "../../../shared/components/datagrid/datagrid.component";
import { GetDescriptionPipe } from '../../../shared/pipes/getdescription.pipe';

@NgModule({
  declarations: [
    PartyfileComponent,
    OnboardingComponent,
    SearchComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    PartyRoutingModule,
    NgbDatepickerModule,
    PaginationComponent,
    DatagridComponent,
    GetDescriptionPipe
],
providers: [
  DatePipe
]
})
export class PartiesModule { }
