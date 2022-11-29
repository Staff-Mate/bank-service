import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'homepage-root',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  constructor(
    private _router: Router
  ) { }
  btnClick() {
    this._router.navigate(['signin']);
    }
}