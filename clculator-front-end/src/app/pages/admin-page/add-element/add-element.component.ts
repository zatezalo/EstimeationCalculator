import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EstimationService } from 'src/app/services/estemation/estimation.service';

@Component({
  selector: 'app-add-element',
  templateUrl: './add-element.component.html',
  styleUrls: ['./add-element.component.css']
})
export class AddElementComponent implements OnInit {
  public addElementForm: FormGroup;

  constructor(private router: Router,private formBuilder: FormBuilder, private estimationService: EstimationService,) {
    this.addElementForm = this.formBuilder.group({
      name: ['', Validators.required],
      image: ['', Validators.required],
      price: ['', Validators.required]
    })
   }

  ngOnInit(): void {
  }

  public get name() {
    return this.addElementForm.get('name')
  }

  public get image() {
    return this.addElementForm.get('image')
  }

  public get price() {
    return this.addElementForm.get('price')
  }

  submitForm(formValue) {
    console.log(formValue);
    this.estimationService.addElement(formValue).subscribe(
      (returnObject: string) => {
        this.router.navigate(['/estemaion'])
      },
      (error: HttpErrorResponse) => {
        //this.errorMsg = error.error.massage;
      }
    )
  }

}
