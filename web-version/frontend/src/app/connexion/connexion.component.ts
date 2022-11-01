import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  constructor( private router: Router) { }

  ngOnInit(): void {
  }

  
  goToRegister(){
    this.router.navigate(["/inscription"])
  }


  goToConnexion(){
    this.router.navigate(["/connexion"])
  }
  
}
