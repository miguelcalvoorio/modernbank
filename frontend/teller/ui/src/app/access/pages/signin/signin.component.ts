import { Component, inject } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { AccessService } from '../../access.service';

@Component({
  selector: 'page-signin',
  standalone: false,
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.scss'
})
export class SigninComponent {
  accessService: AccessService = inject(AccessService);

  signInForm = new FormGroup({
    user: new FormControl(''),
    password: new FormControl(''),
    rememberMe: new FormControl(false)
  });

  submitSignInForm() {
    this.serviceCheckCredentials(
      this.signInForm.value.user ?? '',
      this.signInForm.value.password ?? '',
      this.signInForm.value.rememberMe ?? false
    );
  }

  serviceCheckCredentials(user: string, password: string, rememberMe: boolean) {
    this.accessService.getAccessToken(user, password, rememberMe);
  }
}
