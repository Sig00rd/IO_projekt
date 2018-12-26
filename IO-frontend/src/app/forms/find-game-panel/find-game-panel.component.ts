import {Component, Input, OnInit} from '@angular/core';
import {GameLobby} from '../../shared/game.lobby';

@Component({
  selector: 'app-find-game-panel',
  templateUrl: './find-game-panel.component.html',
  styleUrls: ['./find-game-panel.component.css']
})
export class FindGamePanelComponent implements OnInit {
  selectedGame: GameLobby;

  message = 'Wybierz grę, aby poznać szczegóły';

  constructor() { }

  ngOnInit() {
  }

  playerJoined() {
    this.selectedGame = null;
    this.message = 'Do zobaczenia!';
  }

}
