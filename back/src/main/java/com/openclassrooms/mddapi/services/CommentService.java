package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsById(Long id) {
        return commentRepository.findByArticleId(id);
    }

    public Comment createNewComment(Comment commentRequest) {

        Long time = Date.from(Instant.now()).getTime();

        Comment comment = new Comment(null, commentRequest.getContent(), commentRequest.getArticleId(), commentRequest.getUser(), new Date(time));
        return commentRepository.save(comment);
    }
}
