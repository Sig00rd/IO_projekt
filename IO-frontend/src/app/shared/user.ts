import {GameLobby} from './game.lobby';
import {HttpClient} from '@angular/common/http';

export class User {
  name: string;
  email: string;
  description: string;
  photo_url: string;
  games: any[];
  id: string;
  messages = [];

  // messages = ['Użytkownik julo zaprasza cię do gry', 'Użytkownik bodek zaprasza cię do gry'];

  constructor(name: string) {
    this.name = name;

  }

  setEmail(email: string): User {
    this.email = email;
    return this;
  }

  setDescription(description: string): User {
    this.description = description;
    return this;
  }

  setPhotoUrl(photo_url: string): User {
    this.photo_url = photo_url;
    return this;
  }

  setGames(games: any[]): User {
    this.games = games;
    return this;
  }

  setID(id: string): User {
    this.id = id;
    return this;
  }

  setMessages(messages: any[]): User {
    messages.forEach(
      message => this.messages.push(this.buildMessages(message))
    );
    return this;
  }

  buildMessages(message: any[]) {
    if (message['type'] === 'INVITATION') {
      return {
        'body': 'Użytkownik ' + message['sender'] + ' zaprasza cię do gry',
        'gameId': message['game'], 'header': 'Zaproszenie do gry'
      };
    } else if (message['type'] === 'INFORMATION') {
      return {
        'header': 'Wypisanie z gry', 'gameId': message['game'], 'body': 'Użytkownik '
          + message['sender'] + ' wypisał się z gry'
      };
    }
    return {'body': '', 'gameId': '', 'header': ''};
  }
}
