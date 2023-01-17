import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgPaymentCardModule } from 'ng-payment-card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {CardPaymentComponent} from "./payment/card-payment/card-payment.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { EmptyComponent } from './utils/empty/empty.component';
import { HeaderComponent } from './utils/header/header.component';
import { QrPaymentComponent } from './payment/qr-payment/qr-payment.component';
import { PaymentComponent } from './payment/payment/payment.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import { PaymentErrorComponent } from './payment/payment-error/payment-error.component';

@NgModule({
  declarations: [
    AppComponent,
    CardPaymentComponent,
    EmptyComponent,
    HeaderComponent,
    QrPaymentComponent,
    PaymentComponent,
    PaymentErrorComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgPaymentCardModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatButtonModule


    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
