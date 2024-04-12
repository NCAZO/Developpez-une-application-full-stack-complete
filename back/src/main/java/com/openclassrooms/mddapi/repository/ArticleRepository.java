package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	
	List<Article> getAllArticle();
}
