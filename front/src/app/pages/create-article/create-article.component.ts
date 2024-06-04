import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleService} from "../../_services/article/article.service";
import {Article} from "../../_models/article/article";
import {MatSnackBar} from "@angular/material/snack-bar";
import {StorageService} from "../../_services/storage/storage.service";
import {NgxSpinnerService} from "ngx-spinner";
import {finalize} from "rxjs";
import {ThemeService} from "../../_services/theme/theme.service";

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {

  public onError = false;

  themes: any[] = [];
  selectedTheme: string = "";
  content: String = "";
  title: String = "";

  constructor(
    private router: Router,
    private articleService: ArticleService,
    private _snackBar: MatSnackBar,
    private storageService: StorageService,
    private spinnerService: NgxSpinnerService,
    private themeService: ThemeService,
  ) {
  }

  ngOnInit(): void {
    this.getThemes();
  }

  getThemes() {
    this.spinnerService.show();
    this.themeService.getThemes()
      .pipe((finalize(() => this.spinnerService.hide())))
      .subscribe(
        (response) => {
          this.themes = response;
        }
      )
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

  hideSideBar() {
    document.getElementById('containerSideBar').style.visibility = 'hidden';
  }
}
