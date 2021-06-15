import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MyElement } from 'src/app/model/element.model';
import { Pdf } from 'src/app/model/pdf.model';
import { EstimationService } from 'src/app/services/estemation/estimation.service';

@Component({
  selector: 'app-estemation',
  templateUrl: './estemation.component.html',
  styleUrls: ['./estemation.component.css']
})
export class EstemationComponent implements OnInit {
  elements: MyElement[] = [];
  pdfElements: MyElement[] = [];
  pdfSum: number = 0;
  public addElementForm: FormGroup;
  public createPdfForm: FormGroup;

  constructor(private estimationService: EstimationService, private formBuilder: FormBuilder) {
    this.addElementForm = this.formBuilder.group({
      description: ['']
    });
    this.createPdfForm = this.formBuilder.group({
      text: ['']
    })
   }
  
  ngOnInit(): void {
    this.estimationService.getElements().subscribe(elements => {
      this.elements = elements;
      //console.log(elements);
    })
  }

  addElement(el: MyElement, formValue) {

    let elementToAdd: MyElement = new MyElement();
    elementToAdd.id = Date.now();
    elementToAdd.name = el.name;
    elementToAdd.image = el.image;
    elementToAdd.price = el.price;
    elementToAdd.description = formValue.description;
    console.log(elementToAdd)
    this.pdfSum = this.pdfSum + elementToAdd.price;
    //this.pdfSum = this.pdfSum;
    this.pdfElements.push(elementToAdd);
  }

  pdfCreate(formValue) {
    let pdf: Pdf = new Pdf();
    pdf.elements = this.pdfElements;
    pdf.sum = parseFloat(this.pdfSum.toFixed(2));
    pdf.text = formValue.text;
    console.log(pdf);
    this.estimationService.createPDF(pdf).subscribe(
      (returnObject) => {
        //location.reload();
        //console.log(returnObject);
      },
      (error: HttpErrorResponse) => {
        console.log("ne radi");
        //this.errorMsg = error.error.massage;
      }
    );
  }

}
