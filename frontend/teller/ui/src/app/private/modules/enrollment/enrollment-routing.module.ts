import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CatalogComponent, SavingsComponent, CardsComponent, FinancingComponent, InvestmentsComponent } from './pages';
import { PageNotFoundComponent } from '../../../shared/pages/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: 'catalog', component: CatalogComponent },
  { path: 'savings',   component: SavingsComponent },
  { path: 'cards',   component: CardsComponent },
  { path: 'financing',   component: FinancingComponent },
  { path: 'investments',   component: InvestmentsComponent },
  { path: '', redirectTo: 'catalog', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnrollmentRoutingModule { }