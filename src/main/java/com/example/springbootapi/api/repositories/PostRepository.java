package com.example.springbootapi.api.repositories;

import com.example.springbootapi.api.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findByUserId(Long userId);
}
