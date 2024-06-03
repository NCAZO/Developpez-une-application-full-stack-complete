import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private pathService = environment.baseUrl;


  constructor(
    private http: HttpClient,
  ) {
  }

  getSubscriptions(): Observable<any> {
    return this.http.get<any>(`${this.pathService}getSubscriptions`);
  }

  unSubscribe(idSub: number) {
    return this.http.delete<any>(`${this.pathService}unSubscribe/` + idSub);
  }

  // isAlreadySub(){
  //   return this.http.get<any>(`${this.pathService}isAlreadySub`);
  // }
}
