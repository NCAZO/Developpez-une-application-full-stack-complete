import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderArticlesComponent } from './header-articles.component';

describe('HeaderArticlesComponent', () => {
  let component: HeaderArticlesComponent;
  let fixture: ComponentFixture<HeaderArticlesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderArticlesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
