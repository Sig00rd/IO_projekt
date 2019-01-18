import {Component, Input, OnInit} from '@angular/core';
import {GameInfo} from '../../shared/game.info';
import {MapsService} from '../../services/maps.service';
import {GameLobby} from '../../shared/game.lobby';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {GamesService} from '../../services/games.service';

@Component({
  selector: 'app-find-game-panel',
  templateUrl: './find-game-panel.component.html',
  styleUrls: ['./find-game-panel.component.css']
})
export class FindGamePanelComponent implements OnInit {
  selectedGame: GameInfo;

  private API = 'http://localhost:8080/api/lobby/';
  message = 'Wybierz grę, aby poznać szczegóły';

  constructor(private mapsService: MapsService, private router: Router,
              private http: HttpClient, private gamesService: GamesService) {
  }

  ngOnInit() {
  }

  selectGame(gameLobby: GameLobby) {
    this.selectedGame = gameLobby;
    this.mapsService.getLocation(this.selectedGame.sportObject.address, this.selectedGame.sportObject.city).subscribe(
      (response) => {
        this.mapsService.location = response['results'][0]['geometry']['location'];
      });
    this.router.navigate(['/find', this.selectedGame.id]);
  }

  findAndSelectGame() {
    const url = window.location.href.split('/');
    const id = url[url.length - 1];
    this.http.get<GameLobby>(this.API + id).subscribe(
      data => {
        this.selectGame(data);
        this.router.navigate(['/find', id]);
      },
          error => console.log(error)
    );
  }

}
