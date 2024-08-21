import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnChanges, Output } from '@angular/core';

import { Page } from '../../models/service.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'widget-pagination',
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [CommonModule],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent implements OnChanges {
  @Input('pageObject') pageObject?: Page;
  @Output('goToPageFunction') goToPage: EventEmitter<number> = new EventEmitter();

  public navigator: number[] = [];

  ngOnChanges() {
    this.navigator = this.getPageNavigator();
  }

  private getPageNavigator(): number[] {
    const MAX_NAVIGATOR_SIZE = 3; // number of pages before and after current page
    var navigation: number[] = [];

    if (this.pageObject) {
      // Previous pages
      for (let i = 0; i < Math.min(this.pageObject.page, MAX_NAVIGATOR_SIZE); i++) navigation.push(i);
      // Current page
      navigation.push(this.pageObject.page);
      // Next pages
      for (let i = this.pageObject.page + 1; i < Math.min(this.pageObject.page + MAX_NAVIGATOR_SIZE, this.pageObject.pageCount); i++) navigation.push(i);
    }

    return navigation;
  }
}
