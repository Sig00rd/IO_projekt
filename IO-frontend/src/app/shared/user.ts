import {GameLobby} from './game.lobby';

export class User {
  name: string;
  email: string;
  description: string;
  photo_url: string;
  games: any[];
  messages: ['Użytkownik julo zaprasza cię do gry', 'Użytkownik bodek zaprasza cię do gry']


  constructor(name: string, email: string, description: string, photo_url: string, games: any[]) {
    this.name = name;
    this.email = email;
    this.description = description;
    this.photo_url = photo_url;
    this.games = games;
  }
}
