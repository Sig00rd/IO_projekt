import {Component, Input, OnInit} from '@angular/core';
import {SportsService} from '../../services/sports.service';

@Component({
  selector: 'app-games-element',
  templateUrl: './games-element.component.html',
  styleUrls: ['./games-element.component.css']
})
export class GamesElementComponent implements OnInit {
  @Input() game = [];

  constructor(private sportsService: SportsService) { }

  ngOnInit() {
  }

}
