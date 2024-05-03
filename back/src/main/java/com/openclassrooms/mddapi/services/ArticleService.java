package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.exception.NotFoundException;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ThemeService themeService;
	
//	Return list of Articles
	public List<Article>getArticles() throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
	        throw new Exception("Utilisateur non authentifié !");
	    }
		return (List<Article>) articleRepository.findAll();
	}
	
//	return one article
	public Article getArticle(Long id) {
		Optional<Article> article = articleRepository.findById(id);
		
		if(article.isEmpty()) {
			throw new NotFoundException();
		}
		return article.get();
	}
	
//	Create an Article
	public Article createArticle(ArticleRequest articleRequest) throws Exception {
		
		Theme theme = themeService.getById(articleRequest.getThemeId());
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user == null) {
	        throw new Exception("Utilisateur non authentifié !");
	    }
		
		Article article = Article.builder()
				.theme(theme)
				.user(user)
				.content(articleRequest.getContent())
				.build();
		return articleRepository.save(article);
	}

}
