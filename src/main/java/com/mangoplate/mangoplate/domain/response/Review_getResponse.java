package com.mangoplate.mangoplate.domain.response;

import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.type.TasteRating;

import java.sql.Timestamp;
import java.util.List;

public record Review_getResponse(
        String reviewCode,
        String content,
        List<String> imageUrl,
        TasteRating tasteRating,
        String postCode,
        Timestamp registeredAt,
        Timestamp updatedAt
) {
}
