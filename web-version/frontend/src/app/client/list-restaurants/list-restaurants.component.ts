import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-restaurants',
  templateUrl: './list-restaurants.component.html',
  styleUrls: ['./list-restaurants.component.css']
})
export class ListRestaurantsComponent implements OnInit {

  @Input() restaurant:any;

  constructor() { }

  ngOnInit(): void {
    console.log(this.restaurant);
  }

}
