package com.mangoplate.mangoplate.controller;

import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.request.ReviewRequest;
import com.mangoplate.mangoplate.domain.response.PostResponse;
import com.mangoplate.mangoplate.domain.response.Response;
import com.mangoplate.mangoplate.domain.response.ReviewResponse;
import com.mangoplate.mangoplate.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/review")
    public Response<ReviewResponse> getReview(@RequestBody ReviewRequest request){
        ReviewResponse reviewResponse = reviewService.get_review(request);
        return Response.success(reviewResponse);
    }

    @PostMapping("/review")
    public Response<ReviewResponse> createReview(@RequestBody ReviewRequest request){
        reviewService.write_review(request);
        return Response.success(null);
    }
    @PutMapping("/review")
    public Response<ReviewResponse> updateReview(@RequestBody ReviewRequest request){
        reviewService.change_review(request);
        return Response.success(null);
    }

    @DeleteMapping("/review")
    public Response<ReviewResponse> deletePost(@RequestBody ReviewRequest request) {

        reviewService.delete_review(request);
        return Response.success(null);
    }



}
