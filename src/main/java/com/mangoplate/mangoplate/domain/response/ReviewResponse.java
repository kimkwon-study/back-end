package com.mangoplate.mangoplate.domain.response;

import com.mangoplate.mangoplate.domain.entity.Post;
import org.hibernate.tool.schema.internal.SchemaTruncatorImpl;

import java.sql.Timestamp;

public record ReviewResponse(
        String reviewCode,
        String content,
        String imageUrl,
        Post post,
        Timestamp registeredAt,
        Timestamp updatedAt
) {
}
