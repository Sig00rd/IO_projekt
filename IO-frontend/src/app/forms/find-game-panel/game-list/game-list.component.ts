import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../../shared/game.lobby';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  @Output() selectedGameEvent = new EventEmitter<GameLobby>();
  @Input() gameList: GameLobby[] = [new GameLobby('Siatkówka', 'Zarzecze 12', 'Kraków', 5, '12/12/2012', 15, 'A'),
    new GameLobby('Piłka nożna', 'Krakowskie Przedmieście 9', 'Warszawa', 8, '07/01/2015', 8, 'CC/B')];

  private API = 'http://localhost:8080';
  private GAMES_API = this.API + '/api/register';

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getGames();
  }

  chosenGame(gameLobby: GameLobby) {
    this.selectedGameEvent.emit(gameLobby);
  }

  getGames() {
    this.http.get(this.GAMES_API).subscribe(
      (respond) => console.log(respond));
  }


}
