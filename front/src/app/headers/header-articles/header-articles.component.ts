import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-articles',
  templateUrl: './header-articles.component.html',
  styleUrls: ['./header-articles.component.scss']
})
export class HeaderArticlesComponent implements OnInit {

  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  goBack() {
    this.router.navigate(['/', '/']);
  }

}
