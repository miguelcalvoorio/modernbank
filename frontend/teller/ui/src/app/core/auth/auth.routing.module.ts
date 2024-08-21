import { NgModule } from "@angular/core";
import { Routes, RouterModule} from '@angular/router';

import { AuthComponent } from "./auth.component";
import { SigninComponent, SignupComponent } from "./pages";

const routes: Routes = [
    { path: '', component: AuthComponent, children: [
        { path: '', component: SigninComponent},
        { path: 'signin', component: SigninComponent},
        { path: 'signup', component: SignupComponent}
    ] }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserRoutingModule {}