import {ICardDetails} from "ng-payment-card/lib/domain/i-card-details";

export class Card {
  pan: string;
  cardHolderName: string;
  expirationMonth: String;
  expirationYear: string
  securityCode: string;
  paymentId: string;


  constructor(cc:ICardDetails, paymentId: string) {
    this.pan = cc.cardNumber;
    this.cardHolderName = cc.cardHolder;
    this.expirationMonth = cc.expirationMonth;
    this.expirationYear = cc.expirationYear;
    this.securityCode = cc.ccv.toString();
    this.paymentId = paymentId;
  }
}
