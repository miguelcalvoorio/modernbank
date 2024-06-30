import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyfileComponent } from './partyfile.component';

describe('PartyfileComponent', () => {
  let component: PartyfileComponent;
  let fixture: ComponentFixture<PartyfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartyfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartyfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
