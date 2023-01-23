package com.example.springbootapi.services;

import com.example.springbootapi.entities.Post;
import com.example.springbootapi.entities.User;
import com.example.springbootapi.repositories.PostRepository;
import com.example.springbootapi.requests.PostCreateRequest;
import com.example.springbootapi.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> postsList(Optional<Long> userId) {
        if (userId.isPresent())
            return this.postRepository.findByUserId(userId.get());
        return this.postRepository.findAll();
    }

    public Post postRetrieve(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post postCreate(PostCreateRequest requestPost) {
        User user = userService.userRetrieve(requestPost.getUser_id());
        if (user == null)
            return null;
        Post createdPost = new Post();
        createdPost.setId(requestPost.getId());
        createdPost.setDescription(requestPost.getDescription());
        createdPost.setTitle(requestPost.getTitle());
        createdPost.setUser(user);
        return postRepository.save(createdPost);
    }

    public Post postUpgrade(Long postId, PostUpdateRequest requestPost) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isPresent()) {
            Post updatedPost = foundPost.get();
            updatedPost.setDescription(requestPost.getDescription());
            updatedPost.setTitle(requestPost.getTitle());
            postRepository.save(updatedPost);
            return updatedPost;
        } else return null;
    }

    public void postDelete(Long postId) {
        postRepository.deleteById(postId);
    }
}
