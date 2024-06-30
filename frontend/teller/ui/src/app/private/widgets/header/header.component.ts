import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';

import { PRIVATE_MODULE_CONFIG } from '../../private.config';

import * as menuConfig from '../../menu.json';

@Component({
  selector: 'widget-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit, OnDestroy {
  config = inject(PRIVATE_MODULE_CONFIG);
  basePath = this.config.privateUrlPath;

  currentPath: string = '';

  scenarios: any[] = menuConfig.items;
  scenarioSelected: any = null;
  itemSelected: any = null;

  urlEventSubscription!: Subscription;

  constructor(private router: Router) {}

  ngOnInit(): void {
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
  }

  ngOnDestroy(): void {
    // prevent memory leak when component destroyed
    this.urlEventSubscription.unsubscribe();
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
    console.log('Sign out');
  }
}
