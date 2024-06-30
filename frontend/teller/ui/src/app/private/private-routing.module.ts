import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PrivateComponent } from './private.component';

import { PageNotFoundComponent } from '../shared/pages/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', component: PrivateComponent, children: [
    { path: 'home', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule) },
    { path: 'parties', loadChildren: () => import('./modules/parties/parties.module').then(m => m.PartiesModule) },
    { path: 'enrollment', loadChildren: () => import('./modules/enrollment/enrollment.module').then(m => m.EnrollmentModule) },
    { path: '', redirectTo: 'home', pathMatch: 'full'},
    { path: '**', component: PageNotFoundComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
