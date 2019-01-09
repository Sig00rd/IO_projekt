import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameInfo} from '../../../../shared/game.info';
import {SportsService} from '../../../../services/sports.service';
import {HttpClient} from '@angular/common/http';
import {GameLobby} from '../../../../shared/game.lobby';

@Component({
  selector: 'app-game-element',
  templateUrl: './game-element.component.html',
  styleUrls: ['./game-element.component.css']
})
export class GameElementComponent implements OnInit {
  @Input() game: GameInfo;
  @Output() chosenGameEvent = new EventEmitter<GameLobby>();
  private API = 'http://localhost:8080/api/lobby/';
  gameLobby: GameLobby;

  constructor(private sportsService: SportsService, private http: HttpClient) {
  }

  ngOnInit() {
  }

  onSelect() {
    const lobbyAPI = this.API + this.game.id;
    this.http.get<GameLobby>(lobbyAPI).subscribe(
      data => {
        this.gameLobby = data;
        this.chosenGameEvent.emit(this.gameLobby);
      },
          error => console.log(error)
    );


  }


}
