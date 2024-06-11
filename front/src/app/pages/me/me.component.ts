import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../_services/auth/auth.service";
import {SessionService} from "../../_services/session/session.service";
import {Router} from "@angular/router";
import {MeService} from "../../_services/me/me.service";
import {User} from "../../_models/user/user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SubscriptionService} from "../../_services/subscription/subscription.service";
import {NgxSpinnerService} from "ngx-spinner";
import {catchError, finalize, throwError} from "rxjs";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  //#region VARIABLES
  public onError = false;
  currentUser: User;
  subscriptions: any[];
  public form = this.fb.group({
    name: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required,]],
    confirmPassword: ['', [Validators.required,]],
  });

  private user = this.authService.me().subscribe(
    (response: User) => {
      this.currentUser = response;
      this.form.controls['name'].setValue(response.username);
      this.form.controls['email'].setValue(response.email);
    },
    error => this.onError = true
  );

  //#endregion

  constructor(
    private fb: FormBuilder,
    private sessionService: SessionService,
    private authService: AuthService,
    private router: Router,
    private meService: MeService,
    private _snackBar: MatSnackBar,
    private subscriptionService: SubscriptionService,
    private spinnerService: NgxSpinnerService,
  ) {
  }

  ngOnInit(): void {
    this.getSubscription();
  }

  onSubmit() {
    if (this.currentUser.id === undefined) {
      this._snackBar.open('Utilisateur inconnu !', 'Fermer', {
        duration: 3000
      });
    }
    if (this.form.value.password != this.form.value.confirmPassword) {
      this._snackBar.open('Les mots de passe ne correspondent pas !', 'Fermer', {
        duration: 3000
      });
    } else {
      const updateUserRequest = this.form.value as User;
      const validationErrors = this.validatePassword(updateUserRequest);
      if (validationErrors.length > 0) {
        this._snackBar.open(validationErrors.join('\n'), 'Fermer', {duration: 3000});
        return;
      }
      updateUserRequest.id = this.currentUser.id
      this.spinnerService.show();
      this.meService.saveUserInfo(updateUserRequest)
        .pipe(
          catchError(error => {
            this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
            return throwError(error);
          }),
          finalize(() => this.spinnerService.hide())
        )
        .subscribe((updatedUser: User) => {
            this.currentUser = updatedUser;
            this._snackBar.open('Informations sauvegardées !', 'Fermer', {
              duration: 3000
            });
          },
          error => this.onError = true
        );
    }
  }

  validatePassword(updateUserRequest: User): string[] {
    const errors: any[] = [];
    if (!updateUserRequest.password) {
      errors.push('Mot de passe requis');
      return errors;
    } else if (updateUserRequest.password.length < 8) {
      errors.push('Mot de passe trop court (minimum 8 caractères)');
      return errors;
    } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])/.test(updateUserRequest.password)) {
      errors.push('Le mot de passe doit contenir une majuscule, une minuscule, un chiffre et un caractère spécial');
      return errors;
    }
    return errors;
  }

  logOut() {
    this.sessionService.logOut();
    this.router.navigate(['/'])
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
      .subscribe(
        (subscriptions: any[]) => {
          this.subscriptions = subscriptions;
        });
  }

  unSubscribe(subscription: any) {
    this.spinnerService.show();
    this.subscriptionService.unSubscribe(subscription.id)
      .pipe(
        catchError(error => {
          this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
          return throwError(error);
        }),
        finalize(() => this.spinnerService.hide())
      )
      .subscribe(
        (response: any) => {
          this.getSubscription();
        }
      )
  }

  hideSideBar() {
    document.getElementById('containerSideBar').style.visibility = 'hidden';
  }
}
