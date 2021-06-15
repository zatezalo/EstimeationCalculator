import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogInComponent } from './pages/log_in_page/log-in/log-in.component';
import { EstemationComponent } from './pages/estemation-page/estemation/estemation.component';
import { AddElementComponent } from './pages/admin-page/add-element/add-element.component';
import { EditElementComponent } from './pages/admin-page/edit-element/edit-element.component';

@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    EstemationComponent,
    AddElementComponent,
    EditElementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
