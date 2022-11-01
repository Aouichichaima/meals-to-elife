import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-nav-admin',
  templateUrl: './nav-admin.component.html',
  styleUrls: ['./nav-admin.component.css']
})
export class NavAdminComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit(): void {
  }
  goToHome(){
    this.router.navigate(["/home"])
  }


  goToGestion(){
    this.router.navigate(['/gestion'])
  }



  goToDashboard(){
    this.router.navigate(['/dashboard'])
  }


  goToContact(){
    this.router.navigate(['/send'])
  }

  goToSettings(){
    this.router.navigate(["/profile"])
  }
}
