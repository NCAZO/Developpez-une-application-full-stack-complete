import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import {AuthService} from "../_services/auth/auth.service";
import {SessionService} from "../_services/session/session.service";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(
        private router: Router,
        private sessionService: SessionService,
    ) { }

  public canActivate(): boolean {
      console.log('this.sessionService.isLogged', this.sessionService.isLogged);
    if (this.sessionService.isLogged) {
      //this.router.navigate(['session']);
      return true;
    }
    else{
      this.router.navigate(['login']);
      return false;
    }
  }
}
