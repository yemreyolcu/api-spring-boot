package com.example.springbootapi.repositories;

import com.example.springbootapi.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
