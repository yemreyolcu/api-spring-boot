package com.example.springbootapi.api.repositories;

import com.example.springbootapi.api.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost_Id(Long post_id);

    List<Comment> findByUser_Id(Long user_id);

    List<Comment> findByPost_IdAndUser_Id(Long post_id, Long user_id);
}
