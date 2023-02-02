import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Card} from "../payment/dto/card.dto";
import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";

@Injectable({providedIn: 'root'})
export class HttpService{
  constructor(private _http: HttpClient) {
  }

  getCardRedirectionUrl(card: Card){
    return this._http.post<{headers:{},body:string,statusCodeValue:number,statusCode:string}>(environment.host + ":8080/accounts/", card);
  }

  getQRRedirectionUrl(id: string) {
    let card = new Card({cardNumber: '5501828726630806',cardHolder:'John Doe',expirationMonth:'07',expirationYear:'2023',ccv:518},id);
    return this._http.post<{headers:{},body:string,statusCodeValue:number,statusCode:string}>(environment.host + ":8080/accounts/", card);
  }
}
