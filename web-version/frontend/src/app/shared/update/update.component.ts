import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  @ViewChild('form', {static: false}) form!: NgForm;
  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.form.value);
  }
  submitted(): void {

}
}
