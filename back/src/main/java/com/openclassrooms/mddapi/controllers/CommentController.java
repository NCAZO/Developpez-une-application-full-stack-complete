package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/getComments")
    public ResponseEntity<List<Comment>> getComments() {
        try {
            return ResponseEntity.ok(commentService.getComments().getBody());
        } catch (Exception e) {
            return (ResponseEntity<List<Comment>>) ResponseEntity.notFound();
        }
    }
}
