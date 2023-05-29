package com.mangoplate.mangoplate.domain.response;

import com.mangoplate.mangoplate.domain.entity.Post;

import java.sql.Timestamp;

public record ReviewResponse(
        String token,
        String content,
        String imageUrl,
        Post post,
        Timestamp registeredAt,
        Timestamp updatedAt
) {
}
