import { Component, inject, DestroyRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { catchError, of } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

import { AuthService } from '../../auth.service';
import { ResponseError } from '../../auth.models';


@Component({
  selector: 'page-signin',
  standalone: false,
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.scss'
})
export class SigninComponent {
  authService: AuthService = inject(AuthService);
  destroyRef: DestroyRef = inject(DestroyRef);

  // Form definition
  signInForm!: FormGroup;
  MIN_USERNAME_LENGHT = 5;
  formError: string = '';

  ngOnInit(): void {
    this.signInForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(this.MIN_USERNAME_LENGHT)
      ]),
      password: new FormControl('', [
        Validators.required
      ]),
      rememberMe: new FormControl(false)
    });
  }

  get username() {
    return this.signInForm.get('username');
  }

  get password() {
    return this.signInForm.get('password');
  }

  submitSignInForm() {
    if (this.signInForm.valid) {
      this.serviceSignIn(
        this.signInForm.value.username ?? '',
        this.signInForm.value.password ?? '',
        this.signInForm.value.rememberMe ?? false
      );
    } else {
      // Mark all controls as touched to ensure showing all validation errors
      Object.keys(this.signInForm.controls).forEach(field => { 
        const control = this.signInForm.get(field);
        control?.markAsTouched({ onlySelf: true });
      });
    }
  }

  serviceSignIn(username: string, password: string, rememberMe: boolean) {
    this.authService.signIn(username, password, rememberMe)
      .pipe(
        catchError((error) => {
          const signInError = error as ResponseError;

          // Show error message
          if (signInError.code === 401) {
            // Unauthorized
            this.formError = "Unauthorized. Please check your credentials";

          } else {
            // Other errors
            this.formError = "Unexpected error. Try it again. If error persists, please contact technical service";
          }

          return of();
        }),
        takeUntilDestroyed(this.destroyRef)
      ).subscribe();
  }
}
