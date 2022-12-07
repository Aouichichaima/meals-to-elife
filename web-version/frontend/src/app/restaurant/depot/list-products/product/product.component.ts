import { Component, Input, OnInit } from '@angular/core';
import { DepotService } from '../../depot.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() product:any;
  @Input() stockId!:number;

  constructor(private depotService: DepotService) { }

  ngOnInit(): void {
  }

  onDeleteProduct(productId: number) {
    this.depotService.deleteProduct(this.stockId, +productId);
  }

}
