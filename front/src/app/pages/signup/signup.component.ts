import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {RegisterRequest} from 'src/app/_interfaces/register/register-request';
import {User} from 'src/app/_models/user/user';
import {AuthService} from 'src/app/_services/auth/auth.service';
import {SessionService} from 'src/app/_services/session/session.service';
import {AuthSuccess} from "../../_interfaces/authSuccess/authSuccess.interface";
import {MatSnackBar} from "@angular/material/snack-bar";

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
    password: ['', [Validators.required, Validators.minLength(8), /*Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_-+={}[]|;\':"/<>,.?]).{8,}$')*/]]
  });
  private isPasswordOK = false;

  //#endregion VARIABLE

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: SessionService,
              private _snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
  }

  goBack() {
    this.router.navigate(['/', '/']);
  }

  onSubmit() {
    const registerRequest = this.form.value as RegisterRequest;
    // this.validatePassword(registerRequest.password);
    if (!this.form.valid) {
      if (registerRequest.email == "" || registerRequest.password == "" || registerRequest.name == "") {
        this._snackBar.open('Formulaire non valide !', 'Fermer', {
          duration: 3000
        });
      } else if (registerRequest.password.length < 8) {
        this._snackBar.open('Mot de passe trop faible !', 'Fermer', {
          duration: 3000
        });
      }
    } else {
      this.authService.register(registerRequest).subscribe(
        (response: any) => {
          this.authService.login(registerRequest).subscribe(
            (response: AuthSuccess) => {
              localStorage.setItem('token', response.token);
              this.authService.me().subscribe((user: User) => {
                this.sessionService.logIn(user);
                this.router.navigate(['/session']);
                this._snackBar.open('Enregistrement réussi !', 'Fermer', {
                  duration: 3000
                });
              });
            }
          )
        },
        error => this.onError = true
      );
    }
  }
}
