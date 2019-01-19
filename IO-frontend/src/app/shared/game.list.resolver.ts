import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {GamesService} from '../services/games.service';
import {GameInfo} from './game.info';
import {Injectable} from '@angular/core';

@Injectable()
export class GameListResolver implements Resolve<GameInfo[]> {
  games: GameInfo[];

  constructor(private gamesService: GamesService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GameInfo[]> | Promise<GameInfo[]> | GameInfo[] {
    this.gamesService.getGames().subscribe(
      data => this.games = data,
      error => console.log(error)
    );
    return this.games;
  }


}
