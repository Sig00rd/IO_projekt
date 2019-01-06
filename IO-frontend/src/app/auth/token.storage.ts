import {Injectable} from '@angular/core';

@Injectable({providedIn: 'root'})
export class TokenStorage {
  private roles: Array<string> = [];

  constructor() {
  }

  private TOKEN_KEY = 'AuthToken';
  private USERNAME_KEY = 'AuthUsername';
  private AUTHORITIES_KEY = 'AuthAuthorities';

  public saveToken(token: string) {
    window.sessionStorage.removeItem(this.TOKEN_KEY);
    window.sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  public getToken() {
    return window.sessionStorage.getItem(this.TOKEN_KEY);
  }

  public saveUsername(username: string) {
    window.sessionStorage.removeItem(this.USERNAME_KEY);
    window.sessionStorage.setItem(this.USERNAME_KEY, username);
  }

  public getUsername() {
    return window.sessionStorage.getItem(this.USERNAME_KEY);
  }

  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(this.AUTHORITIES_KEY);
    window.sessionStorage.setItem(this.AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];
    if (sessionStorage.getItem(this.TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(this.AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority.authority);
      });
    }
    return this.roles;
  }

  public logout() {
    window.sessionStorage.clear();
  }

}
