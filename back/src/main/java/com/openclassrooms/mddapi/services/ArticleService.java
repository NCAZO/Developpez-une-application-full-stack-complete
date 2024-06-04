package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
	
	@Autowired
	private ThemeService themeService;
    @Autowired
    private UserService userService;

    public ResponseEntity<List<ArticleResponse>> getArticles() {
        List<Article> articles = (List<Article>) articleRepository.findAll();
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            articleResponses.add(new ArticleResponse(article.getId(), article.getUser(), article.getContent(), article.getTitle(), article.getCreated_at()));
        }
        return ResponseEntity.ok(articleResponses);
    }

    public Article createArticle(Article articleRequest) {

        Long time = Date.from(Instant.now()).getTime();

        Article article = new Article(null, articleRequest.getTitle(), articleRequest.getContent(), new Date(time), articleRequest.getUser());
        return articleRepository.save(article);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

}
