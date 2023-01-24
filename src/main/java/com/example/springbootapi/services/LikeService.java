package com.example.springbootapi.services;


import com.example.springbootapi.entities.Like;
import com.example.springbootapi.entities.Post;
import com.example.springbootapi.entities.User;
import com.example.springbootapi.repositories.LikeRepository;
import com.example.springbootapi.requests.LikeCreateRequest;
import com.example.springbootapi.requests.LikeUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> likesList(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent())
            return likeRepository.findByPost_IdAndUser_Id(postId.get(), userId.get());
        else if (postId.isPresent())
            return likeRepository.findByPost_Id(postId.get());
        else if (userId.isPresent())
            return likeRepository.findByUser_Id(userId.get());
        return likeRepository.findAll();
    }

    public Like likeCreate(LikeCreateRequest likeCreateRequest) {
        User likeUser = userService.userRetrieve(likeCreateRequest.getUser_id());
        Post likePost = postService.postRetrieve(likeCreateRequest.getPost_id());

        if (likeUser != null && likePost != null) {
            Like createdLike = new Like();
            createdLike.setId(likeCreateRequest.getId());
            createdLike.setUser(likeUser);
            createdLike.setPost(likePost);
            createdLike.setStatus(likeCreateRequest.isStatus());
            return likeRepository.save(createdLike);
        } return null;
    }

    public Like likeRetrieve(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like likeUpgrade(Long likeId, LikeUpdateRequest likeUpdateRequest) {
        Optional<Like> upgradedLike = likeRepository.findById(likeId);
        if (upgradedLike.isPresent()) {
            Like upgLike = upgradedLike.get();
            upgLike.setStatus(likeUpdateRequest.isStatus());
            return likeRepository.save(upgLike);
        } return null;
    }

    public void likeDelete(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
