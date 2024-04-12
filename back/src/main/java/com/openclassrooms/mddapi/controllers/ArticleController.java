package com.openclassrooms.mddapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.Article;

@RestController
public class ArticleController {

	@PostMapping("/article")
	public ResponseEntity<Article> createArticle(@RequestBody Article article) {
		return null;
		
	}
	
}
