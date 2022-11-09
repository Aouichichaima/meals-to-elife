import { Component, Input, OnInit } from '@angular/core';
/**
 * @author ichraktoueiti <ichrak.touaiti@esprit.tn>
 */
@Component({
  selector: 'app-list-menu',
  templateUrl: './list-menu.component.html',
  styleUrls: ['./list-menu.component.css']
})
export class ListMenuComponent implements OnInit {

  @Input() menu:any;
  constructor() { }

  ngOnInit(): void {
    console.log(this.menu);
  }

  }


