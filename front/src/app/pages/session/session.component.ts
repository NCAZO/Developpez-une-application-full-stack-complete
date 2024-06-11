import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleService} from "../../_services/article/article.service";
import {Article} from "../../_models/article/article";
import {NgxSpinnerService} from "ngx-spinner";
import {catchError, finalize, throwError} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";

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
  sortBy: string = "ASCdate";

  //#endregion VARIABLE

  constructor(
    private router: Router,
    private articleService: ArticleService,
    private spinnerService: NgxSpinnerService,
    private _snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.getArticle();
  }

  getArticle() {
    this.spinnerService.show();
    this.articleService.getArticles()
      .pipe(
        catchError(error => {
          this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
          return throwError(error);
        }),
        finalize(() => this.spinnerService.hide())
      )
      .subscribe(
        (response) => {
          this.articles = response;
        },
        error => this.onError = true,
      );
  }

  createArticle() {
    this.router.navigate(['/createArticle'])
  }

  hideSideBar() {
    document.getElementById('containerSideBar').style.visibility = 'hidden';
  }

  sortArticles() {
    this.sortBy = this.sortBy === 'DESCdate' ? 'ASCdate' : 'DESCdate';

    this.articles.sort((a, b) => {
      const dateA = new Date(a.created_at);
      const dateB = new Date(b.created_at);

      if (this.sortBy === 'DESCdate') {
        return dateB.getTime() - dateA.getTime(); // Descending order
      } else {
        return dateA.getTime() - dateB.getTime(); // Ascending order
      }
    });
  }
}
