import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SignUpForm} from '../shared/SignUpForm';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  API = 'http://localhost:8080';
  REGISTER_API = this.API + '/api/auth/signup';
  message: string;

  constructor(private http: HttpClient) {
  }

  save(userRegistration: SignUpForm): Observable<SignUpForm> {
    return this.http.post<SignUpForm>(this.REGISTER_API, userRegistration);
  }
}
