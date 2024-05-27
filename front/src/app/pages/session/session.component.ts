import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleService} from "../../_services/article/article.service";
import {Article} from "../../_models/article/article";

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})
export class SessionComponent implements OnInit {

  //#region VARIABLE
  public onError = false;
  date: Date;
  articles: Article[];

  //#endregion VARIABLE

  constructor(
    private router: Router,
    private articleService: ArticleService,
  ) { }

  ngOnInit(): void {
    this.getArticle();
  }

  getArticle() {
    this.articleService.getArticles().subscribe(
      (response) => {
        this.articles = response;
        console.log('this.articles', this.articles)
      },
      error => this.onError = true,
    );
  }

  createArticle(){
    this.router.navigate(['/createArticle'])
  }


}
