import {Component, ElementRef, Input, OnInit, Output, ViewChild} from '@angular/core';
import {CrmUser} from '../../shared/CrmUser';
import {RegisterService} from '../../services/register.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpEvent, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  registerForm: FormGroup;
  invalidForm = false;
  userExists: boolean;
  passwordMatch: boolean;
  userRegistered = false;

  constructor(private registerService: RegisterService) {
  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      'username': new FormControl(null, [Validators.required, Validators.min(3), Validators.max(10)]), // TODO accept only letters / numbers
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [Validators.required, Validators.min(5)]),
      'rPassword': new FormControl(null, [Validators.required])
    });
    this.userExists = false;
    this.passwordMatch = true;
  }

  validate(password: string, rPassword: string) {
    return password === rPassword;
  }

  registerUser() {
    const username = this.registerForm.get('username').value;
    const email = this.registerForm.get('email').value;
    const password = this.registerForm.get('password').value;
    const rPassword = this.registerForm.get('rPassword').value;

    this.passwordMatch = this.validate(password, rPassword);

    if (this.invalidForm = this.registerForm.invalid) {
      const newUser = new CrmUser(username, password, rPassword, email);
      console.log(newUser);
      this.registerService.save(newUser).subscribe((response: CrmUser) => {
        if (response['userName'] == null) {
          this.userExists = true;
          this.invalidForm = true;
        } else {
          this.userExists = false;
          this.userRegistered = true;
        }
      });
    }
  }

}
