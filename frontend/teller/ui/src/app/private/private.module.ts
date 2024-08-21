import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { PrivateRoutingModule } from './private-routing.module';
import { PrivateComponent } from './private.component';
import { HeaderComponent, StatusComponent } from './widgets';
import { PartySelectorComponent } from "./widgets/partyselector/partyselector.component";
import { GetDescriptionPipe } from '../shared/pipes/getdescription.pipe';

@NgModule({
  declarations: [
    PrivateComponent,
    HeaderComponent,
    StatusComponent,
    PartySelectorComponent
  ],
  imports: [
    CommonModule,
    PrivateRoutingModule,
    GetDescriptionPipe
],
  providers: [
    DatePipe
  ]
})
export class PrivateModule {}
