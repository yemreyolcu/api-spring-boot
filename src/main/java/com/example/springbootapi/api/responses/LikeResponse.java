package com.example.springbootapi.api.responses;

import com.example.springbootapi.api.entities.Like;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeResponse {
    private Long id;
    private Long user_id;
    private String username;
    private Long post_id;
    private String title;
    private String description;
    private boolean status;

    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.user_id = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.post_id = entity.getPost().getId();
        this.title = entity.getPost().getTitle();
        this.description = entity.getPost().getDescription();
        this.status = entity.isStatus();
    }
}
