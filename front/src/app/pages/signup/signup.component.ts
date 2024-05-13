import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from 'src/app/_interfaces/register/register-request';
import { User } from 'src/app/_models/user/user';
import { AuthService } from 'src/app/_services/auth/auth.service';
import { SessionService } from 'src/app/_services/session/session.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    name: ['', [Validators.required, Validators.min(3)]],
    password: ['', [Validators.required, Validators.min(3)]]
  });

  constructor(private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService) { }

  ngOnInit(): void {
  }

  goBack() {
    this.router.navigate(['/', '/']);
  }

  onSubmit() {
    // const registerRequest = this.form.value as RegisterRequest;
    // console.log('registerRequest', registerRequest);

    // this.authService.register(registerRequest)
    // .subscribe(ret => {
    //   console.log('ret', ret);
    //   // this.router.navigate(['/login'])
    // })

    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(
      (response: any) => {
        console.log('response', response)
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
