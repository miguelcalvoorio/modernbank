import { CommonModule, DatePipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, inject, Input, OnChanges, Output, TemplateRef } from '@angular/core';

import { DataContainer } from '../../models/service.model';
import { PaginationComponent } from '../pagination/pagination.component';
import { BasicDictionary, TypeOfData } from '../../models';
import { GetDescriptionPipe } from '../../pipes/getdescription.pipe';

@Component({
  selector: 'widget-datagrid',
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [GetDescriptionPipe, DatePipe],
  imports: [CommonModule, PaginationComponent],
  templateUrl: './datagrid.component.html',
  styleUrl: './datagrid.component.scss'
})
export class DatagridComponent implements OnChanges {
  @Input('dataGridConfiguration') dataGridConfiguration?: DataGridConfiguration;
  @Input('dataContainer') dataContainer?: DataContainer;
  @Input('detailTemplate') detailTemplate?: TemplateRef<any>;
  @Output('goToPageFunction') goToPage: EventEmitter<number> = new EventEmitter();

  // Object injection
  private getDescriptionhPipe = inject(GetDescriptionPipe);
  private datePipe = inject(DatePipe);

  // Rest of attributes
  public idItemSelected: number = -1;

  ngOnChanges() {
    // New data: reset grid
    this.idItemSelected = -1;
  }

  public formatData(columnConfig: DataGridColumnConfig, value: string): string {
    switch(columnConfig.type) {
      case TypeOfData.DICTIONARY: {
        try {
          return this.getDescriptionhPipe.transform(value, columnConfig.dictionary);
        } catch (e) {
          console.log("ERROR formating data dictionary (field: " + columnConfig.field + ", value: " + value);
          console.log("Dictionary: ");
          console.log(columnConfig.dictionary);
          console.log("ERROR message: " + e);
          return value;
        }
      }
      case TypeOfData.DATE: {
        const date = this.datePipe.transform(new Date(value), 'longDate');
        return date ? date : '';
      }
      case TypeOfData.DATETIME: {
        const datetime = this.datePipe.transform(new Date(value), 'long');
        return datetime ? datetime : '';
      }
      default: {
        return value;
      }
    }
  }

  public navigateToPage(page: number): void {
    this.goToPage.emit(page);
  }

  public showDetail(id: number): void {
    if (this.idItemSelected === id) {
      this.idItemSelected = -1;
    } else {
      this.idItemSelected = id;
    }
  }
}

export class DataGridConfiguration {
  private columns: DataGridColumnConfig[];

  public constructor() {
    this.columns = [];
  }

  public getColumnsConfiguration(): DataGridColumnConfig[] {
    return this.columns;
  }

  public addColumnConfiguration(
    field: string,
    label: string,
    type: TypeOfData = TypeOfData.STRING,
    dictionary: BasicDictionary = {}
  ): void {
    const column: DataGridColumnConfig = {
      field: field,
      label: label,
      type: type,
      dictionary: dictionary
    }
    this.columns.push(column);
  }
}

type DataGridColumnConfig = {
  field: string,
  label: string,
  type: TypeOfData,
  dictionary: BasicDictionary
}




