import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindGamePanelComponent } from './find-game-panel.component';

describe('FindGamePanelComponent', () => {
  let component: FindGamePanelComponent;
  let fixture: ComponentFixture<FindGamePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindGamePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindGamePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
