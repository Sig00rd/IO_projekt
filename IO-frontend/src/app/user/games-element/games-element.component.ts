import {Component, Input, OnInit} from '@angular/core';
import {SportsService} from '../../services/sports.service';
import {HttpClient} from '@angular/common/http';
import {GameLobby} from '../../shared/game.lobby';
import {Router} from '@angular/router';
import {GamesService} from '../../services/games.service';

@Component({
  selector: 'app-games-element',
  templateUrl: './games-element.component.html',
  styleUrls: ['./games-element.component.css']
})
export class GamesElementComponent implements OnInit {
  @Input() game: GameLobby;
  private DELETE_API = 'http://localhost:8080/api/mygames/';

  constructor(private sportsService: SportsService, private http: HttpClient,
              private router: Router, private gamesService: GamesService) {
  }

  ngOnInit() {
  }

  onCancelClicked() {
    if (confirm('Czy chcesz usunąć wyświetloną grę? Poinformujemy o tym zapisanych użytkowników')) {
      this.http.delete(this.DELETE_API + this.game.id).subscribe(
        data => {
          this.gamesService.updateGames();
          this.router.navigate(['/find']);
        },
            error => console.log(error)

      );
    }
  }

}
