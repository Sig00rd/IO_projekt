import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-feature-choice-panel',
  templateUrl: './feature-choice-panel.component.html',
  styleUrls: ['./feature-choice-panel.component.css']
})
export class FeatureChoicePanelComponent implements OnInit {
  @Output() featureEvent = new EventEmitter<string>();

  constructor() {
  }

  ngOnInit() {
  }

  chooseFeature(feature: string) {
    this.featureEvent.emit(feature);
  }

}
