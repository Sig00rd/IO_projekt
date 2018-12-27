import {Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {User} from '../../shared/user';
import {LoggedUserService} from '../../services/logged.user.service';

@Component({
  selector: 'app-logged-navbar',
  templateUrl: './logged-navbar.component.html',
  styleUrls: ['./logged-navbar.component.css']
})
export class LoggedNavbarComponent implements OnInit {
  private user: User;
  @Output() private logOutEvent = new EventEmitter();
  @Output() private resetEvent = new EventEmitter();

  constructor(private loggedUserService: LoggedUserService) {
  }

  ngOnInit() {
    this.user = new User(this.loggedUserService.user, 'tomeczek@tomek.com');
    console.log(this.loggedUserService.user + ' logged service user');
  }




  logOutUser() {
    this.logOutEvent.emit();

  }

  backToFeatures() {
    this.resetEvent.emit();
  }
}
