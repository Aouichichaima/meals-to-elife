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
    
    const updatedStock = {
      id,
      title,
      typeStock: type_stock,
      description
    };

    this.http.put('http://127.0.0.1:3000/api/stocks/', updatedStock).subscribe(response => {
      this.fetchStocks();
    });
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

  getProductById(id: number) {

    if(this.stocks)
      for(const stock of this.stocks) {
        for(const product of stock.Products) {
          if(product.id == id) return product;
        }
      }

  }

  updatedProduct(prodId: number, stockId:number, nameProd:string, priceProd: number, quantityProd: number, descriptionProd: string) {
    const updatedProduct = {
      id: prodId,
      name: nameProd,
      price: priceProd,
      quantity: quantityProd,
      description: descriptionProd,
      stockId: stockId
    };
  
    this.http.put('http://127.0.0.1:3000/api/products', updatedProduct).subscribe(response => {
      console.log(response);
      this.fetchStocks();
    });
  }

  addProduct(stockId:number, nameProd:string, priceProd: number, quantityProd: number, descriptionProd: string) {

    const newProduct = {
      name: nameProd,
      price: priceProd,
      quantity: quantityProd,
      description: descriptionProd,
      stockId: +stockId
    };
    this.http.post('http://127.0.0.1:3000/api/products', newProduct).subscribe(response => {
      console.log(response);
      this.fetchStocks();
    });
  }


  deleteProduct(stockId: number, productId: number) {

    console.log(stockId, productId);
    this.http.delete('http://127.0.0.1:3000/api/products/'+productId).subscribe(response => {
      console.log(response);
      this.fetchStocks();
    });

  }

}
