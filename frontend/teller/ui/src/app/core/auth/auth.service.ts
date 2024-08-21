import { HttpClient, HttpContext, HttpHeaders } from '@angular/common/http';
import { DestroyRef, Injectable, inject } from '@angular/core';
import { Router } from '@angular/router';

import { Observable, catchError, retry, tap, of, timer} from 'rxjs';

import { environment } from '../../../environments/environment';

import { IS_PUBLIC } from '../../shared/services/interceptor.http';
import { AuthTokenRequest, User, SignInResponse, SignInSuccess, ResponseError } from './auth.models';
import { StorageService } from '../../shared/services/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseProxyAccessUrl: string;

  // We will refresh the access token 60 seconds before current token expires
  private readonly TOKEN_EXPIRY_THRESHOLD_SECONDS = 60;

  private readonly destroyRef = inject(DestroyRef);

  constructor(
    private http: HttpClient,
    private router: Router,
    private storage: StorageService) {
      this.baseProxyAccessUrl = environment.urlAuthProxyServer;
    }

  isAuthenticated(): boolean {
    const access_token_exp = this.storage.get('access_token_exp');

    if (access_token_exp) {
      const time_to_expire = Number(access_token_exp) - (new Date()).getTime();

      if (time_to_expire > 0) {
        // There's a valid non expired token
        return true;
      }
    }
    return false;
  }

  signIn(username: string, password: string, rememberMe: boolean): Observable<SignInResponse> {
    const url = this.baseProxyAccessUrl + '/access/signin';
    const context = {
      context: new HttpContext().set(IS_PUBLIC, true),
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    } 
    const body: AuthTokenRequest = {
      username: username,
      password: password
    }

    return this.http.post<SignInResponse>(url, body, context)
      .pipe(
        retry({
          count: 2,
          delay: (error) => {
            if (error.status === 401) {
              // Don't retry and throw error to next 'catchError' step
              throw error;
            }
            return timer(500);
          }
        }),
        catchError(error => {
          // Propagate error to caller method for form handling
          const signInError: ResponseError = {
            code: error.status,
            message: error.message
          };
          throw signInError;
        }),
        tap(data => {
          const signInSuccessData = data as SignInSuccess;

          // Save Access & Refresh Token
          this.storeTokens(signInSuccessData);
          
          // Navigate to /private route
          this.router.navigate(['/private']);
        })
      );
  }

  signOut(): void {
    // We should invalidate the refresh token
    const url = this.baseProxyAccessUrl + '/access/signout';
    const context = new HttpContext().set(IS_PUBLIC, true);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = {
      refresh_token: this.storage.get('refresh_token')
    }

    this.http.post(url, body, { headers: headers, observe: 'response', context: context})
      .pipe(
        catchError(error => {
          // Propagate error to caller method for form handling
          const signOutError = {
            code: error.status,
            message: error.message
          };
          return of();
        }),
        tap(data => {
          // Remove stored tokens
          this.removeTokens();
    
          // Navigate to /public route
          this.router.navigate(['/public']);
        })
      )
      .subscribe();
  }

  getUser(): Observable<User> {
    const url = this.baseProxyAccessUrl + '/access/userinfo';
    const token = this.getAccessToken();
    const headers = new HttpHeaders().set('Content-Type', 'application/json')
      
    return this.http.get<User>(url, { headers: headers })
      .pipe(
        catchError(error => {
          // Propagate error to caller method for form handling
          const userError: ResponseError = {
            code: error.status,
            message: error.message
          };
          throw userError;
        }),
        tap(data => {
          const userData = data as User;
        })
      );
  }

  refreshToken(): Observable<any> {
    const refresh_token_exp = this.storage.get('refresh_token_exp');

    console.log("Request new token");

    if (refresh_token_exp === null) {
      // There's no auth information locally stored
      console.log("There's no auth information locally stored");

      // Remove all tokens
      this.removeTokens();

      return of();
    } else {
      const time_to_expire = Number(refresh_token_exp) - (new Date()).getTime();

      if (time_to_expire < 0) {
        // Refresh token has expired refresh token
        console.log("Session expired");

        // Remove all tokens
        this.removeTokens();

        return of();
      }
    }

    // Request new tokens
    const url = this.baseProxyAccessUrl + '/access/refresh-access';
    const context = {
      context: new HttpContext().set(IS_PUBLIC, true),
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    }
    const body = {
      refresh_token: this.getRefreshToken()
    }

    return this.http.post<SignInResponse>(url, body, context)
      .pipe(
        catchError(error => {
          // Propagate error to caller method for form handling
          const signInError: ResponseError = {
            code: error.status,
            message: error.message
          };

          // Remove all tokens
          this.removeTokens();
          return of();
        }),
        tap(data => {
          const signInSuccessData = data as SignInSuccess;

          // Save Access & Refresh Token
          this.storeTokens(signInSuccessData);
        })
      );
  }

  private storeTokens(data: SignInSuccess): void {
    const access_token_exp: Date = new Date(Date.now() + data.expires_in * 1000);
    const refresh_token_exp: Date = new Date(Date.now() + data.refresh_expires_in * 1000);

    this.storage.set('access_token', data.access_token);
    this.storage.set('refresh_token', data.refresh_token);
    this.storage.set('access_token_exp', access_token_exp.getTime().toString());
    this.storage.set('refresh_token_exp', refresh_token_exp.getTime().toString());
  }

  private removeTokens(): void {
    this.storage.rem('access_token');
    this.storage.rem('refresh_token');
    this.storage.rem('access_token_exp');
    this.storage.rem('refresh_token_exp');
  }

  private getAccessToken(): string | null {
    return this.storage.get('access_token');
  }

  private getRefreshToken(): string | null {
    return this.storage.get('refresh_token');
  }
}
