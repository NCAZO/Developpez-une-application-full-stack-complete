import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(
    private http: HttpClient,
  ) { }

  getArticles(): Observable<any> {
    const url = environment.baseUrl += 'getArticles';
    return this.http.get<any>(url);
  }
}
