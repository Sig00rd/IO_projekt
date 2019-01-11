import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from '../../shared/user';
import {Router} from '@angular/router';
import {TokenStorage} from '../../auth/token.storage';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-logged-navbar',
  templateUrl: './logged-navbar.component.html',
  styleUrls: ['./logged-navbar.component.css']
})
export class LoggedNavbarComponent implements OnInit {
  private user: User;
  @Output() private logOutEvent = new EventEmitter();

  constructor(private tokenStorage: TokenStorage, private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.user = new User(this.tokenStorage.getUsername(), 'tomeczek@tomek.com',
      'totalny kozak', 'https://i.ytimg.com/vi/5-32LWcqQkU/hqdefault.jpg', []); // TODO get proper email
    this.userService.user = this.user;
  }


  logOutUser() {
    this.tokenStorage.logout();
    window.location.reload();

  }

  backToFeatures() {
    this.router.navigate(['/home']);
  }

  showUserInfo() {
    this.router.navigate(['/user']);
  }
}
