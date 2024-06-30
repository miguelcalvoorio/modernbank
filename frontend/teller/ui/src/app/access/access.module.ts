import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './access.routing.module';
import { AccessComponent } from './access.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SigninComponent, SignupComponent } from './pages';

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    AccessComponent,
    SigninComponent,
    SignupComponent
  ]
})
export class AccessModule { }
