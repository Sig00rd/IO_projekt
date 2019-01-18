import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HostedGamesComponent } from './hosted-games.component';

describe('HostedGamesComponent', () => {
  let component: HostedGamesComponent;
  let fixture: ComponentFixture<HostedGamesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HostedGamesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HostedGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
