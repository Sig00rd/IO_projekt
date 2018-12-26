import {Component, OnChanges, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./CrmUser.css']
})
export class AppComponent implements OnChanges {
  showLogin = false;
  showRegister = false;
  isUserLogged = false;
  userIsPlayer = false;
  userIsHost = false;
  showFeatures = false;

  ngOnChanges(changes: SimpleChanges): void {
    this.showFeatures = (this.userIsHost || this.userIsPlayer);
  }

  setLogin() {
    if (this.showRegister) {
      this.showRegister = false;
    }
    this.showLogin = true;
  }


  setRegister() {
    if (this.showLogin) {
      this.showLogin = false;
    }
    this.showRegister = true;
  }

  logUserIn() {
    this.isUserLogged = true;
    this.showRegister = false;
    this.showLogin = false;
    this.showFeatures = true;
  }

  logUserOut() {
    this.resetView();
    this.showFeatures = false;
    this.isUserLogged = false;
  }

  chooseFeature(feature: string) {
    if (feature === 'player') {
      this.userIsPlayer = true;
    } else if (feature === 'host') {
      this.userIsHost = true;
    }
    this.showFeatures = false;
  }

  resetView() {
    this.showFeatures = true;
    this.userIsHost = false;
    this.userIsPlayer = false;
  }
}
