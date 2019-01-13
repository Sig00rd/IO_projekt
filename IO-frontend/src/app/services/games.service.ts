import {GameInfo} from '../shared/game.info';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GamesService {
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  gameList: GameInfo[] = [];
  private API = 'http://localhost:8080';
  private GAMES_API = this.API + '/api/games';


  constructor(private http: HttpClient) {
  }


  updateGames() {
    this.http.get<GameInfo[]>(this.GAMES_API, this.httpOptions).subscribe(
      data => {
        this.gameList = data;
      });
  }
}
