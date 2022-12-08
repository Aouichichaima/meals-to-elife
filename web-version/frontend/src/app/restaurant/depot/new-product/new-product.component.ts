import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DepotService } from '../depot.service';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  @ViewChild('f', {static: false}) productForm!: NgForm;
  stocks!:any;
  isEditMode = false;
  productId!: number;
  product!: any;
  constructor(private route: ActivatedRoute, private depotService: DepotService) { }

  ngOnInit(): void {
    this.depotService.depotChanged.subscribe((updateDepot) => {
      this.stocks = updateDepot;
      if(this.isEditMode)
        this.product = this.depotService.getProductById(this.productId);
      
    });

    this.stocks = this.depotService.getStocks();
    if(this.route.snapshot.params['id'] != null) {
      this.isEditMode = true;
      this.productId = +this.route.snapshot.params['id'];
      this.product = this.depotService.getProductById(this.productId);
      
      setTimeout(()=>{
        this.productForm?.setValue({
          name: this.product?.name,
          price: this.product?.price,
          quantity: this.product?.quantity,
          description: this.product?.description,
          stockId: this.product?.stockId,
        });
      }, 30);

    }

  }

  onSubmitProduct(form: NgForm) {

    if(!this.productForm.valid) {
      alert('formulaire invalid...');
      return;
    }

    const { name, price, quantity, description, stockId } = form.value;

    if(this.isEditMode)
      this.depotService.updatedProduct(this.productId, +stockId, name, price, quantity, description);
    else
      this.depotService.addProduct(+stockId, name, price, quantity, description);

   
    
  }

}
