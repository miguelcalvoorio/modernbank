import { Component, inject, Inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { BehaviorSubject } from 'rxjs';
import { PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { AuthService } from './core/auth/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  static isBrowser = new BehaviorSubject<boolean>(false);

  title = 'teller';

  private authService = inject(AuthService);

  constructor(@Inject(PLATFORM_ID) private platformId: any) {
    AppComponent.isBrowser.next(isPlatformBrowser(platformId));
  }

  ngOnInit() {
    // Retrieve user credentials if they are stored in localStorage
    this.authService.refreshToken().subscribe();
  }
}
