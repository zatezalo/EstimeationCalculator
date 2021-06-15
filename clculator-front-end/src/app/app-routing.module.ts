import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddElementComponent } from './pages/admin-page/add-element/add-element.component';
import { EditElementComponent } from './pages/admin-page/edit-element/edit-element.component';
import { EstemationComponent } from './pages/estemation-page/estemation/estemation.component';
import { LogInComponent } from './pages/log_in_page/log-in/log-in.component';

const routes: Routes = [
  {path: "", component: LogInComponent},
  {path: "estemaion", component: EstemationComponent},
  {path: "addElement", component: AddElementComponent},
  {path: "editElement/:id", component: EditElementComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
