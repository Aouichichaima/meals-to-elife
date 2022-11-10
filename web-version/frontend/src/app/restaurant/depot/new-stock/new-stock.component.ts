import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { DepotService } from '../depot.service';

@Component({
  selector: 'app-new-stock',
  templateUrl: './new-stock.component.html',
  styleUrls: ['./new-stock.component.css']
})
export class NewStockComponent implements OnInit {
  @ViewChild('f', {static: false}) stockForm!: NgForm;
  isEditMode = false;
  stockId!: number;
  stock!:any;
  constructor(private route: ActivatedRoute, private depotService: DepotService) { }





 

  ngOnInit(): void {
    if(this.route.snapshot.params['id'] != null) {
      this.isEditMode = true;
      this.stockId = +this.route.snapshot.params['id'];
      this.stock = this.depotService.getStock(this.stockId);
      
      setTimeout(()=>{
        this.stockForm?.setValue({
          title: this.stock.title,
          stockType: this.stock.type_stock,
          description: this.stock.description,
        });
      }, 30);

    }

    this.route.params.subscribe(
      (params: Params) => {
        this.isEditMode = params['id'] != null;
        if(this.isEditMode) {
          this.stockId = +params['id'];
          this.stock = this.depotService.getStock(this.stockId);
          this.stockForm?.setValue({
            title: this.stock.title,
            stockType: this.stock.type_stock,
            description: this.stock.description,
          });
        }
      }
    );
  }


  onAddStock(form: NgForm) {

    if(!this.stockForm.valid) {
      alert('formulaire invalid...');
      return;
    }

    const {title, description, stockType} = form.value;
    if(this.isEditMode) {
      this.depotService.updateStock(this.stockId, title, stockType, description);
      return;
    } else {
      this.depotService.addStock(title, stockType, description);
    }
  }

}
