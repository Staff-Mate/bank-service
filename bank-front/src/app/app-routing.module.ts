import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomepageComponent} from "./utils/homepage/homepage.component";
import {PaymentComponent} from "./payment/payment/payment.component";
import {PaymentErrorComponent} from "./payment/payment-error/payment-error.component";

const routes: Routes = [
  {path: '', component: HomepageComponent},
  {path: 'error',    component: PaymentErrorComponent},
  {path: 'payment/:method/:id',
    component: PaymentComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
