import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  private API = 'http://localhost:8080/api/'; // TODO add it
  private FILTER_API = ''; // TODO add it as well
  showButton = true;

  filterForm: FormGroup;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.filterForm = new FormGroup({
      'chosenSport': new FormControl(null),
      'minLevel': new FormControl(null),
      'players': new FormControl(null),
      'address': new FormControl(null),
      'city': new FormControl(null),
      'distance': new FormControl(null),
      'maxLevel': new FormControl(null),
      'date': new FormControl(null) // TODO add filter roles
    });
  }

  onButtonClick() {
    this.showButton = false;
  }

  cancelForm() {
    this.filterForm.reset();
    this.showButton = true;
  }

  submitFilter() {
    let address = this.filterForm.get('address').value;
    const city = this.filterForm.get('city').value;
    if (address !== null && city !== null) {
      address = address + ', ' + city;
    }
    const request = {
      'discipline': (this.filterForm.get('chosenSport').value).toUpperCase(),
      'minLevel': this.filterForm.get('minLevel').value,
      'maxLevel': this.filterForm.get('maxLevel').value,
      'address': address,
      'distance': this.filterForm.get('distance').value,
      'date': this.filterForm.get('date').value,
      'needed': this.filterForm.get('players').value
    };
    console.log(request);
    this.showButton = true;
    // TODO add http request for filter
  }

}
