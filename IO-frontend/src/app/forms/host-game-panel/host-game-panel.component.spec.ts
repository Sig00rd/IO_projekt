import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HostGamePanelComponent } from './host-game-panel.component';

describe('HostGamePanelComponent', () => {
  let component: HostGamePanelComponent;
  let fixture: ComponentFixture<HostGamePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HostGamePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HostGamePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
