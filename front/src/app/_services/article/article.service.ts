import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {Article} from "../../_models/article/article";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private pathService = environment.baseUrl;

  constructor(
    private http: HttpClient,
  ) { }

  getArticles(): Observable<any> {
    return this.http.get<any>(`${this.pathService}getArticles`);
  }

  createArticle(body: Article) {
    return this.http.post<any>(`${this.pathService}createArticle`, body);
  }

  getArticleById(id: number) {
    return this.http.get<any>(`${this.pathService}${id}`);
  }


}
