import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginRequest} from 'src/app/_interfaces/login/login-request';
import {User} from 'src/app/_models/user/user';
import {ArticleService} from 'src/app/_services/article/article.service';
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
    name: ['', /*[Validators.required, Validators.email || Validators.name]*/],
    password: ['', [Validators.required, Validators.min(3)]]
  });

  public hide = true;
  public onError = false;

  //#endregion

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private sessionService: SessionService,
    private _snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
  }

  goBack() {
    this.router.navigate(['/', '/']);
  }

  onSubmit() {
    const loginRequest = this.form.value as LoginRequest;
    console.log(loginRequest)
    this.authService.login(loginRequest).subscribe(
      (response) => {
        console.log('response', response);
        localStorage.setItem('token', response.token);

        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          console.log('user', user);

          this.router.navigate(['/session']);
        });
        // this.router.navigate(['/session']);
      },
      error => this.onError = true,
    );
  }
}
