import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PartyRoutingModule } from './parties-routing.module';
import { OnboardingComponent, PartyfileComponent } from './pages';

@NgModule({
  declarations: [
    PartyfileComponent,
    OnboardingComponent
  ],
  imports: [
    CommonModule,
    PartyRoutingModule
  ]
})
export class PartiesModule { }
