import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameInfo} from '../../../shared/game.info';
import {SportsService} from '../../../services/sports.service';
import {MapsService} from '../../../services/maps.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameLobby} from '../../../shared/game.lobby';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {
  private API = 'http://localhost:8080/api/games/';

  @Input() selectedGame: GameLobby;
  @Output() buttonClicked = new EventEmitter();
  @Input() public lat: number;
  @Input() public lng: number;

  constructor(private sportsService: SportsService, private http: HttpClient) {
  }

  ngOnInit() {
    console.log(this.selectedGame);
  }

  onButtonClicked() {
    const gameId = this.selectedGame.id;
    const GAME_API = this.API + gameId;
    this.http.post<any>(GAME_API, {}).subscribe(
      data => {
        this.selectedGame = data;
        this.buttonClicked.emit();
        console.log(data);
      },
      error => console.log(error)
    );

  }

}
