import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

import { AuthService } from './auth.service';

export const authenticatedGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isAuthenticated()) {
    // Authenticated
    return true; 
  }

  // Not authenticated: move to signin page
  router.navigate(['/auth']);
  return false;
};

export const avoidAuthenticatedGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router)

  if (authService.isAuthenticated()) {
    // Authenticated
    router.navigate(['/private']);
    return false; 
  }

  // Not authenticated
  return true;
}
