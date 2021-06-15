import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MyElement } from 'src/app/model/element.model';
import { Pdf } from 'src/app/model/pdf.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Authorization': 'Bearer ' + localStorage.getItem('jwt')
  })
}

@Injectable({
  providedIn: 'root'
})
export class EstimationService {
  

  private readonly baseUrl = 'http://localhost:8080/api/element';

  constructor(private http: HttpClient) { }

  getElements(): Observable<MyElement[]> {
    return this.http.get<MyElement[]>(this.baseUrl, httpOptions);
  }

  getElement(id: number): Observable<MyElement> {
    //console.log(id);
    return this.http.get<MyElement>(this.baseUrl + '/' + id, httpOptions);
  }

  createPDF(pdf: Pdf) {
    //console.log(pdf);
    return this.http.post(this.baseUrl + '/createPdf', {
      elements: pdf.elements,
      sum: pdf.sum,
      description: pdf.text
    }, httpOptions);
  }
  
  addElement(formValue) {
    return this.http.post(this.baseUrl + '/addElement', {
      name: formValue.name,
      price: formValue.price,
      image: formValue.image
    }, httpOptions);
  }

  editElement(formValue: any, id: number) {
    console.log(this.baseUrl + '/' + id + '/editElement')
    return this.http.put(this.baseUrl + '/' + id + '/editElement', {
      name: formValue.name,
      price: formValue.price,
      image: formValue.image
    }, httpOptions);
  }
}
