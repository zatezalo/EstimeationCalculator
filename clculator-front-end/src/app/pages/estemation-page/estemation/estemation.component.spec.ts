import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstemationComponent } from './estemation.component';

describe('EstemationComponent', () => {
  let component: EstemationComponent;
  let fixture: ComponentFixture<EstemationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstemationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EstemationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
