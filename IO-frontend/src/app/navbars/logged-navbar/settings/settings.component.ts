import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  editUserForm: FormGroup;

  constructor() { }

  ngOnInit() {
    this.editUserForm = new FormGroup({
      'email' : new FormControl(null),
      'password' : new FormControl(null),
      'rPassword' : new FormControl(null),
      'description' : new FormControl(null)
    });
  }

}
