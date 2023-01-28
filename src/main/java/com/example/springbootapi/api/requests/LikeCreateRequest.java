package com.example.springbootapi.api.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeCreateRequest {
    private Long id;
    private Long user_id;
    private Long post_id;
    private boolean status;
}
