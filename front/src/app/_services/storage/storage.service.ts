import {Injectable} from '@angular/core';

const USER_KEY = "currentUser";

@Injectable({
  providedIn: 'root'
})
export class StorageService {


  constructor() {
  }

  //Save du user dans le sessionStorage
  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  //Fonction de récup de l'utilisateur depuis le sessionStorage
  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }

}
