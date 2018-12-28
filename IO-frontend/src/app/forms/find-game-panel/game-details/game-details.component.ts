import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../../shared/game.lobby';
import {SportsService} from '../../../services/sports.service';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {
  @Input() selectedGame: GameLobby;
  @Output() buttonClicked = new EventEmitter();

  constructor(private sportsService: SportsService) {
  }

  ngOnInit() {
  }

  onButtonClicked() {
    this.buttonClicked.emit();
  }

}
