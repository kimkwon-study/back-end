package com.mangoplate.mangoplate.controller;

import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.request.UserJoinRequest;
import com.mangoplate.mangoplate.domain.request.UserLoginRequest;
import com.mangoplate.mangoplate.domain.response.PostResponse;
import com.mangoplate.mangoplate.domain.response.Response;
import com.mangoplate.mangoplate.domain.response.UserRegisterResponse;
import com.mangoplate.mangoplate.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public Response<PostRequest> getPost(@RequestBody PostRequest request) {
        PostRequest postRequest = postService.get_post(request);
        return Response.success(postRequest);
    }

    @PostMapping("/post")
    public Response<PostResponse> createPost(@RequestBody PostRequest request) {
        postService.post_write(request);
        return Response.success(new PostResponse(request.getEntity().getUserEntity().getUserId(),request.restaurantName()));
    }


//    @GetMapping("/review")
//    public Response<ReviewResponse> getPost(@RequestBody ReviewRequest request){
//        ReviewResponse reviewResponse = userService.get_review(request.getReviewId);
//        return Response.success(postResponse);
//    }



//    @PostMapping("/review")
//    public Response<ReviewResponse> createReview(@RequestBody ReviewRequest request){
//        ReviewResponse reviewResponse = userService.review_write(request);
//        return Response.success(reviewResponse);
//    }



}
