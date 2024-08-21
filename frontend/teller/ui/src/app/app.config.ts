import { APP_INITIALIZER, ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';

import { PRIVATE_MODULE_CONFIG, DEFAULT_PRIVATE_MODULE_CONFIG } from './private/private.config';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { secureHttpInterceptor } from './shared/services/interceptor.http';

import { AuthService } from './core/auth/auth.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(
      withFetch(),
      withInterceptors([secureHttpInterceptor])
    ),
    { 
      provide: PRIVATE_MODULE_CONFIG,
      useValue: DEFAULT_PRIVATE_MODULE_CONFIG
    },
    { 
      provide: APP_INITIALIZER,
      useFactory: initializerFactory,
      multi: true,
      deps: [AuthService]
    }
  ]
};


export function initializerFactory(authService: AuthService) {
  // This function is not working as localStorage is still not initialized
  // so user credentials (access and refresh tokens) cannot be accessed yet
  return () => authService.refreshToken().subscribe();
}
