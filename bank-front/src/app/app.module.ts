import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgPaymentCardModule } from 'ng-payment-card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {PaymentComponent} from "./auth/signIn/payment.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { EmptyComponent } from './utils/empty/empty.component';

@NgModule({
  declarations: [
    AppComponent,
    PaymentComponent,
    EmptyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgPaymentCardModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
