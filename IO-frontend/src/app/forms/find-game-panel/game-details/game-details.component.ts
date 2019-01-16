import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {GameInfo} from '../../../shared/game.info';
import {SportsService} from '../../../services/sports.service';
import {MapsService} from '../../../services/maps.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameLobby} from '../../../shared/game.lobby';
import {GamesService} from '../../../services/games.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit, OnChanges {
  private API = 'http://localhost:8080/api/games/';
  private MESSAGES_API = 'http://localhost:8080/api/messages/lobby/';

  @Input() selectedGame: GameLobby;
  @Input() public lat: number;
  @Input() public lng: number;
  prioritiesForm: FormGroup;

  roleNotChosen = false;
  registeredToGame = false;

  prioritiesNeeded: string[] = [];

  message = '';
  messages = [];

  priorities = false;
  roles = {
    'GOALKEEPER': 'bramkarz',
    'DEFENDER': 'obrońca',
    'MIDFIELDER': 'pomocnik',
    'STRIKER': 'napastnik',
    'POINT_GUARD': 'rozgrywający',
    'SHOOTING_GUARD': 'rzucający obrońca',
    'POWER_FORWARD': 'silny skrzydłowy',
    'CENTER': 'środkowy',
    'SPIKER': 'atakujący',
    'LIBERO': 'libero',
    'RECIEVER': 'przyjmujący',
    'SETTER': 'rozgrywający',
  };

  constructor(private sportsService: SportsService, private gamesService: GamesService, private http: HttpClient) {
  }


  ngOnInit() {
    this.registeredToGame = false;
    if (Object.keys(this.selectedGame.prioritiesNeeded).length !== 0) {
      this.priorities = true;
      this.prioritiesForm = new FormGroup({'priority': new FormControl(null)});
      Object.keys(this.selectedGame.prioritiesNeeded).forEach(
        key => {
          this.prioritiesNeeded.push(key);
        }
      );
      this.prioritiesNeeded = Array.from(new Set(this.prioritiesNeeded.map((item: any) => item)));
    } else {
      this.priorities = false;
    }
    this.refreshMessages();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.ngOnInit();
  }


  onButtonClicked() {
    const gameId = this.selectedGame.id;
    const GAME_API = this.API + gameId;
    const body = this.priorities ? this.prioritiesForm.get('priority').value : {};
    if (this.priorities && !this.prioritiesForm.dirty) {
      this.roleNotChosen = true;
      this.registeredToGame = false;
      window.scroll(0, 0);
    } else {
      this.roleNotChosen = false;
      this.http.post<any>(GAME_API, body).subscribe(
        data => {
          this.selectedGame = data;
          this.gamesService.updateGames();
          window.scroll(0, 0);
          this.registeredToGame = true;
        },
        error => console.log(error)
      );
    }
  }

  capitalizeFirstLetter(word: string) {
    return word.charAt(0).toUpperCase() + word.slice(1);
  }

  sendMessage() {
    this.http.post<string>(this.MESSAGES_API + this.selectedGame.id, this.message).subscribe(
      data => this.refreshMessages(),
      error => console.log(error)
    );
    this.message = '';
  }

  refreshMessages() {
    this.http.get<string[]>(this.MESSAGES_API + this.selectedGame.id).subscribe(
      data => this.messages = data,
      error => console.log(error)
    );
  }

}
