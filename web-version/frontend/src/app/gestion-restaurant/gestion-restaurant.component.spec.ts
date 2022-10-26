import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionRestaurantComponent } from './gestion-restaurant.component';

describe('GestionRestaurantComponent', () => {
  let component: GestionRestaurantComponent;
  let fixture: ComponentFixture<GestionRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionRestaurantComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
