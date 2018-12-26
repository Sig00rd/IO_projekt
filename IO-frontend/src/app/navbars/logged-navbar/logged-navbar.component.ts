import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from '../../shared/user';

@Component({
  selector: 'app-logged-navbar',
  templateUrl: './logged-navbar.component.html',
  styleUrls: ['./logged-navbar.component.css']
})
export class LoggedNavbarComponent implements OnInit {
  private user: User;
  @Output() private logOutEvent = new EventEmitter();
  @Output() private resetEvent = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
    this.user = new User('Tomek', 'tomeczek@tomek.com');
  }


  logOutUser() {
    this.logOutEvent.emit();

  }

  backToFeatures() {
    this.resetEvent.emit();
  }
}
