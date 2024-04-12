package com.openclassrooms.mddapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article>getAllArticle(){
		return articleRepository.getAllArticle();
	}
	
	public Article createArticle(Article createArticle) {
		return articleRepository.save(createArticle);
	}

}
