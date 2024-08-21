import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartysearchComponent } from './search.component';

describe('PartysearchComponent', () => {
  let component: PartysearchComponent;
  let fixture: ComponentFixture<PartysearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartysearchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartysearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
