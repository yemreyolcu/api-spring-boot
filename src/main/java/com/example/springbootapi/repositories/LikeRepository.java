package com.example.springbootapi.repositories;

import com.example.springbootapi.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
