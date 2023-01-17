import { Component } from '@angular/core';
import {HttpService} from "../../services/http.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-qr-payment',
  templateUrl: './qr-payment.component.html',
  styleUrls: ['../styles/payment.component.css']
})
export class QrPaymentComponent {

  id: string;

  constructor(private httpService: HttpService, private route: ActivatedRoute) {
    this.id = this.route.snapshot.params['id']
  }


  onButton() {
    this.httpService.getQRRedirectionUrl(this.id).subscribe({
      next: response =>{
        window.location.href = response.body;
      },
      error: err =>{
        window.location.href = err.error;
      }
  });
  }
}
