import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FeatureChoicePanelComponent } from './feature-choice-panel.component';

describe('FeatureChoicePanelComponent', () => {
  let component: FeatureChoicePanelComponent;
  let fixture: ComponentFixture<FeatureChoicePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FeatureChoicePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FeatureChoicePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
