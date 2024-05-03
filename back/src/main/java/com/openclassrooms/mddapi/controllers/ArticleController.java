package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.services.ArticleService;


@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/getArticles")
	public List<Article> getArticles() throws Exception {
		return articleService.getArticles();
	}
	
	@GetMapping("/article/{id}")
	public Article getArticle(Long id) {
		return articleService.getArticle(id);
	}
	
	@PostMapping("/createArticle")
	public Article createArticle(@RequestBody ArticleRequest articleRequest) throws Exception {
		return articleService.createArticle(articleRequest);
	}
}
