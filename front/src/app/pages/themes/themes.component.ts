import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../_services/theme/theme.service";
import {Theme} from "../../_models/theme/theme";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Subscription} from "../../_models/subscription/subscription";
import {NgxSpinnerService} from "ngx-spinner";
import {catchError, finalize, throwError} from "rxjs";
import {StorageService} from "../../_services/storage/storage.service";
import {SubscriptionService} from "../../_services/subscription/subscription.service";

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {

  //#region VARIABLE
  themes: Theme[] = [];
  subscribes: Subscription[] = [];
  public onError = false;

  //#endregion VARIABLE

  constructor(
    private themeService: ThemeService,
    private _snackBar: MatSnackBar,
    private spinnerService: NgxSpinnerService,
    private storageService: StorageService,
    private subscriptionService: SubscriptionService,
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
      .subscribe
      ((response) => {
          this.themes = response;
          this.getSubscription();
        },
        error => this.onError = true,
      );
  }

  getSubscription() {
    this.spinnerService.show();
    this.subscriptionService.getSubscriptions()
      .pipe(
        catchError(error => {
          this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
          return throwError(error);
        }),
        finalize(() => this.spinnerService.hide())
      )
      .subscribe
      ((response) => {
          this.subscribes = response;
          this.isAlreadySub();
        },
        error => this.onError = true,
      );
  }

  subscribeTheme(theme: Theme) {
    this.spinnerService.show();
    this.themeService.subscribeTheme(theme.id)
      .pipe(
        catchError(error => {
          this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
          return throwError(error);
        }),
        finalize(() => this.spinnerService.hide())
      )
      .subscribe(
        (response: any) => {
          this._snackBar.open('Abonnement réussi !', 'Fermer', {
            duration: 3000
          });
          this.getThemes();
        },
        (error: any) => {
          this._snackBar.open('Abonnement échoué !', 'Fermer', {
            duration: 3000
          });
        }
      );
  }

  hideSideBar() {
    document.getElementById('containerSideBar').style.visibility = 'hidden';
  }

  private isAlreadySub() {
    let user = this.storageService.getUser();
    let userSubs: Theme[] = [];
    this.themes.forEach((theme) => {
      this.subscribes.forEach((sub) => {
        if (user.id === sub.idUser && sub.theme.id === theme.id) {
          userSubs.push(theme);
          theme.isSubscribed = true;
        }
      });
    });
  }
}
