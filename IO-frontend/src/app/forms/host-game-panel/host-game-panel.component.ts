import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../shared/game.lobby';
import {SportObject} from '../../shared/sport.object';
import {HttpClient} from '@angular/common/http';
import {TokenStorage} from '../../auth/token.storage';
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

  showPriorities = false;
  priorityDate: string;

  pitchRoles = {};

  roles = {
    'FOOTBALL': {
      'bramkarz': 'GOALKEEPER',
      'obrońca': 'DEFENDER',
      'pomocnik': 'MIDFIELDER',
      'napastnik': 'STRIKER'
    },
    'BASKETBALL': {
      'rozgrywający': 'POINT_GUARD',
      'rzucający obrońca': 'SHOOTING_GUARD',
      'silny skrzydłowy': 'POWER_FORWARD',
      'środkowy': 'CENTER'
    },
    'VOLLEYBALL': {
      'atakujący': 'SPIKER',
      'libero': 'LIBERO',
      'przyjmujący': 'RECIEVER',
      'rozgrywający': 'SETTER',
      'środkowy': 'CENTER'
    }
  };

  Object = Object;


  private API = 'http://localhost:8080';
  private GAMES_API = this.API + '/api/games';


  constructor(private tokenStorage: TokenStorage, private http: HttpClient) {
  }

  ngOnInit() {
  }

  onSubmitButton() {
    if (this.priorityDate == null) { // TODO make it prettier
      this.priorityDate = this.date;
    }
    const newGame = new GameForm(this.fee, this.players, this.date, this.priorityDate,
      (this.address + ', ' + this.city), this.chosenSport.toUpperCase(), this.pitchRoles, this.level);
    console.log(newGame);
    this.http.post(this.GAMES_API, newGame).subscribe(
      (response) => console.log(response));
  }


  capitalizeFirstLetter(word: string) {
    return word.charAt(0).toUpperCase() + word.slice(1);
  }

  adjustPriorities() {
    this.showPriorities = true;
    Object.keys(this.roles[this.chosenSport.toUpperCase()]).forEach(
      key => this.pitchRoles[this.roles[this.chosenSport.toUpperCase()][key]] = 0
    );
    console.log(this.pitchRoles);
  }
}
