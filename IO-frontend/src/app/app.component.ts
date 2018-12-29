import {Component, OnChanges, SimpleChanges} from '@angular/core';
import {Router} from '@angular/router';
import {LoggedUserService} from './services/logged.user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./CrmUser.css']
})
export class AppComponent {
  constructor(private router: Router, private loggedUserService: LoggedUserService) {
  }

  logUserOut() {
    this.loggedUserService.user = null;
    this.router.navigate(['/']);
  }
}
