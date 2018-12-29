import {Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {User} from '../../shared/user';
import {LoggedUserService} from '../../services/logged.user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-logged-navbar',
  templateUrl: './logged-navbar.component.html',
  styleUrls: ['./logged-navbar.component.css']
})
export class LoggedNavbarComponent implements OnInit {
  private user: User;
  @Output() private logOutEvent = new EventEmitter();

  constructor(private loggedUserService: LoggedUserService, private router: Router) {
  }

  ngOnInit() {
    this.user = new User(this.loggedUserService.user, 'tomeczek@tomek.com'); // TODO get proper email
  }




  logOutUser() {
    this.logOutEvent.emit();

  }

  backToFeatures() {
    this.router.navigate(['/home']);
  }
}
