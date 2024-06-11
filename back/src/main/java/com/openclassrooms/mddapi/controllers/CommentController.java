package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/getComments/{id}")
    public ResponseEntity<List<Comment>> getCommentsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(commentService.getCommentsById(id));
        } catch (Exception e) {
            return (ResponseEntity<List<Comment>>) ResponseEntity.notFound();
        }
    }

    @PostMapping("/createNewComment")
    public ResponseEntity<Comment> createNewComment(@RequestBody Comment commentRequest) {
        try {
            return ResponseEntity.ok(commentService.createNewComment(commentRequest));
        } catch (Exception e) {
            return (ResponseEntity<Comment>) ResponseEntity.notFound();
        }
    }
}
