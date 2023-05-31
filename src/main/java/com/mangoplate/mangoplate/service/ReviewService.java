package com.mangoplate.mangoplate.service;

import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.entity.Review;
import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.request.ReviewRequest;
import com.mangoplate.mangoplate.domain.response.PostResponse;
import com.mangoplate.mangoplate.domain.response.ReviewResponse;
import com.mangoplate.mangoplate.domain.response.Review_getResponse;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.repository.PostRepository;
import com.mangoplate.mangoplate.repository.ReviewRepository;
import com.mangoplate.mangoplate.utill.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;
    public Review_getResponse get_review(ReviewRequest request) {
//        String userId = JwtTokenUtils.getUserId(request.token(),secretKey);

        Review res = reviewRepository.findByReviewCode(request.reviewCode()).orElseThrow(()->{
            throw new ApplicationException(ErrorCode.NO_REVIEW);
        });

        Post post = postRepository.findByPostCode(request.postCode()).orElseThrow(()->{
            throw new ApplicationException(ErrorCode.NO_POST);
        });


//
        res.setPost(post);



        return new Review_getResponse( res.getReviewCode(),res.getContent(), res.getImageUrl(),res.getTaste(),
                res.getPost().getPostCode(), res.getRegisteredAt(), res.getUpdatedAt());

    }

    public void write_review(ReviewRequest request){
//        String userId = JwtTokenUtils.getUserId(request.token(), secretKey);

        if(request.reviewCode() == null || request.reviewCode().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_REVIEWCODE);
        }
        else if(request.taste()== null){
            throw new ApplicationException(ErrorCode.NO_TASTE);
        }
        else if(request.content() == null || request.content().isEmpty() || request.content().length() <20 ){
            throw new ApplicationException(ErrorCode.SHORT_CONTENT);
        }

        Post post = postRepository.findByPostCode(request.postCode()).orElseThrow(()->{
            throw new ApplicationException(ErrorCode.NO_POST);
        });

        Review review = Review.getEntity(request.reviewCode(),
                request.updatedAt(),request.registeredAt(),request.content(),request.imageUrl(),request.taste()
        );
        review.setPost(post);

        reviewRepository.save(review);

    }

    public ReviewResponse change_review(ReviewRequest request) {
//        String userId = JwtTokenUtils.getUserId(request.token(),secretKey);

        Review res = reviewRepository.findByReviewCode(request.reviewCode()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_REVIEW);
        });

        if(request.reviewCode() == null || request.reviewCode().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_REVIEWCODE);
        }
        else if(request.taste()== null){
            throw new ApplicationException(ErrorCode.NO_TASTE);
        }
        else if(request.content() == null || request.content().isEmpty() || request.content().length() <20 ){
            throw new ApplicationException(ErrorCode.SHORT_CONTENT);
        }

        Review updatereview = res;
        //System.out.println(updatePost.getRestaurantName());


        updatereview.setContent(request.content());
        updatereview.setImageUrl(request.imageUrl());
        updatereview.setTaste(request.taste());

        reviewRepository.save(updatereview);



        return new ReviewResponse( updatereview.getReviewCode(),updatereview.getContent(), updatereview.getImageUrl(),
                updatereview.getTaste(),updatereview.getPost(), updatereview.getRegisteredAt(), updatereview.getUpdatedAt());
    }

    public  void delete_review(ReviewRequest request){
        Review res = reviewRepository.findByReviewCode(request.reviewCode()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });
//        JwtTokenUtils.getUserId(request.token(), secretKey);

        Review review = res;

        reviewRepository.deleteById(review.getReviewId());

    }

}
