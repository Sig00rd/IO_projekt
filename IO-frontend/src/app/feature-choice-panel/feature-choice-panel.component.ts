import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-feature-choice-panel',
  templateUrl: './feature-choice-panel.component.html',
  styleUrls: ['./feature-choice-panel.component.css']
})
export class FeatureChoicePanelComponent implements OnInit {
  @Output() featureEvent = new EventEmitter<string>();

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  chooseFeature(feature: string) {
    this.router.navigate([feature]);
  }

}
