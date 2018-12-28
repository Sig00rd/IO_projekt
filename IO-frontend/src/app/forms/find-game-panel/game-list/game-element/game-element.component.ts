import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameLobby} from '../../../../shared/game.lobby';
import {SportsService} from '../../../../services/sports.service';

@Component({
  selector: 'app-game-element',
  templateUrl: './game-element.component.html',
  styleUrls: ['./game-element.component.css']
})
export class GameElementComponent implements OnInit {
  @Input() game: GameLobby;
  @Output() chosenGameEvent = new EventEmitter<GameLobby>();

  constructor(private sportsService: SportsService) {
  }

  ngOnInit() {
  }

  onSelect() {
    this.chosenGameEvent.emit(this.game);
  }


}
