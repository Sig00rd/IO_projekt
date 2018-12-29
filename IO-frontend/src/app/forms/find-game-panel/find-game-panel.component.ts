import {Component, Input, OnInit} from '@angular/core';
import {GameLobby} from '../../shared/game.lobby';
import {MapsService} from '../../services/maps.service';

@Component({
  selector: 'app-find-game-panel',
  templateUrl: './find-game-panel.component.html',
  styleUrls: ['./find-game-panel.component.css']
})
export class FindGamePanelComponent implements OnInit {
  selectedGame: GameLobby;

  message = 'Wybierz grę, aby poznać szczegóły';

  constructor(private mapsService: MapsService) {
  }

  ngOnInit() {
  }

  playerJoined() {
    this.selectedGame = null;
    this.message = 'Do zobaczenia!';
  }

  selectGame(gameLobby: GameLobby) {
    this.selectedGame = gameLobby;
    this.mapsService.getLocation(this.selectedGame.sportObject.address, this.selectedGame.sportObject.city).subscribe(
      (response) => {
        this.mapsService.location = response['results'][0]['geometry']['location'];
      });
  }

}
