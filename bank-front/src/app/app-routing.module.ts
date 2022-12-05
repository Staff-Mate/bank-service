import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomepageComponent} from "./utils/homepage/homepage.component";
import { PaymentComponent} from "./auth/signIn/payment.component"
import {EmptyComponent} from "./utils/empty/empty.component";

const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'payment',
    component: EmptyComponent,
  children:[
    // Ostavi da error bude pre :id
    // {path:"error",    component: PaymentErrorComponent}
    {path:":id",    component: PaymentComponent},
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
