import {Component, ElementRef, Input, OnInit, Output, ViewChild} from '@angular/core';
import {CrmUser} from '../../shared/CrmUser';
import {RegisterService} from '../../services/register.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  registerForm: FormGroup;


  constructor(private registerService: RegisterService) {
  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      'username': new FormControl(null, [Validators.required, Validators.min(3), Validators.max(10)]),
      'email': new FormControl(null, [Validators.email]),
      'password': new FormControl(null, [Validators.required]),
      'rPassword': new FormControl(null, [Validators.required])
    });
  }

  validate(password: string, rPassword: string) {
    return password === rPassword;
  }

  registerUser() {
    const username = this.registerForm.get('username').value;
    const email = this.registerForm.get('email').value;
    const password = this.registerForm.get('password').value;
    const rPassword = this.registerForm.get('rPassword').value;

    if (this.validate(password, rPassword)) {
      const newUser = new CrmUser(username, password, rPassword, email);
      console.log(newUser);
      // TODO send request for new user registration
      // this.registerService.save(newUser).subscribe(value => console.log(value));
    }

  }
}
