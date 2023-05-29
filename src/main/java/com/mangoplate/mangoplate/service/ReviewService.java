package com.mangoplate.mangoplate.service;

import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.entity.Review;
import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.request.ReviewRequest;
import com.mangoplate.mangoplate.domain.response.PostResponse;
import com.mangoplate.mangoplate.domain.response.ReviewResponse;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.repository.ReviewRepository;
import com.mangoplate.mangoplate.utill.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;
    public ReviewResponse get_review(ReviewRequest request) {
        String userId = JwtTokenUtils.getUserId(request.token(),secretKey);

        Review res = reviewRepository.findById(request.reviewId()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_REVIEW);
        });

        return new ReviewResponse(request.token(), request.content(), request.imageUrl(),
                request.post(), request.registeredAt(), request.updatedAt());

    }
}
