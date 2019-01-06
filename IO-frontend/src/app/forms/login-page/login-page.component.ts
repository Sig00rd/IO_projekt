import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserLogin} from '../../shared/user.login';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import {LoggedUserService} from '../../services/logged.user.service';
import {Router} from '@angular/router';

// TODO add logging with facebook/google
// TODO add profile view and edit

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  @Output() loginEmitter = new EventEmitter();
  private API = 'http://localhost:8080';
  private LOGIN_API = this.API + '/api/login';

  loginForm: FormGroup;
  loginFailed = false;

  constructor(private http: HttpClient, private loggedUserService: LoggedUserService, private router: Router) {
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required)
    });
  }

  logIn(userLogin: UserLogin) {
    console.log(userLogin);
    this.http.post(this.LOGIN_API, userLogin).subscribe(
      (response: HttpEvent<Object>) => {
        this.loggedUserService.user = response['username'];
        if (this.loggedUserService.user !== '') {
          this.loggedUserService.password = this.loginForm.get('password').value; // TODO ugly
          this.loginFailed = false;
          this.loginEmitter.emit();
          this.router.navigate(['/home']);
        } else {
          this.loginFailed = true;
          this.loginForm.get('password').setValue('');
        }

      }
    );

  }

  submitForm() {
    // TODO send http request to ask if given info is correct
    const userLogin = new UserLogin(this.loginForm.get('username').value, this.loginForm.get('password').value);
    this.logIn(userLogin);

  }

}
