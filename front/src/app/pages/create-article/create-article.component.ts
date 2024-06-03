import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleService} from "../../_services/article/article.service";
import {Article} from "../../_models/article/article";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthService} from "../../_services/auth/auth.service";
import {StorageService} from "../../_services/storage/storage.service";
import {NgxSpinnerService} from "ngx-spinner";
import {finalize} from "rxjs";

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {

  public onError = false;

  listTheme: any[] = [];
  selectedTheme: string = "";
  content: String = "";
  title: String = "";

  constructor(
    private router: Router,
    private articleService: ArticleService,
    private _snackBar: MatSnackBar,
    private authService: AuthService,
    private storageService: StorageService,
    private spinnerService: NgxSpinnerService,
  ) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    let user = this.storageService.getUser();
    const articleRequest: Article = {
      id: null,
      content: this.content,
      title: this.title,
      user: user,
      created_at: null,
    };
    this.spinnerService.show();
    this.articleService.createArticle(articleRequest)
      .pipe(finalize(() => this.spinnerService.hide()))
      .subscribe((response) => {
          this.router.navigate(['/session'])
        this._snackBar.open('Article créé !', 'Fermer', {
          duration: 3000
        });
      },
      error => this.onError = true,
    );
  }

}
