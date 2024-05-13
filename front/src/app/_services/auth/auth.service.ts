import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from 'src/app/_interfaces/register/register-request';
import { Observable } from 'rxjs';
import { AuthSuccess } from 'src/app/_interfaces/authSuccess/authSuccess.interface';
import { LoginRequest } from 'src/app/_interfaces/login/login-request';
import { User } from 'src/app/_models/user/user';
import { environment } from 'src/environments/environment';
import { RegisterSuccess } from 'src/app/_interfaces/registerSuccess/register-success';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = environment.baseUrl + 'auth';

  constructor(
    private httpClient: HttpClient,
    ) { }

  public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.httpClient.post<any>(`${this.pathService}/login`, loginRequest);
  }

  public register(registerRequest: RegisterRequest): Observable<RegisterSuccess> {
    return this.httpClient.post<RegisterSuccess>(`${this.pathService}/register`, registerRequest);
  }

  public me(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }
}
