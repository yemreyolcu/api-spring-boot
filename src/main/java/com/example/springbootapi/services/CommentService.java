package com.example.springbootapi.services;


import com.example.springbootapi.entities.Comment;
import com.example.springbootapi.entities.Post;
import com.example.springbootapi.entities.User;
import com.example.springbootapi.repositories.CommentRepository;
import com.example.springbootapi.requests.CommentCreateRequest;
import com.example.springbootapi.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> commentsList(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent())
            return commentRepository.findByPost_Id(postId.get());
        else if (userId.isPresent())
            return commentRepository.findByUser_Id(userId.get());
        else if (userId.isPresent() && postId.isPresent())
            return commentRepository.findByPost_IdAndUser_Id(userId.get(), postId.get());
        return commentRepository.findAll();
    }

    public Comment commentRetrieve(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment commentCreate(CommentCreateRequest commentRequest) {
        User commentUser = userService.userRetrieve(commentRequest.getUser_id());
        Post commentPost = postService.postRetrieve(commentRequest.getPost_id());
        if (commentUser != null && commentPost != null) {
            Comment createdComment = new Comment();
            createdComment.setId(commentRequest.getId());
            createdComment.setText(commentRequest.getText());
            createdComment.setUser(commentUser);
            createdComment.setPost(commentPost);
            return commentRepository.save(createdComment);
        } else return null;
    }

    public Comment commentUpgrade(Long commentId, CommentUpdateRequest commentRequest) {
        Optional<Comment> upgradedComment = commentRepository.findById(commentId);
        if (upgradedComment.isPresent()) {
            Comment upgComment = upgradedComment.get();
            upgComment.setText(commentRequest.getText());
            return commentRepository.save(upgComment);
        } else return null;
    }

    public void commentDelete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
