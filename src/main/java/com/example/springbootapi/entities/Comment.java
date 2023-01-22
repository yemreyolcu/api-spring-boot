package com.example.springbootapi.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="comments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "post_id")
    Long post_id;

    @Column(name = "user_id")
    Long user_id;

    @Column(name = "text", length = 255)
    String text;
}
