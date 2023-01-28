package com.example.springbootapi.api.controllers;


import com.example.springbootapi.api.entities.Comment;
import com.example.springbootapi.api.requests.CommentCreateRequest;
import com.example.springbootapi.api.requests.CommentUpdateRequest;
import com.example.springbootapi.api.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/")
    public List<Comment> commentsListAPI(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        return commentService.commentsList(postId, userId);
    }

    @GetMapping("/comment/{commentId}")
    public Comment commentRetrieveAPI(@PathVariable Long commentId) {
        return commentService.commentRetrieve(commentId);
    }

    @PostMapping("/comment-create")
    public Comment commentCreateAPI(@RequestBody CommentCreateRequest commentRequest) {
        return commentService.commentCreate(commentRequest);
    }

    @PutMapping("/comment/{commentId}")
    public Comment commentUpgradeAPI(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentRequest) {
        return commentService.commentUpgrade(commentId, commentRequest);
    }

    @DeleteMapping("/comment/{commentId}")
    public void commentDeleteAPI(@PathVariable Long commentId) {
        commentService.commentDelete(commentId);
    }
}
