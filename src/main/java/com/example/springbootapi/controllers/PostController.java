package com.example.springbootapi.controllers;


import com.example.springbootapi.entities.Post;
import com.example.springbootapi.requests.PostCreateRequest;
import com.example.springbootapi.requests.PostUpdateRequest;
import com.example.springbootapi.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    List<Post> postListAPI(@RequestParam Optional<Long> userId) {
        return postService.postsList(userId);
    }

    @GetMapping("/post/{postId}")
    Post postRetrieveAPI(@PathVariable Long postId) {
        return postService.postRetrieve(postId);
    }

    @PostMapping("/post-create")
    Post postCreateAPI(@RequestBody PostCreateRequest requestPost) {
        return postService.postCreate(requestPost);
    }

    @PutMapping("/post/{postId}")
    Post postUpgradeAPI(@PathVariable Long postId, @RequestBody PostUpdateRequest requestPost) {
        return postService.postUpgrade(postId, requestPost);
    }

    @DeleteMapping("/post/{postId}")
    public int postDeleteAPI(@PathVariable Long postId) {
        postService.postDelete(postId);
        return 1;
    }
}
