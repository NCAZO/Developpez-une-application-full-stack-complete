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

  menuToggle() {
    const toggleMenu = document.querySelector('.menu');
    toggleMenu.classList.toggle('active');
    console.log('toggleMenu', toggleMenu)
  }

  toggleSideBar() {
    document.getElementById('containerSideBar').style.visibility = 'visible';
  }
}
