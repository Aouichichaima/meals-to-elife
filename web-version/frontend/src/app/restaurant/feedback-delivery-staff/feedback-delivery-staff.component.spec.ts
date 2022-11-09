import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackDeliveryStaffComponent } from './feedback-delivery-staff.component';

describe('FeedbackDeliveryStaffComponent', () => {
  let component: FeedbackDeliveryStaffComponent;
  let fixture: ComponentFixture<FeedbackDeliveryStaffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackDeliveryStaffComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackDeliveryStaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
