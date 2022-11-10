import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
/**
 * @author ichraktoueiti <ichrak.touaiti@esprit.tn>
 */
@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  cartChanged=new Subject();

  private cart=[
    {
      name: "repas3",
      price: 3.5,
      quantite: 1 ,
      Total:12,
      imagePath:"image-de-repas"},


  {
    name: 'nom-de-repas1',
    price: 0.00,
    quantity: 0,
    imagePath:"image-de-repas",
    description: 'description_du_repas'
  },
  {
    name: 'nom-de-repas2',
    price: 0.00,
    quantity: 0,
    imagePath:"image-de-repas",
    description: 'description_du_repas'
  },
  {
    name: 'nom-de-repas3',
    price: 0.00,
    quantity: 0,
    imagePath:"image-de-repas",
    description: 'description_du_repas'
  },



]

getCart() {
  return this.cart.slice();
}

addToCart(name:string,
  price: number,
  quantity:number,
  imagePath:string,
  description:string){

    this.cart.push({
      name,price,quantity,imagePath,description
    })
}


    }

