import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { from } from 'rxjs';
import { LogInCredentials } from 'src/app/model/log-in-credentials.model';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  public loginForm: FormGroup;
  errorMsg:string = "";
  
  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6), Validators.pattern('.*[0-9].*')]]
    })
  }

  ngOnInit(): void {
  }

  public get username() {
    return this.loginForm.get('username')
  }

  public get password() {
    return this.loginForm.get('password')
  }

  public submitForm(credentials: LogInCredentials) {
    localStorage.setItem("jwt", "");
    
    this.authService.login(credentials).subscribe(data => {
      this.router.navigate(['/estemaion'])
    },
    (error: HttpErrorResponse) => {
      this.errorMsg = error.error.massage;
    });
  }
}
