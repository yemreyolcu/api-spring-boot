package com.example.springbootapi.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="posts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="user_id")
    Long user_id;

    @Column(name="title")
    String title;

    @Column(name = "description", length = 550)
    String text;
}
