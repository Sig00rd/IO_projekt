import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameNotExistingComponent } from './game-not-existing.component';

describe('GameNotExistingComponent', () => {
  let component: GameNotExistingComponent;
  let fixture: ComponentFixture<GameNotExistingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GameNotExistingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameNotExistingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
