import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PartyfileComponent, OnboardingComponent } from './pages';
import { PageNotFoundComponent } from '../../../shared/pages/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: 'partyfile', component: PartyfileComponent },
  { path: 'onboarding',   component: OnboardingComponent },
  { path: '', redirectTo: 'partyfile', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartyRoutingModule { }