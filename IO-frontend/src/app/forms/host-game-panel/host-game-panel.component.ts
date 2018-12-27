import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../shared/game.lobby';

@Component({
  selector: 'app-host-game-panel',
  templateUrl: './host-game-panel.component.html',
  styleUrls: ['./host-game-panel.component.css']
})
export class HostGamePanelComponent implements OnInit {
  chosenSport = '';
  level: string;
  fee: number;
  players: number;
  address: string;
  city: string;
  date: string;
  @Output() newGameEvent = new EventEmitter<GameLobby>();

  constructor() {
  }

  ngOnInit() {
  }

  onSubmitButton() {
    const newGame = new GameLobby(this.chosenSport, this.address,
      this.city, this.players, this.date, this.fee, this.level);
    this.newGameEvent.emit(newGame);
    console.dir(newGame);
  }

}
