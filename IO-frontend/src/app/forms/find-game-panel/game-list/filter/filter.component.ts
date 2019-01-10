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
      'chosenSport': new FormControl(''),
      'minLevel': new FormControl(''),
      'players': new FormControl(''),
      'address': new FormControl(''),
      'city': new FormControl(''),
      'distance': new FormControl(''),
      'maxLevel': new FormControl(''),
      'date': new FormControl('') // TODO add filter roles
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
    if (address !== '' && city !== '') {
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
