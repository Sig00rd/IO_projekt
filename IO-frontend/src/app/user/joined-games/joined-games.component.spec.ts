import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinedGamesComponent } from './joined-games.component';

describe('JoinedGamesComponent', () => {
  let component: JoinedGamesComponent;
  let fixture: ComponentFixture<JoinedGamesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinedGamesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinedGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
