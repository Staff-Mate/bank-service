import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ICardDetails} from "ng-payment-card/lib/domain/i-card-details";
import {ActivatedRoute} from "@angular/router";
import {Card} from "../dto/card.dto";
import {HttpService} from "../../services/http.service";

@Component({
  selector: 'app-card-payment',
  templateUrl: './card-payment.component.html',
  styleUrls:['../styles/payment.component.css']
})
export class CardPaymentComponent {
  constructor(private httpService: HttpService,private route: ActivatedRoute) {
  }

  onSave(cc: ICardDetails) {
    let card = new Card(cc, this.route.snapshot.params['id']);
    this.httpService.getCardRedirectionUrl(card).subscribe({
      next: response =>{
        window.location.href = response.body;
      },
      error: err =>{
        // console.log(err)

        window.location.href = err.error.text;
      }
    })
  }
}
