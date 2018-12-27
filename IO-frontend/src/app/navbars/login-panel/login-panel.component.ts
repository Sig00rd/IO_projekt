import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LoggedUserService} from '../../services/logged.user.service';

@Component({
  selector: 'app-login-panel',
  templateUrl: './login-panel.component.html',
  styleUrls: ['./login-panel.component.css']
})
export class LoginPanelComponent implements OnInit {
  @Output() loginEmitter = new EventEmitter();
  @Output() registerEmitter = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  switchToLogin() {
    this.loginEmitter.emit();
  }

  switchToRegister() {
    this.registerEmitter.emit();
  }


}
