import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserLogin} from '../../shared/user.login';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  @Output() loginEmitter = new EventEmitter();

  loginForm: FormGroup;

  constructor() {
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required)
    });
  }

  logIn() {
    this.loginEmitter.emit();
  }

  submitForm() {
    // TODO send http request to ask if given info is correct
    const userLogin = new UserLogin(this.loginForm.get('username').value, this.loginForm.get('password').value);
    console.log(userLogin);
    this.logIn();

  }

}
