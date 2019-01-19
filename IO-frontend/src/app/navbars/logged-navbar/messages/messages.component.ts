import {Component, Input, OnInit} from '@angular/core';
import {User} from '../../../shared/user';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

}
