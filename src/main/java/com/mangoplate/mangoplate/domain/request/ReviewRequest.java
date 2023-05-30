package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.entity.Post;

import java.sql.Timestamp;

public record ReviewRequest(
        Long reviewId,
        String reviewCode,
        String content,
        String imageUrl,
        Post post,
        Timestamp registeredAt,
        Timestamp updatedAt
) {
}
