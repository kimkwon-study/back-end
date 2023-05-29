package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.entity.Post;

import java.sql.Timestamp;

public record ReviewRequest(
        String token,
        Long reviewId,
        String content,
        String imageUrl,
        Post post,
        Timestamp registeredAt,
        Timestamp updatedAt
) {
}
