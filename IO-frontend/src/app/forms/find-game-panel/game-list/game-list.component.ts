import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../../shared/game.lobby';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})



export class GameListComponent implements OnInit {
  gameList: GameLobby[] = [];
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  @Output() selectedGameEvent = new EventEmitter<GameLobby>();

  private API = 'http://localhost:8080';
  private GAMES_API = this.API + '/api/games';

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getGames();
  }

  chosenGame(gameLobby: GameLobby) {
    this.selectedGameEvent.emit(gameLobby);
  }

  getGames() {
    this.http.get<GameLobby[]>(this.GAMES_API, this.httpOptions).subscribe(
      data =>  {
        this.gameList = data;
      });
  }


}
