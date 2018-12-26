import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../../shared/game.lobby';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {
  @Input() selectedGame: GameLobby;
  @Output() buttonClicked = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  onButtonClicked() {
    this.buttonClicked.emit();
  }

}
