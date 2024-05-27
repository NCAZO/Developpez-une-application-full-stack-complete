import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './pages/home/home.component';
import {LoginComponent} from './pages/login/login.component';
import {SignupComponent} from './pages/signup/signup.component';
import {SessionComponent} from './pages/session/session.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FooterComponent} from './footer/footer.component';
import {CreateArticleComponent} from './pages/create-article/create-article.component';
import {MatSelectModule} from '@angular/material/select';
import {HeaderAuthComponent} from './headers/header-auth/header-auth.component';
import {HeaderSessionComponent} from './headers/header-session/header-session.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {JwtInterceptor} from './_interceptor/jwt.interceptor';
import {MeComponent} from './pages/me/me.component';
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {ThemesComponent} from './pages/themes/themes.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    SessionComponent,
    FooterComponent,
    CreateArticleComponent,
    HeaderAuthComponent,
    HeaderSessionComponent,
    MeComponent,
    ThemesComponent,
  ],

  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    HttpClientModule,
    MatProgressSpinnerModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },

    MatSnackBar,
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
