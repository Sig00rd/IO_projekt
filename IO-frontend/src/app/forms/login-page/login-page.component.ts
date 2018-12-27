import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserLogin} from '../../shared/user.login';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import {LoggedUserService} from '../../services/logged.user.service';

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

  constructor(private http: HttpClient, private loggedUserService: LoggedUserService) {
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
        console.log(response);
        this.loggedUserService.user = response['username'];
        console.log(this.loggedUserService.user + ' logged service user');
        this.loginEmitter.emit();
      }
  );

  }

  submitForm() {
    // TODO send http request to ask if given info is correct
    const userLogin = new UserLogin(this.loginForm.get('username').value, this.loginForm.get('password').value);
    this.logIn(userLogin);

  }

}
