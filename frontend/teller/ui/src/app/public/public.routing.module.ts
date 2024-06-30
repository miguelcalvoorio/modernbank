import { NgModule } from "@angular/core";
import { Routes, RouterModule} from '@angular/router';

import { PublicComponent } from "./public.component";
import { WelcomeComponent } from "./pages";

const routes: Routes = [
    { path: '', component: PublicComponent, children: [
        { path: '', component: WelcomeComponent},
        { path: 'welcome', component: WelcomeComponent}
    ] }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PublicRoutingModule {}
