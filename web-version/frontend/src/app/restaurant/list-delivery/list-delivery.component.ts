import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
/**
 * @author Aouichichaima <chaima.aouichi@esprit.tn>
 */
@Component({
  selector: 'app-list-delivery',
  templateUrl: './list-delivery.component.html',
  styleUrls: ['./list-delivery.component.css']
})
export class ListDeliveryComponent implements OnInit {
  route: any;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  goToDetails(){
    this.router.navigate(["/restaurant/delivery-details"])
  }
}
