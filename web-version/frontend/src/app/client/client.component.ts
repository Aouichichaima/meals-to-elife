import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  restaurants = [{
    name: "number one",
    address:"route tabarka",
    imagePath: "https://cdn.vox-cdn.com/thumbor/L8NF6YQlRhNE9f6M4GwVyRtAAek=/0x0:2564x2564/920x613/filters:focal(1059x809:1469x1219):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/71377462/01_Cauliflower___Front.0.jpg",
    rating: 4.2
  },
  {
    name: "let's eat",
    address:"route tabarka",
    imagePath: "https://eatouteatwell.com/wp-content/uploads/2014/01/restaurant-front.jpg",
    rating: 4.5
  }
];

menu = [
  {
      "name": "repas1",
      "price": 1.5,
      "description": "description1",
      imagePath: "https://img.hellofresh.com/f_auto,fl_lossy,q_auto,w_1200/hellofresh_s3/image/saute-de-boeuf-a-lail-et-au-gingembre-f07ffc1d.jpg"
  },
  {
      "name": "repas2",
      "price": 2.5,
      "description": "description2",
      imagePath: "https://m1.quebecormedia.com/emp/rqc_prod/recettes_du_quebec-_-8098baf4f716ca7cbb973fe0c1b056529815f00f-_-TOP-15-RECETTES-AU-BBQ.jpg"
  },
  {
      "name": "repas3",
      "price": 3.5,
      "description": "description3",
      imagePath:"https://images.lecker.de/pizza-margherita-F6267101,id=30592d14,b=lecker,w=440,h=440,cg=c.jpg"

  }
];

  constructor() { }

  ngOnInit(): void {
  }

}
