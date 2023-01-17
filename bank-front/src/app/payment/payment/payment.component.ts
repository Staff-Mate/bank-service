import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {
  method: string;

  constructor(private route: ActivatedRoute) {
    this.method = this.route.snapshot.params['method'];
  }
}
