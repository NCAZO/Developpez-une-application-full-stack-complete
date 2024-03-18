import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {

  listTheme: any[] = [];
  selectedTheme: string = "";

  constructor(
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  goBack(){
    this.router.navigate(['/session'])

  }

  onSubmit(){

  }

}
