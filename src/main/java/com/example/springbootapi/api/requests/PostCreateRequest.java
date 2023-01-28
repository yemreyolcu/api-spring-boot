package com.example.springbootapi.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    private Long id;
    private String title;
    private String description;
    private Long user_id;
}
