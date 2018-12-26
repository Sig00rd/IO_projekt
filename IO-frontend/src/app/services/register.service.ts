import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CrmUser} from '../shared/CrmUser';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  API = 'http://localhost:8080';
  REGISTER_API = this.API + '/register/api';

  constructor(private http: HttpClient) {

  }

  getUser(): Observable<any> {
    return this.http.get(this.REGISTER_API);
  }

  get(name: string) {
    return this.http.get(this.REGISTER_API + '/' + name);
  }

  save(userRegistration: CrmUser): Observable<CrmUser> {
    return this.http.post<CrmUser>(this.REGISTER_API, userRegistration);
  }
}
