import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {GamesService} from '../../../../services/games.service';
import {GameInfo} from '../../../../shared/game.info';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  private API = 'http://localhost:8080/api/';
  private FILTER_API = this.API + 'games/filter';
  showButton = true;

  filterForm: FormGroup;

  constructor(private http: HttpClient, private gamesService: GamesService) {
  }

  ngOnInit() {
    this.filterForm = new FormGroup({
      'chosenSport': new FormControl(null),
      'minLevel': new FormControl(null),
      'pitchType': new FormControl(null),
      'players': new FormControl(null),
      'address': new FormControl(null),
      'city': new FormControl(null),
      'distance': new FormControl(null),
      'maxLevel': new FormControl(null),
      'fromDate': new FormControl(null),
      'toDate': new FormControl(null) // TODO add filter roles
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
      'chosenSport': this.filterForm.get('chosenSport').value === null ? null : (this.filterForm.get('chosenSport').value).toUpperCase(),
      'pitchType': this.filterForm.get('pitchType').value,
      'minLevel': this.filterForm.get('minLevel').value,
      'maxLevel': this.filterForm.get('maxLevel').value,
      'address': address,
      'radius': this.filterForm.get('distance').value === null ? null : +this.filterForm.get('distance').value,
      'fromDate': this.filterForm.get('fromDate').value,
      'toDate': this.filterForm.get('toDate').value,
      'minStillNeeded': this.filterForm.get('players').value === null ? null : +this.filterForm.get('players').value
    };
    request['pitchRole'] = null;
    this.showButton = true;
    this.http.post<GameInfo[]>(this.FILTER_API, request).subscribe(
      data => this.gamesService.gameList = data,
      error => console.log(error)
    );
  }

}
