package com.example.springbootapi.repositories;

import com.example.springbootapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
