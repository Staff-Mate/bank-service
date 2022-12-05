import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormControl} from "@angular/forms";
import {ICardDetails} from "ng-payment-card/lib/domain/i-card-details";

export class Card {
  pan: string;
  cardHolderName: string;
  expirationDate: Date;
  securityCode: string;
  paymentId: string;


  constructor(pan: string, cardHolderName: string, expirationDate: Date, securityCode: string, paymentId: string) {
    this.pan = pan;
    this.cardHolderName = cardHolderName;
    this.expirationDate = expirationDate;
    this.securityCode = securityCode;
    this.paymentId = paymentId;
  }
}

@Component({
  selector: 'signin-root',
  templateUrl: './signin.component.html',
  styleUrls:['./signin.component.css']
})
export class SigninComponent {
  constructor(private _http: HttpClient) {
  }

  onSave(cc: ICardDetails) {
    console.log(cc)
    let card = new Card(cc.cardNumber, cc.cardHolder, new Date(),cc.ccv.toString(), '7117622270');
    console.log(card)
    this._http.post("http://localhost:8080/accounts/", card).subscribe((response)=>{
      console.log(response)
    })
  }
}
