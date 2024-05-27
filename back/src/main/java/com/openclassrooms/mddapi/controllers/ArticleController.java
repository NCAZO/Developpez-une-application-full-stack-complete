package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getArticles")
    public ResponseEntity<List<ArticleResponse>> getArticles() {
        try {
            return ResponseEntity.ok(articleService.getArticles().getBody());
        } catch (Exception e) {
            return (ResponseEntity<List<ArticleResponse>>) ResponseEntity.notFound();
        }
    }

    @PostMapping("/createArticle")
    public ResponseEntity<Article> createArticle(@RequestBody Article articleRequest) {
        try {
            Article articleResponse = articleService.createArticle(articleRequest);
            return new ResponseEntity<>(articleResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
