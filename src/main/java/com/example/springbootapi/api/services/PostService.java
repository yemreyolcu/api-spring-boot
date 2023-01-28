package com.example.springbootapi.api.services;

import com.example.springbootapi.api.repositories.PostRepository;
import com.example.springbootapi.api.entities.Post;
import com.example.springbootapi.api.entities.User;
import com.example.springbootapi.api.requests.PostCreateRequest;
import com.example.springbootapi.api.requests.PostUpdateRequest;
import com.example.springbootapi.api.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> postsList(Optional<Long> userId) {
        List<Post> myList;
        if (userId.isPresent())
            myList = this.postRepository.findByUserId(userId.get());
        else
            myList = this.postRepository.findAll();
        return myList.stream().map(PostResponse::new).collect(Collectors.toList());
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
