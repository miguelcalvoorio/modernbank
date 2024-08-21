import { Routes } from '@angular/router';

import { PRIVATE_MODULE_CONFIG, PrivateModuleConfig } from './private/private.config';
import { authenticatedGuard, avoidAuthenticatedGuard } from './core/auth/auth.guard';

import { PageNotFoundComponent } from './shared/components/page-not-found/page-not-found.component';

const PRIVATE_MODULE_CONFIG_VALUE: PrivateModuleConfig = { privateUrlPath: 'private' } 

export const routes: Routes = [
    {
        path: 'public', 
        loadChildren: () => import('./public/public.module').then(m => m.PublicModule)
    },
    {
        path: 'auth',
        loadChildren: () => import('./core/auth/auth.module').then(m => m.AuthModule),
        canActivate: [avoidAuthenticatedGuard]
    },
    {
        path: 'private',
        loadChildren: () => import('./private/private.module').then(m => m.PrivateModule),
        providers: [{ provide: PRIVATE_MODULE_CONFIG, useValue: PRIVATE_MODULE_CONFIG_VALUE }],
        canActivate: [authenticatedGuard]
    },
    { path: '' , redirectTo: '/public', pathMatch: 'full' },
    { path: '**', component: PageNotFoundComponent}
];
