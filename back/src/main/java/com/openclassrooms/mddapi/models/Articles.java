package com.openclassrooms.mddapi.models;

import java.util.List;

public class Articles {
	
	public Articles(List<Article> listArticle) {
		this.articles = listArticle;
	}
	List<Article> articles;
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	

}
