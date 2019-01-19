import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from '../../shared/user';
import {Router} from '@angular/router';
import {TokenStorage} from '../../auth/token.storage';
import {UserService} from '../../services/user.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-logged-navbar',
  templateUrl: './logged-navbar.component.html',
  styleUrls: ['./logged-navbar.component.css']
})
export class LoggedNavbarComponent implements OnInit {
  private user: User;
  private USERS_API = 'http://localhost:8080/api/users/';
  private MESSAGES_API = 'http://localhost:8080/api/notifications/';

  userReady = false;
  @Output() private logOutEvent = new EventEmitter();

  constructor(private tokenStorage: TokenStorage, private router: Router,
              private userService: UserService, private http: HttpClient) {
  }

  ngOnInit() {
    this.userReady = false;
    this.user = new User(this.tokenStorage.getUsername());
    this.userService.user = this.user;
    this.http.get(this.USERS_API + this.tokenStorage.getUsername()).subscribe(
      data => {
        this.user.setEmail(data['email']).setDescription('Piłkarski amator')
          .setPhotoUrl('https://i.ytimg.com/vi/5-32LWcqQkU/hqdefault.jpg').setID(data['id']).setGames([]);
        this.refreshMessages();
        this.userReady = true;
      },
      error => console.log(error)
    );
  }

  refreshMessages() {
    this.http.get<string[]>(this.MESSAGES_API).subscribe(
      messages => {
        this.user.setMessages(messages);
        },
          error => console.log(error)
    );
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

  setMessageAsRead(id: number) {
    this.http.put(this.MESSAGES_API + id, null).subscribe(
      data => this.refreshMessages(),
      error => console.log(error)
    );
  }
}
