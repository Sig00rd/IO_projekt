import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameInfo} from '../../../shared/game.info';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameLobby} from '../../../shared/game.lobby';
import {GamesService} from '../../../services/games.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})



export class GameListComponent implements OnInit {

  @Output() selectedGameEvent = new EventEmitter<GameLobby>();


  constructor(private http: HttpClient, private gamesService: GamesService) {
  }

  ngOnInit() {
    this.gamesService.updateGames();
  }

  chosenGame(gameLobby: GameLobby) {
    this.selectedGameEvent.emit(gameLobby);
  }



}
