package com.example.springbootapi.responses;


import com.example.springbootapi.entities.Post;
import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private Long user_id;
    private String username;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.title = entity.getTitle();
        this.user_id = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
    }
}
