import { Component, OnInit } from '@angular/core';
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
    login: ['', [Validators.required, Validators.min(3)]],
    email: ['', [Validators.required, Validators.email]]
  });
  //#endregion



  constructor(
    private fb: FormBuilder,
    private sessionService: SessionService,
  ) { }

  ngOnInit(): void {
  }

  onSubmit(){

  }

  logOut(){
    this.sessionService.logOut();
  }

}
