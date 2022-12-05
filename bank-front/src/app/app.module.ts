import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgPaymentCardModule } from 'ng-payment-card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {SigninComponent} from "./auth/signIn/signin.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent
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
