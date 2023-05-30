package com.mangoplate.mangoplate.domain.response;

import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.type.TasteRating;
import org.hibernate.tool.schema.internal.SchemaTruncatorImpl;

import java.sql.Timestamp;
import java.util.List;

public record ReviewResponse(
        String reviewCode,
        String content,
        List<String> imageUrl,
        TasteRating tasteRating,
        Post post,
        Timestamp registeredAt,
        Timestamp updatedAt
) {
}
