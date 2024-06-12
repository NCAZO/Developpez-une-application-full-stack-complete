import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {LoginComponent} from './pages/login/login.component';
import {SignupComponent} from './pages/signup/signup.component';
import {SessionComponent} from './pages/session/session.component';
import {CreateArticleComponent} from './pages/create-article/create-article.component';
import {MeComponent} from "./pages/me/me.component";
import {AuthGuard} from "./_guards/auth.guard";
import {ThemesComponent} from "./pages/themes/themes.component";
import {CommentComponent} from "./pages/comment/comment.component";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'session', component: SessionComponent, canActivate: [AuthGuard]},
  {path: 'createArticle', component: CreateArticleComponent, canActivate: [AuthGuard]},
  {path: 'me', component: MeComponent, canActivate: [AuthGuard]},
  {path: 'themes', component: ThemesComponent, canActivate: [AuthGuard]},
  {path: 'comment/:id', component: CommentComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
