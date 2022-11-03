import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-depot',
  templateUrl: './depot.component.html',
  styleUrls: ['./depot.component.css']
})
export class DepotComponent implements OnInit {
  stocks = [
    {
      id: 1,
      title: 'stocks-title-n°01',
      type_stock: 'type_stock_n°01',
      description: 'description_de_stock_n°01'
    },
    {
      id: 2,
      title: 'stocks-title-n°02',
      type_stock: 'type_stock_n°02',
      description: 'description_de_stock_n°02'
    },
    {
      id: 3,
      title: 'stocks-title-n°03',
      type_stock: 'type_stock_n°03',
      description: 'description_de_stock_n°03'
    },
    {
      id: 4,
      title: 'stocks-title-n°04',
      type_stock: 'type_stock_n°04',
      description: 'description_de_stock_n°04'
    },
    {
      id: 5,
      title: 'stocks-title-n°05',
      type_stock: 'type_stock_n°05',
      description: 'description_de_stock_n°05'
    },
  ];

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

  onSelectStock(id: number) {
    console.log('send request to the backend and get the list of products within the stock with id ' + id);
    // set the new values of stock_products after fetching them from the database...
  }

}
