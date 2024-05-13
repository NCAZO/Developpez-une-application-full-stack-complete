import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-session',
  templateUrl: './header-session.component.html',
  styleUrls: ['./header-session.component.scss']
})
export class HeaderSessionComponent implements OnInit {

  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  goBack() {
    // this.router.navigate(['/', '/session']);
    window.history.back();
  }

  me() {
    this.router.navigate(['/me'])
  }

  goArticle(){
    this.router.navigate(['/session']);
  }

  goTheme(){
    this.router.navigate(['/themes']);
  }
}
