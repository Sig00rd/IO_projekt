import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserLogin} from '../../shared/user.login';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {TokenStorage} from '../../auth/token.storage';
import {JwtResponse} from '../../auth/jwt.response';

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
  private LOGIN_API = this.API + '/api/auth/signin';

  loginForm: FormGroup;
  loginFailed = false;

  constructor(private http: HttpClient, private router: Router, private tokenStorage: TokenStorage) {
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required)
    });
  }

  // logIn(userLogin: UserLogin) {
  //   console.log(userLogin);
  //   this.http.post(this.LOGIN_API, userLogin).subscribe(
  //     (response: HttpEvent<Object>) => {
  //       this.loggedUserService.user = response['username'];
  //       if (this.loggedUserService.user !== '') {
  //         this.loginFailed = false;
  //         this.loginEmitter.emit();
  //         this.router.navigate(['/home']);
  //       } else {
  //         this.loginFailed = true;
  //         this.loginForm.get('password').setValue('');
  //       }
  //
  //     }
  //   );
  //
  // }

  logIn(userLogin: UserLogin) {
    this.http.post<JwtResponse>(this.LOGIN_API, userLogin).subscribe(
        data => {
          this.tokenStorage.saveToken(data.accessToken);
          this.tokenStorage.saveUsername(data.username);
          this.tokenStorage.saveAuthorities(data.authorities);

          this.loginFailed = false;
          this.loginEmitter.emit();
          this.router.navigate(['/home']);

      },
      error => {
        this.loginFailed = true;
        this.loginForm.get('password').setValue('');

      }
    );

  }

  submitForm() {
    // TODO send http request to ask if given info is correct
    const userLogin = new UserLogin(this.loginForm.get('username').value, this.loginForm.get('password').value);
    this.logIn(userLogin);

  }

}
