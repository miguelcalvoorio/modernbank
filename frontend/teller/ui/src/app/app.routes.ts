import { Routes } from '@angular/router';
import { environment } from '../environments/environment';

import { PageNotFoundComponent } from './shared/pages/page-not-found/page-not-found.component';
import { PRIVATE_MODULE_CONFIG, PrivateModuleConfig } from './private/private.config';

const PRIVATE_MODULE_CONFIG_VALUE: PrivateModuleConfig = { privateUrlPath: 'private' } 

export const routes: Routes = [
    {
        path: 'public', 
        loadChildren: () => import('./public/public.module').then(m => m.PublicModule)
    },
    {
        path: 'access', 
        loadChildren: () => import('./access/access.module').then(m => m.AccessModule)
    },
    {
        path: 'private',
        loadChildren: () => import('./private/private.module').then(m => m.PrivateModule),
        providers: [{ provide: PRIVATE_MODULE_CONFIG, useValue: PRIVATE_MODULE_CONFIG_VALUE }]
    },
    { path: '' , redirectTo: '/public', pathMatch: 'full' },
    { path: '**', component: PageNotFoundComponent}
];
