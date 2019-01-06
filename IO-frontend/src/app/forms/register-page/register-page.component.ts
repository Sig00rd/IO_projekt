import {Component, OnInit} from '@angular/core';
import {SignUpForm} from '../../shared/SignUpForm';
import {RegisterService} from '../../services/register.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  registerForm: FormGroup;
  invalidForm = false;
  userExists: boolean;
  emailExist: boolean;
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
    this.invalidForm = this.registerForm.invalid;
    if (!this.invalidForm) {
      const newUser = new SignUpForm(username, password, rPassword, email);
      console.log(newUser);
      this.registerService.save(newUser).subscribe(
        data => {
          this.userExists = false;
          this.userRegistered = true;
        },
        error => {
          if (error.error.message === 'Fail -> Username is already taken!') {
            this.userExists = true;
            this.invalidForm = true;
          } else if (error.error.message === 'Fail -> Email is already in use!') {
            this.emailExist = true;
            this.invalidForm = true;
          }
        }
      );
    }
  }

}
