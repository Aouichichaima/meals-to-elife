import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { Stock } from 'app/shared/models/stock.model';

/**
 * @author djebby <djebby.firas@gmail.com>
 */
@Injectable({providedIn: 'root'})
export class DepotService {

  depotChanged = new Subject();
  private stocks!:Stock[];


  constructor(private http: HttpClient) {
    this.fetchStocks();
  }

  fetchStocks() {
    this.http.get<Stock[]>('http://127.0.0.1:3000/api/stocks/').subscribe(response => {
      this.stocks = response;
      this.depotChanged.next(this.stocks.slice());
    });
  }

  getStocks() {
    return this.stocks?.slice();
  }


  getStock(stockId: number) {
    const stock = this.stocks?.slice().find(stock => stock.id === stockId);
    return stock;
  }

  addStock(title: string, type_stock: string, description: string) {
   
    const newStock = {
      title,
      typeStock: type_stock,
      description
    };
    
    this.http.post('http://127.0.0.1:3000/api/stocks/', newStock)
    .subscribe(response => {
      this.fetchStocks();
    });

  }

  updateStock(id: number, title: string, type_stock: string, description: string) {
    console.log(id, title, type_stock, description);
    // const stockIdx = this.stocks.findIndex(stock => stock.id === id);
    // if(stockIdx !== -1)
    //   this.stocks[stockIdx] = {...this.stocks[stockIdx], title, type_stock, description};
    // this.depotChanged.next(this.stocks.slice());
  }

  deleteStock(stockId: number) {
    console.log(`delete stock with id ${stockId}`);
    this.http.delete(`http://127.0.0.1:3000/api/stocks/${stockId}`)
    .subscribe(response => {
      console.log(response);
      this.fetchStocks();
    });
  }

  getStockProducts(stockId: number) {
    // this.http.get(`http://127.0.0.1:3000/api/products/${stockId}`).subscribe(response => {
    //   console.log(response);
    // });
    // const products = this.stocks.find(stock => stock.id === stockId)?.products;
    // return products;
  }


  addProduct(stockId:number, nameProd:string, priceProd: number, quantityProd: number, descriptionProd: string) {
    // const stockIdx = this.stocks.findIndex(stock => stock.id === stockId);
    // this.stocks[stockIdx].products.push({
    //   id: Math.floor(Math.random()*1_000_000_000),
    //   name: nameProd,
    //   price: priceProd,
    //   quantity: quantityProd,
    //   description: descriptionProd
    // });
    // this.depotChanged.next(this.stocks.slice());
  }


  deleteProduct(stockId: number, productId: number) {
    // const stockIdx = this.stocks.findIndex(stock => stock.id === stockId);
    // // const productIdx = this.stocks[stockIdx].products.findIndex(product => product.id === productId);

    // this.stocks[stockIdx].products = this.stocks[stockIdx].products.filter(product => product.id !== productId);
    // this.depotChanged.next(this.stocks.slice());


  }

}
