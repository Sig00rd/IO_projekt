export class User {
  name: string;
  email: string;
  description: string;
  photo_url: string;
  games: any[];
  id: string;
  messagesRead = [];
  messagesUnread = [];


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
      message => {
        if (message.read) {
          this.messagesRead.push(this.buildMessages(message));
        } else {
          this.messagesUnread.push(this.buildMessages(message));
        }

      }
    );
    return this;
  }

  buildMessages(message: any[]) {
    if (message['type'] === 'INVITATION') {
      return {
        'body': 'Użytkownik ' + message['sender'] + ' zaprasza cię do gry',
        'gameId': message['game'], 'header': 'Zaproszenie do gry', 'id': message['id']
      };
    } else if (message['type'] === 'INFORMATION') {
      return {
        'header': 'Wypisanie z gry', 'gameId': message['game'], 'body': 'Użytkownik '
          + message['sender'] + ' wypisał się z gry', 'id': message['id']
      };
    }
    return {'body': '', 'gameId': '', 'header': ''};
  }
}

