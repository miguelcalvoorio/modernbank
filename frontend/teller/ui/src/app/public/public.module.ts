import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PublicComponent } from './public.component';
import { PublicRoutingModule } from './public.routing.module';

import { HeaderComponent, FooterComponent } from './widgets';

@NgModule({
  imports: [
    CommonModule,
    PublicRoutingModule
  ],
  declarations: [
    PublicComponent,
    HeaderComponent,
    FooterComponent
  ]
})
export class PublicModule { }
