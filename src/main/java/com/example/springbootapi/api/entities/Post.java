package com.example.springbootapi.api.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Table(name = "posts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name="title")
    private String title;

    @Column(name = "description", length = 550)
    private String description;
}
