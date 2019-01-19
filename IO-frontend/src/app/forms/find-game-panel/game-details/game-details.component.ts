import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {GameInfo} from '../../../shared/game.info';
import {SportsService} from '../../../services/sports.service';
import {MapsService} from '../../../services/maps.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameLobby} from '../../../shared/game.lobby';
import {GamesService} from '../../../services/games.service';
import {FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, Params} from '@angular/router';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit, OnChanges {
  private API = 'http://localhost:8080/api/games/';
  private LOBBY_API = 'http://localhost:8080/api/lobby/';
  private MESSAGES_API = 'http://localhost:8080/api/messages/lobby/';
  private INVITATIONS_API = 'http://localhost:8080/api/notifications';
  private USERS_API = 'http://localhost:8080/api/users/';

  selectedGame: GameLobby;
  public lat: number;
  public lng: number;
  prioritiesForm: FormGroup;
  givingUpButtons: FormGroup;
  showInviteForm = false;
  inviteSent = false;
  myRoles = [];

  userNotExisting = false;
  showGame = false;
  roleNotChosen = false;
  registeredToGame = false;
  givenUp = false;
  notSignedIn = false;
  showGivingUpButtons = false;

  defaultsNeeded = 0;
  prioritiesNeeded: string[] = [];
  rolesAmounts = {};

  invitedUser = '';
  message = '';
  messages = [];

  priorities = false;
  roles = {
    'GOALKEEPER': 'bramkarz',
    'DEFENDER': 'obrońca',
    'MIDFIELDER': 'pomocnik',
    'STRIKER': 'napastnik',
    'POINT_GUARD': 'rozgrywający',
    'SHOOTING_GUARD': 'rzucający obrońca',
    'POWER_FORWARD': 'silny skrzydłowy',
    'CENTER': 'środkowy',
    'SPIKER': 'atakujący',
    'LIBERO': 'libero',
    'RECIEVER': 'przyjmujący',
    'SETTER': 'rozgrywający',
    'DEFAULT': 'dowolnie'
  };

  constructor(private sportsService: SportsService, private gamesService: GamesService,
              private http: HttpClient, private route: ActivatedRoute, private mapsService: MapsService,
              private userService: UserService) {
  }


  ngOnInit() {
    this.http.get<GameLobby>(this.LOBBY_API + this.route.snapshot.params['id']).subscribe(
      data => {
        this.selectedGame = data;
        console.log(this.selectedGame);
        this.refreshGame();
      },
      error => console.log(error)
    );
    this.route.params
      .subscribe(
        (params: Params) => {
          this.http.get<GameLobby>(this.LOBBY_API + params['id']).subscribe(
            data => {
              this.showGame = false;
              this.selectedGame = data;
              this.refreshGame();
            },
            error => console.log(error)
          );
        }
      );
  }

  refreshGame() {
    this.showGivingUpButtons = false;
    this.showGame = false;
    this.notSignedIn = false;
    this.myRoles = [];
    this.mapsService.getLocation(this.selectedGame.sportObject.address, this.selectedGame.sportObject.city).subscribe(
      (response) => {
        this.mapsService.location = response['results'][0]['geometry']['location'];
        this.lat = this.mapsService.location['lat'];
        this.lng = this.mapsService.location['lng'];
      },
      error => console.log(error)
    );

    this.registeredToGame = false;
    if (Object.keys(this.selectedGame.prioritiesNeeded).length !== 0) {
      this.priorities = true;
      this.prioritiesForm = new FormGroup({'priority': new FormControl(null)});
      Object.keys(this.selectedGame.prioritiesNeeded).forEach(
        key => {
          this.prioritiesNeeded.push(key);
        }
      );
      this.prioritiesNeeded = Array.from(new Set(this.prioritiesNeeded.map((item: any) => item)));
    } else {
      this.priorities = false;
    }
    let sum = 0;
    for (const role of this.prioritiesNeeded) {
      const active = this.selectedGame.playerRoles[role] != null ? this.selectedGame.playerRoles[role].length : 0;
      this.rolesAmounts[role] = this.selectedGame.prioritiesNeeded[role] - active;
      sum += this.rolesAmounts[role];
    }
    if (sum > 0) {
      this.defaultsNeeded = this.selectedGame.stillNeeded - sum;
    } else {
      this.defaultsNeeded = 0;
    }

    this.refreshMessages();
    this.showGame = true;
    this.selectedGame.firstSquad.forEach(
      (item, index) => this.selectedGame.firstSquad[index] = this.replaceRole(item)
    );
    this.selectedGame.reserve.forEach(
      (item, index) => this.selectedGame.reserve[index] = this.replaceRole(item)
    );
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.ngOnInit();

  }


  onButtonClicked() {
    const gameId = this.selectedGame.id;
    const GAME_API = this.API + gameId;
    const body = this.priorities ? this.prioritiesForm.get('priority').value : {};
    if (this.priorities && !this.prioritiesForm.dirty) {
      this.roleNotChosen = true;
      this.registeredToGame = false;
      window.scroll(0, 0);
    } else {
      this.roleNotChosen = false;
      this.http.post<any>(GAME_API, body).subscribe(
        data => {
          this.selectedGame = data;
          this.gamesService.updateGames();
          this.refreshGame();
          window.scroll(0, 0);
          this.registeredToGame = true;
          this.notSignedIn = false;
          this.givenUp = false;
        },
        error => console.log(error)
      );
    }
  }

  capitalizeFirstLetter(word: string) {
    return word.charAt(0).toUpperCase() + word.slice(1);
  }

  sendMessage() {
    this.http.post<string>(this.MESSAGES_API + this.selectedGame.id, this.message).subscribe(
      data => this.refreshMessages(),
      error => console.log(error)
    );
    this.message = '';
  }

  refreshMessages() {
    this.http.get<string[]>(this.MESSAGES_API + this.selectedGame.id).subscribe(
      data => this.messages = data,
      error => console.log(error)
    );
  }

  replaceRole(role: string): string {
    Object.keys(this.roles).forEach(
      key => {
        if (role.includes(key)) {
          role = role.replace(key, this.roles[key]);
          return role;
        }
      }
    );
    return role;
  }


  onInviteClicked() {
    if (!this.showInviteForm) {
      this.showInviteForm = true;
    } else if (this.invitedUser !== '') {
      this.http.get(this.USERS_API + this.invitedUser).subscribe(
        data => {
          if (data === null) {
            this.userNotExisting = true;
          } else {
            this.inviteUser(data['id']);
          }
        }
      )
      ;
    }

  }

  inviteUser(id: number) {
    const invite = {
      'receiverId': id, 'gameId':
      this.selectedGame.id, 'type': 'INVITATION', 'read': false
    };
    this.http.post(this.INVITATIONS_API, invite).subscribe(
      data => {
        this.inviteSent = true;
        window.scroll(0, 0);
        this.showInviteForm = false;
        this.userNotExisting = false;
        this.invitedUser = '';
      },
      error => console.log(error)
    );
  }

  onGiveUpClicked() {
    if (this.prioritiesNeeded.length === 0) {
      this.http.delete(this.API + this.selectedGame.id).subscribe(
        data => {
          this.givenUp = true;
          this.ngOnChanges(null);
          window.scroll(0, 0);

        },
        error => {
          this.notSignedIn = true;
          this.givenUp = false;
        }
      );
    } else if (!this.showGivingUpButtons && this.prioritiesNeeded.length > 0) {
      this.http.get<any[]>('http://localhost:8080/api/games/signedUp').subscribe(
        data => {
          data.forEach(
            key => {
              if (key['gameId'] === this.selectedGame.id) {
                this.myRoles.push(key['myPitchRole']);
              }
            }
          );
          this.myRoles = this.myRoles.map(item => item)
            .filter((value, index, self) => self.indexOf(value) === index);
          this.givingUpButtons = new FormGroup({'gRole': new FormControl(null)});
          this.showGivingUpButtons = true;
        }
      );
    } else if (this.showGivingUpButtons) {
      const request = ('/' + this.givingUpButtons.get('gRole').value);
      this.http.delete(this.API + this.selectedGame.id + request).subscribe(
        data => {
          this.givenUp = true;
          this.ngOnChanges(null);
          window.scroll(0, 0);

        },
        error => {
          this.notSignedIn = true;
          this.givenUp = false;
        }
      );
    }
  }
}
