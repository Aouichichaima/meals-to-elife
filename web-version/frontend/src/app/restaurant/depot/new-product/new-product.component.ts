import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DepotService } from '../depot.service';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  @ViewChild('f', {static: false}) productForm!: NgForm;
  stocks!:any;
  constructor(private depotService: DepotService) { }

  ngOnInit(): void {
    this.stocks = this.depotService.getStocks();
  }

  onAddProduct(form: NgForm) {
    const { name, price, quantity, description, stockId } = form.value;
    this.depotService.addProduct(+stockId, name, price, quantity, description);
  }

}
