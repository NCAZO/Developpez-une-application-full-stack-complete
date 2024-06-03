import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private pathService = environment.baseUrl;

  constructor(
    private http: HttpClient,
  ) {
  }

  getThemes() {
    return this.http.get<any>(`${this.pathService}getThemes`);
  }

  subscribeTheme(idTheme: number) {
    return this.http.post<any>(`${this.pathService}subscribeTheme/` + idTheme, "");
  }

}
