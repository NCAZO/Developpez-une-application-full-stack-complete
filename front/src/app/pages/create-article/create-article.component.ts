import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleService} from "../../_services/article/article.service";
import {Article} from "../../_models/article/article";
import {MatSnackBar} from "@angular/material/snack-bar";
import {StorageService} from "../../_services/storage/storage.service";
import {NgxSpinnerService} from "ngx-spinner";
import {catchError, finalize, throwError} from "rxjs";
import {ThemeService} from "../../_services/theme/theme.service";
import {Theme} from "../../_models/theme/theme";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {

  public form = this.formBuilder.group({
    title: ['', [Validators.required]],
    content: ['', [Validators.required]],
    selectValueTheme: ['', [Validators.required]],
  });

  public onError = false;

  themes: Theme[] = [];
  selectedTheme: Theme;

  constructor(
    private router: Router,
    private articleService: ArticleService,
    private formBuilder: FormBuilder,
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
      .pipe(
        catchError(error => {
          this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
          return throwError(error);
        }),
        finalize(() => this.spinnerService.hide())
      )
      .subscribe(
        (response) => {
          this.themes = response;
        }
      )
  }

  onSubmit() {
    let user = this.storageService.getUser();
    this.selectedTheme = this.themes.find(them => them.id = parseInt(this.form.get("selectValueTheme").value));
    const articleRequest: Article = {
      id: null,
      title: this.form.value.title,
      content: this.form.value.content,
      theme: this.selectedTheme,
      created_at: null,
      user: user,
    };
    if (this.form.value.title == "" || this.form.value.content == "") {
      this._snackBar.open('Veuillez remplir tous les champs !', 'Fermer', {
        duration: 3000,
      })
    } else {
      this.spinnerService.show();
      this.articleService.createArticle(articleRequest)
        .pipe(
          catchError(error => {
            this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
            return throwError(error);
          }),
          finalize(() => this.spinnerService.hide())
        )
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

  hideSideBar() {
    document.getElementById('containerSideBar').style.visibility = 'hidden';
  }
}
