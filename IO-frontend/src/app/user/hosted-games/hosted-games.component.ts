import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-hosted-games',
  templateUrl: './hosted-games.component.html',
  styleUrls: ['./hosted-games.component.css']
})
export class HostedGamesComponent implements OnInit {
  @Input() games = [];

  constructor() {
  }

  ngOnInit() {
  }

}
