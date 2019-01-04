import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../shared/game.lobby';
import {SportObject} from '../../shared/sport.object';
import {LoggedUserService} from '../../services/logged.user.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameForm} from '../../shared/game.form';

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

  private API = 'http://localhost:8080';
  private GAMES_API = this.API + '/api/games';
  @Output() newGameEvent = new EventEmitter<GameLobby>();

  constructor(private loggedUserService: LoggedUserService, private http: HttpClient) {
  }

  ngOnInit() {
  }

  onSubmitButton() {
    const newGameForm = new GameForm(this.fee, this.players,
      this.date, this.date, (this.address + ', ' + this.city), this.chosenSport, {'GOALKEEPER': 1}, this.level);
    console.log(newGameForm);
    const headers = new HttpHeaders( {
      authorization : 'Basic ' + btoa('julo:julo')
    });
    const newGame = new GameLobby(this.level, this.date, this.chosenSport, this.players,
      this.fee, this.loggedUserService.user, new SportObject(this.address, this.city, '', ''));
    this.http.post(this.GAMES_API, newGameForm, {headers: headers}).subscribe(
      (respone) => console.log(respone));
    this.newGameEvent.emit(newGame);
  }

}
