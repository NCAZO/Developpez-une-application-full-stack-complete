import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {User} from "../../_models/user/user";

@Injectable({
  providedIn: 'root'
})
export class MeService {

  private pathService = environment.baseUrl + 'users';

  constructor(
    private http: HttpClient,
  ) {
  }

  saveUserInfo(body: User) {
    return this.http.post<User>(`${this.pathService}/saveUserInfo`, body);
  }

}
