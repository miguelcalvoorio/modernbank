import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EnrollmentRoutingModule } from './enrollment-routing.module';
import { CardsComponent, CatalogComponent, FinancingComponent, InvestmentsComponent, SavingsComponent } from './pages';



@NgModule({
  declarations: [
    CatalogComponent,
    SavingsComponent,
    CardsComponent,
    FinancingComponent,
    InvestmentsComponent
  ],
  imports: [
    CommonModule,
    EnrollmentRoutingModule
  ]
})
export class EnrollmentModule { }
