import {Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {GameInfo} from '../../shared/game.info';
import {SportObject} from '../../shared/sport.object';
import {HttpClient} from '@angular/common/http';
import {TokenStorage} from '../../auth/token.storage';
import {GameForm} from '../../shared/game.form';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-host-game-panel',
  templateUrl: './host-game-panel.component.html',
  styleUrls: ['./host-game-panel.component.css']
})
export class HostGamePanelComponent implements OnInit {
  chosenSport = '';
  level: string;
  fee: number;
  players: number;
  address: string;
  date: string;
  invalidForm = false;
  sportIsChosen = true;
  priorityDateAfterDate = false;
  gameWasAdded = false;


  prioritiesForm: FormGroup;
  hostGameForm: FormGroup;

  preparePriorites = false;
  showPriorities = false;
  priorityDate: string;

  pitchRoles = {};

  sportObjects = [];

  addByName = false;
  addByAddress = false;

  showAddObjectForm = false;
  objectAdded = false;

  roles = {
    'FOOTBALL': {
      'bramkarz': 'GOALKEEPER',
      'obrońca': 'DEFENDER',
      'pomocnik': 'MIDFIELDER',
      'napastnik': 'STRIKER'
    },
    'BASKETBALL': {
      'rozgrywający': 'POINT_GUARD',
      'rzucający obrońca': 'SHOOTING_GUARD',
      'silny skrzydłowy': 'POWER_FORWARD',
      'środkowy': 'CENTER'
    },
    'VOLLEYBALL': {
      'atakujący': 'SPIKER',
      'libero': 'LIBERO',
      'przyjmujący': 'RECIEVER',
      'rozgrywający': 'SETTER',
      'środkowy': 'CENTER'
    }
  };
  Object = Object;


  private API = 'http://localhost:8080';
  private GAMES_API = this.API + '/api/games';

  private SPORTOBJECTS_API = this.API + '/api/sportObjects';


  constructor(private tokenStorage: TokenStorage, private http: HttpClient) {
  }


  ngOnInit() {
    this.hostGameForm = new FormGroup({
      'chosenSport': new FormControl(null, [Validators.required]),
      'level': new FormControl(null, [Validators.required]), // TODO add level enums
      'fee': new FormControl(null, [Validators.required, Validators.min(0)]),
      'players': new FormControl(null, [Validators.required, Validators.min(1)]),
      'address': new FormControl(null, [Validators.required]),
      'date': new FormControl(null, [Validators.required]), // TODO refuse if time is past
    });
    this.http.get<any[]>(this.SPORTOBJECTS_API).subscribe(
      data => {
        this.sportObjects = data;
      },
      error => console.log(error)
    );
    this.showAddObjectForm = false;
    this.objectAdded = false;
  }


  onSubmitButton() {
    if (this.hostGameForm.valid) {
      this.fee = this.hostGameForm.get('fee').value;
      this.players = Math.round(this.hostGameForm.get('players').value);
      this.date = this.hostGameForm.get('date').value;
      this.address = this.hostGameForm.get('address').value;
      this.level = this.hostGameForm.get('level').value;
      this.chosenSport = this.hostGameForm.get('chosenSport').value;


      if (this.prioritiesForm != null) { // TODO make it prettier
        this.priorityDate = this.prioritiesForm.get('priorityDate').value;
        if (this.priorityDate === null) {
          this.priorityDate = this.date;
          this.priorityDateAfterDate = false;
        } else if ((new Date(this.priorityDate).getTime() > new Date(this.date).getTime())) {
          this.priorityDateAfterDate = true;
        } else {
          this.priorityDateAfterDate = false;
        }
      }
      if (!this.priorityDateAfterDate) {
        let sum = 0;
        if (this.prioritiesForm != null) {
          this.showPriorities = false;
          Object.keys(this.roles[this.chosenSport.toUpperCase()]).forEach(
            key => {
              const value = this.prioritiesForm.get(this.roles[this.chosenSport.toUpperCase()][key]).value;
              if (value > 0) {
                this.pitchRoles[this.roles[this.chosenSport.toUpperCase()][key]] = value;
                sum += value;

              }
            }
          );
        }
        if (sum > this.pitchRoles) {
          console.log('more roles than players');
        } else {
          const newGame = new GameForm(this.fee, this.players, this.date, this.priorityDate,
            this.address, this.chosenSport.toUpperCase(), this.pitchRoles, this.level);
          this.http.post(this.GAMES_API, newGame).subscribe(
            () => {
              this.gameWasAdded = true;
              this.hostGameForm.reset();
              if (this.prioritiesForm != null) {
                this.prioritiesForm.reset();
              }

            });
        }
      }

    } else {
      this.invalidForm = true;
    }

  }


  capitalizeFirstLetter(word: string) {
    return word.charAt(0).toUpperCase() + word.slice(1);
  }

  onClickPriorities() {
    if (this.hostGameForm.get('chosenSport').valid) {
      this.preparePriorites = true;
      this.adjustPriorities();
    } else {
      this.sportIsChosen = false;
    }
  }

  adjustPriorities() {
    if (this.preparePriorites) {
      this.sportIsChosen = true;
      this.prioritiesForm = new FormGroup({'priorityDate': new FormControl(null, [])});
      this.chosenSport = this.hostGameForm.get('chosenSport').value;

      Object.keys(this.roles[this.chosenSport.toUpperCase()]).forEach(
        key => {
          this.prioritiesForm.addControl(
            this.roles[this.chosenSport.toUpperCase()][key], new FormControl(0, []));
        });
      this.showPriorities = true;

    }
  }

  showSuccessForm() {
    this.objectAdded = true;
    this.showAddObjectForm = false;
  }

}
