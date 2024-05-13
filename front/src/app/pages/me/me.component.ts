import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../_services/auth/auth.service";
import {SessionService} from "../../_services/session/session.service";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  //#region VARIABLES
  public form = this.fb.group({
    login: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]]
  });
  //#endregion



  constructor(
    private fb: FormBuilder,
    private sessionService: SessionService,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    let user = this.authService.me();
    user.subscribe(ret => {
      console.log(ret)
      this.form.controls['login'].setValue(ret.username);
      this.form.controls['email'].setValue(ret.email);
    })
  }

  onSubmit(){
    //TODO Sauvegarde des nouvelles infos current user
  }

  logOut(){
    this.sessionService.logOut();
  }

}
