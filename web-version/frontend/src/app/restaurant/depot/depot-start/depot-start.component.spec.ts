import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepotStartComponent } from './depot-start.component';

describe('DepotStartComponent', () => {
  let component: DepotStartComponent;
  let fixture: ComponentFixture<DepotStartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepotStartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepotStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
