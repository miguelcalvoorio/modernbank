import { HttpRequest, HttpHandlerFn, HttpEvent, HttpContextToken } from "@angular/common/http";
import { inject } from '@angular/core';
import { Observable, switchMap } from "rxjs";

import { AuthService } from "../../core/auth/auth.service";
import { StorageService } from "./storage.service";

export const IS_PUBLIC = new HttpContextToken(() => false);

export function secureHttpInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    const authService = inject(AuthService);

    console.log(req.url);

    if (req.context.get(IS_PUBLIC)) {
        // Public requests
        return next(req);
    }

    // Non public requests
    if (!authService.isAuthenticated()) {
        // Ask for a new token using the refresh token
        return authService.refreshToken()
            .pipe(
                switchMap(() => {
                    const authRequest = addAuthorizationHeader(req);
                    return next(authRequest);
                })
            );
    } else {
        const authRequest = addAuthorizationHeader(req);
        return next(authRequest);
    }
}

const addAuthorizationHeader = (req: HttpRequest<any>) => {
    const storageService = inject(StorageService);
    const token = storageService.get('access_token');

    return req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
    });
}