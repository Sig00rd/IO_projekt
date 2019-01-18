import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportObjectComponent } from './sport-object.component';

describe('SportObjectComponent', () => {
  let component: SportObjectComponent;
  let fixture: ComponentFixture<SportObjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportObjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportObjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
