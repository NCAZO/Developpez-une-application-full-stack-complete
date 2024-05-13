import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginRequest} from 'src/app/_interfaces/login/login-request';
import {User} from 'src/app/_models/user/user';
import {AuthService} from 'src/app/_services/auth/auth.service';
import {SessionService} from 'src/app/_services/session/session.service';
import {MatSnackBar} from "@angular/material/snack-bar";

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

  public hide = true;
  public onError = false;

  //#endregion

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
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
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe(
      (response) => {
        localStorage.setItem('token', response.token);

        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);

          this.router.navigate(['/session']);
          this._snackBar.open('Connecté', 'Fermer', {
            duration: 3000
          });
        });
      },
      error => this.onError = true,
    );
  }
}
