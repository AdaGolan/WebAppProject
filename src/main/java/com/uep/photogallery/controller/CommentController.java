package com.uep.photogallery.controller;

import com.uep.photogallery.model.Comment;
import com.uep.photogallery.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/photos/{photoId}/comments")
    public ResponseEntity<Comment> createComment(
            @PathVariable Long photoId,
            @RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(photoId, comment.getContent());
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping("/photos/{photoId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPhoto(@PathVariable Long photoId) {
        List<Comment> comments = commentService.getCommentsByPhoto(photoId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @RequestBody Comment comment) {
        try {
            Comment updatedComment = commentService.updateComment(id, comment.getContent());
            return ResponseEntity.ok(updatedComment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 