import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Subscription, catchError, of, tap } from 'rxjs';

import { AuthService } from '../../../core/auth/auth.service';
import { ResponseError } from '../../../core/auth/auth.models';
import { User } from '../../../core/auth/auth.models';

import { PRIVATE_MODULE_CONFIG } from '../../private.config';
import { PartySelectorService } from '../partyselector/partyselector.service';

import * as menuConfig from '../../menu.json';
import { Party } from '../../modules/parties/party.models';

@Component({
  selector: 'widget-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit, OnDestroy {
  private partySelectorService: PartySelectorService = inject(PartySelectorService);
  private router: Router = inject(Router);
  private authService: AuthService = inject(AuthService);

  private config = inject(PRIVATE_MODULE_CONFIG);

  private basePath = this.config.privateUrlPath;

  public currentPath: string = '';

  public scenarios: any[] = menuConfig.items;
  public scenarioSelected: any = null;
  public itemSelected: any = null;
  public party: Party | undefined;

  private urlEventSubscription!: Subscription;

  public currentUser?: User;

  ngOnInit(): void {
    // Load current user
    this.authService.getUser()
      .pipe(
        catchError((error) => {
          const signInError = error as ResponseError;
          console.log("Status: " + signInError.code + " [" + signInError.message + "]")
          return of();
        }),
        tap(data => {
          this.currentUser = data;
        })
      ).subscribe();

    // Default scenario and operation
    this.scenarioSelected = this.scenarios[0];
    this.itemSelected = this.scenarioSelected.items[0];

    // Find scenario selected the first time module starts
    this.selectSecenario(this.router.url);

    // Listen to changes on URL for updating selected scenario
    this.urlEventSubscription = this.router.events.subscribe(e => {
      if (e instanceof NavigationEnd) {
        this.selectSecenario(e.url);
      }
    });

    // Load party selection
    this.partySelectorService.partyDataEmmited.subscribe(party => {
      this.party = party;
    });
  }

  ngOnDestroy(): void {
    // prevent memory leak when component destroyed
    if (this.urlEventSubscription) {
      this.urlEventSubscription.unsubscribe();
    }
  }

  selectSecenario(url: string): void {

    if (url.indexOf(this.basePath) == 1) {
      // URL is correct; get scenario path
      var menuPaths:string[] = url.substring(this.basePath.length + 2, url.length).split('/');

      this.scenarios.forEach(scenario => {
        if (scenario.link == menuPaths[0]) {
          // Selected scenario found
          this.scenarioSelected = scenario;

          // Select default item
          this.itemSelected = scenario.items[0];

          scenario.items.forEach((item: { link: string; }) => {
            if (item.link == menuPaths[1]) {
              // Selected scenario item found
              this.itemSelected = item;
            }
          });
        }
      });
    }
  }

  clickSignOut(): void {
    this.authService.signOut();
  }
}
