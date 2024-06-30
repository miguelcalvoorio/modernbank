import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrivateRoutingModule } from './private-routing.module';
import { PrivateComponent } from './private.component';
import { FooterComponent, HeaderComponent } from './widgets';

@NgModule({
  declarations: [
    PrivateComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    PrivateRoutingModule
  ],
  providers: [
    
  ]
})
export class PrivateModule {}
