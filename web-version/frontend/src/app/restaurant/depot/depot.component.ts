import { Component, OnInit } from '@angular/core';
import { DepotService } from './depot.service';

/**
 * @author djebby <djebby.firas@gmail.com>
 */
@Component({
  selector: 'app-depot',
  templateUrl: './depot.component.html',
  styleUrls: ['./depot.component.css']
})
export class DepotComponent implements OnInit {
  
  stocks!:any;
  
  constructor(private depotService: DepotService) { }

  ngOnInit(): void {

    this.depotService.depotChanged.subscribe((updateDepot) => {
      this.stocks = updateDepot;
    });

    this.stocks = this.depotService.getStocks();
    
  }

  onDeleteStock(stockId: number) {
    this.depotService.deleteStock(stockId);
  }

}
