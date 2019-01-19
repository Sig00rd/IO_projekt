import {Component, OnInit} from '@angular/core';
import {UserService} from '../services/user.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  gamesHosted = [];
  gamesJoined = [];
  private JOINED_API = 'http://localhost:8080/api/games/signedUp';
  private HOSTED_GAME = 'http://localhost:8080/api/mygames';


  constructor(private userService: UserService, private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get<any>(this.JOINED_API).subscribe(
      data => this.gamesJoined = data,
      error => console.log(error)
    );
    this.http.get<any>(this.HOSTED_GAME).subscribe(
      data => this.gamesHosted = data,

      error => console.log(error)
    );
  }

}
