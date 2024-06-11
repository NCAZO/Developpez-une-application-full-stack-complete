import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginRequest} from 'src/app/_interfaces/login/login-request';
import {AuthService} from 'src/app/_services/auth/auth.service';
import {SessionService} from 'src/app/_services/session/session.service';
import {MatSnackBar} from "@angular/material/snack-bar";
import {User} from "../../_models/user/user";
import {StorageService} from "../../_services/storage/storage.service";
import {NgxSpinnerService} from "ngx-spinner";
import {catchError, finalize, throwError} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  //#region VARIABLES
  public form = this.fb.group({
    name: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });
  public onError = false;

  //#endregion

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private sessionService: SessionService,
    private _snackBar: MatSnackBar,
    private storageService: StorageService,
    private spinnerService: NgxSpinnerService,
  ) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const loginRequest = this.form.value as LoginRequest;
    const validationErrors = this.validateForm(loginRequest);
    if (validationErrors.length > 0) {
      this._snackBar.open(validationErrors.join('\n'), 'Fermer', {duration: 3000});
      return;
    }
    this.spinnerService.show();
    this.authService.login(loginRequest)
      .pipe(
        catchError(error => {
          this._snackBar.open(error.error.message, 'Fermer', {duration: 3000});
          return throwError(error);
        }),
        finalize(() => this.spinnerService.hide())
      )
      .subscribe(
        (response) => {
          localStorage.setItem('token', response.token);
          this.authService.me()
            .subscribe((user: User) => {
              this.sessionService.logIn(user);
              this.storageService.saveUser(user);
              this.router.navigate(['/session']);
              this._snackBar.open('Connecté', 'Fermer', {
                duration: 3000
              });
            });
        },
        error => this.onError = true,
      );
  }

  private validateForm(loginRequest: LoginRequest): string[] {
    const errors: any[] = [];
    if (!loginRequest.name) {
      errors.push("Veuillez renseignez votre identifiant");
      return errors;
    }
    if (!loginRequest.password) {
      errors.push("Veuillez renseignez votre mot de passe");
      return errors;
    }
    return errors;
  }
}
