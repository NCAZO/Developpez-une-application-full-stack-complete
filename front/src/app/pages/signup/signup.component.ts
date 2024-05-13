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

  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    name: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

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
        //localStorage.setItem('token', response.token);
        // this.authService.me().subscribe((user: User) => {
        //   console.log('user', user)
        //   this.sessionService.logIn(user);
        //   this.router.navigate(['/session'])
        // });
      },
      error => this.onError = true
    );
  }

}
