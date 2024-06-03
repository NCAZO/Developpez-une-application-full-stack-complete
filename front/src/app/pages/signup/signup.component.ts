import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {RegisterRequest} from 'src/app/_interfaces/register/register-request';
import {User} from 'src/app/_models/user/user';
import {AuthService} from 'src/app/_services/auth/auth.service';
import {SessionService} from 'src/app/_services/session/session.service';
import {AuthSuccess} from "../../_interfaces/authSuccess/authSuccess.interface";
import {MatSnackBar} from "@angular/material/snack-bar";
import {catchError, finalize, throwError} from "rxjs";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  //#region VARIABLE
  public onError = false;
  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    name: ['', [Validators.required]],
    password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_\-+={}[]|;\':"\/<>,.?]).{8,}$/)]]
  });
  private isPasswordOK = false;

  //#endregion VARIABLE

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService,
    private _snackBar: MatSnackBar,
    private spinnerService: NgxSpinnerService,
  ) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const registerRequest = this.form.value as RegisterRequest;
    const validationErrors = this.validateForm(registerRequest);
    if (validationErrors.length > 0) {
      this._snackBar.open(validationErrors.join('\n'), 'Fermer', {duration: 3000});
      return;
    }

    this.spinnerService.show();
    this.authService.register(registerRequest)
      .pipe(
        catchError(error => {
          this._snackBar.open('Enregistrement impossible.', 'Fermer', {duration: 3000});
          return throwError(error);
        }),
        finalize(() => this.spinnerService.hide())
      )
      .subscribe(
        (response: any) => {
          this.authService.login(registerRequest)
            .pipe(
              catchError(error => {
                this._snackBar.open('Identification impossible.', 'Fermer', {duration: 3000});
                return throwError(error);
              })
            )
            .subscribe(
              (response: AuthSuccess) => {
                localStorage.setItem('token', response.token);
                this.authService.me().subscribe((user: User) => {
                  this.sessionService.logIn(user);
                  this.router.navigate(['/session']);
                  this._snackBar.open('Enregistrement réussi !', 'Fermer', {duration: 3000});
                });
              }
            );
        }
      );
  }

  isValidEmail(email: string): boolean {
    const regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z\-0-9]+))$/;
    return regex.test(email.toLowerCase());
  }

  validateForm(registerRequest: RegisterRequest): string[] {
    const errors: any[] = [];
    if (!registerRequest.name) {
      errors.push('Nom d\'utilisateur requis');
      return errors;
    }
    if (!registerRequest.email) {
      errors.push('Email requis');
      return errors;
    } else if (!this.isValidEmail(registerRequest.email)) {
      errors.push('Email invalide');
      return errors;
    }
    if (!registerRequest.password) {
      errors.push('Mot de passe requis');
      return errors;
    } else if (registerRequest.password.length < 8) {
      errors.push('Mot de passe trop court (minimum 8 caractères)');
      return errors;
    } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])/.test(registerRequest.password)) {
      errors.push('Le mot de passe doit contenir une majuscule, une minuscule, un chiffre et un caractère spécial');
      return errors;
    }
    return errors;
  }
}
