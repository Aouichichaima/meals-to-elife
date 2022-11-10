import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { DepotService } from '../depot.service';

/**
 * @author djebby <djebby.firas@gmail.com>
 */
@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent implements OnInit {
  stock_products!:any;
  stockId!:number;
  stock: any;

  constructor(private route: ActivatedRoute, private depotService: DepotService) { }

  ngOnInit(): void {

    this.depotService.depotChanged.subscribe(
      updatedStock => {
        this.stock = this.depotService.getStock(this.stockId);
        this.stock_products = this.stock?.products;
      }
    );

    this.route.params.subscribe(
      (params: Params) => {
        this.stockId = +params['id'];
        this.stock = this.depotService.getStock(this.stockId);
        this.stock_products = this.stock.products;
      }
    )
  }

  

}
