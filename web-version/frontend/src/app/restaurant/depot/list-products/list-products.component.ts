import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent implements OnInit {
  stock_products = [
    {
      id: 1,
      name: 'nom_du_produit_n°01',
      price: 0.00,
      quantity: 0,
      description: 'description_du_produit_n°01'
    },
    {
      id: 2,
      name: 'nom_du_produit_n°02',
      price: 0.00,
      quantity: 0,
      description: 'description_du_produit_n°02'
    },
    {
      id: 3,
      name: 'nom_du_produit_n°03',
      price: 0.00,
      quantity: 0,
      description: 'description_du_produit_n°03'
    },
    {
      id: 4,
      name: 'nom_du_produit_n°04',
      price: 0.00,
      quantity: 0,
      description: 'description_du_produit_n°04'
    },
    {
      id: 5,
      name: 'nom_du_produit_n°05',
      price: 0.00,
      quantity: 0,
      description: 'description_du_produit_n°05'
    },
    {
      id: 6,
      name: 'nom_du_produit_n°06',
      price: 0.00,
      quantity: 0,
      description: 'description_du_produit_n°06'
    },
  ];
  constructor() { }

  ngOnInit(): void {
  }

}
