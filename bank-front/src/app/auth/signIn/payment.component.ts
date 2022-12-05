import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormControl} from "@angular/forms";
import {ICardDetails} from "ng-payment-card/lib/domain/i-card-details";
import {ActivatedRoute} from "@angular/router";

export class Card {
  pan: string;
  cardHolderName: string;
  expirationMonth: String;
  expirationYear: string
  securityCode: string;
  paymentId: string;


  constructor(pan: string, cardHolderName: string, expirationMonth: String, expirationYear: string, securityCode: string, paymentId: string) {
    this.pan = pan;
    this.cardHolderName = cardHolderName;
    this.expirationMonth = expirationMonth;
    this.expirationYear = expirationYear;
    this.securityCode = securityCode;
    this.paymentId = paymentId;
  }
}

@Component({
  selector: 'signin-root',
  templateUrl: './payment.component.html',
  styleUrls:['./payment.component.css']
})
export class PaymentComponent {
  constructor(private _http: HttpClient, private route: ActivatedRoute) {
  }

  onSave(cc: ICardDetails) {
    console.log(cc)
    let card = new Card(cc.cardNumber, cc.cardHolder, cc.expirationMonth, cc.expirationYear,cc.ccv.toString(), this.route.snapshot.params['id']);
    console.log(card)
    this._http.post<{headers:{},body:string,statusCodeValue:number,statusCode:string}>("http://localhost:8080/accounts/", card).subscribe((response)=>{
      console.log(response)
      window.location.href = response.body
    })
  }
}
