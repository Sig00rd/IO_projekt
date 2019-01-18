import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormControl, FormGroup} from '@angular/forms';
import {SportObject} from '../../shared/sport.object';

@Component({
  selector: 'app-sport-object',
  templateUrl: './sport-object.component.html',
  styleUrls: ['./sport-object.component.css']
})
export class SportObjectComponent implements OnInit {
  private API = 'http://localhost:8080';
  private SPORTOBJECTS_API = this.API + '/api/sportObjects';

  @Output() addedObjectEmitter = new EventEmitter<void>();

  sportObjectForm: FormGroup;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.sportObjectForm = new FormGroup({
      'name': new FormControl(null),
      'address': new FormControl(null),
      'city': new FormControl(null),
      'type': new FormControl(null)
    });
  }


  onSubmit() {
    const sportObject = new SportObject(this.sportObjectForm.get('address').value, this.sportObjectForm.get('city').value,
      this.sportObjectForm.get('name').value, this.sportObjectForm.get('type').value);
    this.http.post(this.SPORTOBJECTS_API, sportObject).subscribe(
      data => this.addedObjectEmitter.emit(),
      error => console.log(error)
    );
  }

}
