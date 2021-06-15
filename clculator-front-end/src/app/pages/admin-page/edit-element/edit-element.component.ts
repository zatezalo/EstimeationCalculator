import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MyElement } from 'src/app/model/element.model';
import { EstimationService } from 'src/app/services/estemation/estimation.service';

@Component({
  selector: 'app-edit-element',
  templateUrl: './edit-element.component.html',
  styleUrls: ['./edit-element.component.css']
})
export class EditElementComponent implements OnInit {

  public editElementForm: FormGroup;
  element: MyElement = new MyElement;
  elementId: number = 0;

  constructor(private route: ActivatedRoute, private router: Router,private formBuilder: FormBuilder, private estimationService: EstimationService,) {
    this.editElementForm = this.formBuilder.group({
      name: [''],
      image: [''],
      price: ['']
    })
   }

  ngOnInit(): void {
    this.elementId = this.route.snapshot.params['id'];

    this.estimationService.getElement(this.elementId).subscribe(el => {
      //console.log(el);
      this.element.name = el.name;
      this.element.image = el.image;
      this.element.price = el.price;
      //console.log(this.element);
    })
  }

  submitForm(formValue) {
    if(formValue.name == "") {
      formValue.name = this.element.name
    }
    if(formValue.image == "") {
      formValue.image = this.element.image
    }
    if(formValue.price == "") {
      formValue.price = this.element.price
    }
    this.estimationService.editElement(formValue, this.elementId).subscribe(
      (returnObject: string) => {
        this.router.navigate(['/estemaion'])
      },
      (error: HttpErrorResponse) => {
        //this.errorMsg = error.error.massage;
      }
    )
  }

}
