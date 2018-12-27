import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CrmUser} from '../shared/CrmUser';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  API = 'http://localhost:8080';
  REGISTER_API = this.API + '/api/register';

  constructor(private http: HttpClient) {
  }

  save(userRegistration: CrmUser): Observable<CrmUser> {
    return this.http.post<CrmUser>(this.REGISTER_API, userRegistration);
  }
}
