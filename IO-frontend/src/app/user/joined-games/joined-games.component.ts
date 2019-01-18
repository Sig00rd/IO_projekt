import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-joined-games',
  templateUrl: './joined-games.component.html',
  styleUrls: ['./joined-games.component.css']
})
export class JoinedGamesComponent implements OnInit {
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


  @Input() games = [];

  constructor() {
  }

  ngOnInit() {

  }


}
