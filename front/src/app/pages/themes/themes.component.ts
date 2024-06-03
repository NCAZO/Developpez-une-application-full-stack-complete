import {Component, OnInit} from '@angular/core';
import {ThemeService} from "../../_services/theme/theme.service";
import {Theme} from "../../_models/theme/theme";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Subscription} from "../../_models/subscription/subscription";
import {NgxSpinnerService} from "ngx-spinner";
import {finalize} from "rxjs";

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
  ) {
  }

  ngOnInit(): void {
    this.getThemes();
    this.isAlreadySub();
  }

  getThemes() {
    this.spinnerService.show();
    this.themeService.getThemes()
      .pipe(finalize(() => this.spinnerService.hide()))
      .subscribe
      ((response) => {
          this.themes = response;
          console.log("this.themes", this.themes)
        },
        error => this.onError = true,
      );
  }

  subscribeTheme(theme: Theme) {
    this.spinnerService.show();
    this.themeService.subscribeTheme(theme.id)
      .pipe(finalize(() => this.spinnerService.hide()))
      .subscribe(
        (response: any) => {
          // this.isAlreadySub();
          this._snackBar.open('Abonnement réussi !', 'Fermer', {
            duration: 3000
          });
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
    for (let i = 0; i < this.subscribes.length; i++) {
      for (let y = 0; y < this.themes.length; y++) {
        if (this.subscribes[i].theme.id === this.themes[i].id) {
          console.log('oui');
        }
      }
    }
  }
}
