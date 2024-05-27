import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../_services/auth/auth.service";
import {SessionService} from "../../_services/session/session.service";
import {Router} from "@angular/router";
import {MeService} from "../../_services/me/me.service";
import {User} from "../../_models/user/user";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SubscriptionService} from "../../_services/subscription/subscription.service";

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
  ) {
  }

  ngOnInit(): void {
    this.getSubscription();
  }

  onSubmit() {
    if (this.currentUser.id !== undefined) {
      if (this.form.value.password === this.form.value.confirmPassword) {
        const updateUserRequest = this.form.value as User;
        updateUserRequest.id = this.currentUser.id;
        this.meService.saveUserInfo(updateUserRequest).subscribe((updatedUser: User) => {
            this.currentUser = updatedUser;
            console.log('currentUser', this.currentUser);
            this._snackBar.open('Informations sauvegardées !', 'Fermer', {
              duration: 3000
            });
          },
          error => this.onError = true
        );
      } else {
        this._snackBar.open('Les mots de passe ne correspondent pas !', 'Fermer', {
          duration: 3000
        });
      }
    } else {
      this._snackBar.open('Utilisateur inconnu !', 'Fermer', {
        duration: 3000
      });
    }
  }

  logOut() {
    this.sessionService.logOut();
    this.router.navigate(['/'])
  }

  getSubscription() {
    this.subscriptionService.getSubscriptions().subscribe(
      (subscriptions: any[]) => {
        this.subscriptions = subscriptions;
        console.log('this.subscriptions', this.subscriptions);
      });
  }

  unSubscribe(subscription: any) {
    this.subscriptionService.unSubscribe(subscription.id).subscribe(
      (response: any) => {
        this.getSubscription();
        console.log('res', response);
      }
    )
  }
}
