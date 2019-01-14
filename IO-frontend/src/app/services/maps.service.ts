import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable()
export class MapsService {
  private API = 'https://maps.googleapis.com/maps/api/geocode/json?';
  private API_KEY = 'AIzaSyDTcMb9tsqsJSfWymNME76U1Zze1bORsSg';

  public location: object = {lat: 0, lng: 0};

  constructor(private http: HttpClient) {

  }

  getLocation(address: string, city: string): Observable<Object> {
    const params = new HttpParams().set('address', address.split(' ').join('+') + '+' + city).set('key', this.API_KEY);
    return this.http.get(this.API, {params: params});
  }
}
