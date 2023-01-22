package com.example.springbootapi.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="likes")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    Long id;

    @Column(name="post_id")
    Long post_id;

    @Column(name="user_id")
    Long user_id;
}
