package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findByArticleId(Long articleId);
}
