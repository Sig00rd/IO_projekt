import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesElementComponent } from './games-element.component';

describe('GamesElementComponent', () => {
  let component: GamesElementComponent;
  let fixture: ComponentFixture<GamesElementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GamesElementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GamesElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
