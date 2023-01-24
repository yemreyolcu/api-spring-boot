package com.example.springbootapi.controllers;


import com.example.springbootapi.entities.Like;
import com.example.springbootapi.requests.LikeCreateRequest;
import com.example.springbootapi.requests.LikeUpdateRequest;
import com.example.springbootapi.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/")
    public List<Like> likesListAPI(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        return likeService.likesList(postId, userId);
    }


    @GetMapping("/like/{likeId}")
    public Like likeRetrieve(@PathVariable Long likeId) {
        return likeService.likeRetrieve(likeId);
    }

    @PostMapping("/like-create")
    public Like likeCreateAPI(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.likeCreate(likeCreateRequest);
    }

    @PutMapping("/like/{likeId}")
    public Like likeUpgradeAPI(@PathVariable Long likeId, @RequestBody LikeUpdateRequest likeUpdateRequest) {
        return likeService.likeUpgrade(likeId, likeUpdateRequest);
    }

    @DeleteMapping("/like/{likeId}")
    public void likeDeleteAPI(@PathVariable Long likeId) {
        likeService.likeDelete(likeId);
    }
}
