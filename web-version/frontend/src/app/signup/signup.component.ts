import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  @ViewChild('form') signupForm!: NgForm;
  isFormValid = true;

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log("form submitted");
    const {accountType, cin, email, firstName, lastName,password, phoneNumber} = this.signupForm.value;

    if(accountType?.length === 0 || cin?.length !== 8 || email?.length < 8 || lastName?.length < 3 || firstName?.length < 3 || password?.length < 5 || phoneNumber?.length < 8 ) {
      this.isFormValid = false;
      return console.error("invalid form submittion...");
    }
    this.isFormValid = true;
    console.log("http request send with this information ... \n", accountType, cin, email, firstName, lastName,password, phoneNumber);
  }

}
