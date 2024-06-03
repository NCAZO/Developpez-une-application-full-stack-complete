import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header-session',
  templateUrl: './header-session.component.html',
  styleUrls: ['./header-session.component.scss']
})
export class HeaderSessionComponent implements OnInit {

  constructor(
    private router: Router,
  ) {
  }

  ngOnInit(): void {
  }

  me() {
    this.router.navigate(['/me'])
  }

  goArticle() {
    this.router.navigate(['/session']);
  }

  goTheme() {
    this.router.navigate(['/themes']);
  }

  menuToggle() {
    const toggleMenu = document.querySelector('.menu');
    toggleMenu.classList.toggle('active');
    console.log('toggleMenu', toggleMenu)
  }

  toggleSideBar() {
    document.getElementById('containerSideBar').style.visibility = 'visible';
  }
}
