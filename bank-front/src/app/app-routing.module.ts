import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomepageComponent} from "./utils/homepage/homepage.component";
import { SigninComponent} from "./auth/signIn/signin.component"

const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'signin', component: SigninComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
