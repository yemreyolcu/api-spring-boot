package com.example.springbootapi.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long id;
    private Long post_id;
    private Long user_id;
    private String text;
}
