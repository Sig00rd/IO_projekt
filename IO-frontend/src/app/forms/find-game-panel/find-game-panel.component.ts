import {Component, Input, OnInit} from '@angular/core';
import {GameInfo} from '../../shared/game.info';
import {MapsService} from '../../services/maps.service';
import {GameLobby} from '../../shared/game.lobby';

@Component({
  selector: 'app-find-game-panel',
  templateUrl: './find-game-panel.component.html',
  styleUrls: ['./find-game-panel.component.css']
})
export class FindGamePanelComponent implements OnInit {
  selectedGame: GameInfo;

  message = 'Wybierz grę, aby poznać szczegóły';

  constructor(private mapsService: MapsService) {
  }

  ngOnInit() {
  }

  selectGame(gameLobby: GameLobby) {
    this.selectedGame = gameLobby;
    this.mapsService.getLocation(this.selectedGame.sportObject.address, this.selectedGame.sportObject.city).subscribe(
      (response) => {
        this.mapsService.location = response['results'][0]['geometry']['location'];
      });
  }

}
