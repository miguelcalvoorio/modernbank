import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';

import { PRIVATE_MODULE_CONFIG, DEFAULT_PRIVATE_MODULE_CONFIG } from './private/private.config';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { secureHttpInterceptor } from './shared/services/interceptor.http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(
      withFetch(),
      withInterceptors([secureHttpInterceptor])
    ),
    { provide: PRIVATE_MODULE_CONFIG, useValue: DEFAULT_PRIVATE_MODULE_CONFIG }
  ]
};
