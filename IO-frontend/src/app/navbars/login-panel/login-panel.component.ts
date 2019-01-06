import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-panel',
  templateUrl: './login-panel.component.html',
  styleUrls: ['./login-panel.component.css']
})
export class LoginPanelComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  switchToLogin() {
    this.router.navigate(['/login']);
  }

  switchToRegister() {
    this.router.navigate(['/register']);
  }


}
