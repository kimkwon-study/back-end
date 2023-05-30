package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.type.TasteRating;

import java.sql.Timestamp;
import java.util.List;

public record ReviewRequest(
        Long reviewId,
        String reviewCode,
        String content,
        List<String> imageUrl,
        TasteRating taste,
        Post post,
        Timestamp registeredAt,
        Timestamp updatedAt
) {
}
