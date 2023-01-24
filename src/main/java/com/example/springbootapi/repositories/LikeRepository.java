package com.example.springbootapi.repositories;

import com.example.springbootapi.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByPost_IdAndUser_Id(Long post_id, Long user_id);

    List<Like> findByPost_Id(Long post_id);

    List<Like> findByUser_Id(Long user_id);
}
