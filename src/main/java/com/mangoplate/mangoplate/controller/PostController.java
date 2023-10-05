package com.mangoplate.mangoplate.controller;

import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.response.PostResponse;
import com.mangoplate.mangoplate.domain.response.Response;
import com.mangoplate.mangoplate.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public Response<PostResponse> getPost(@RequestBody PostRequest request) {
        PostResponse postResponse = postService.get_post(request);
        return Response.success(postResponse);
    }

    @PostMapping("/post")
    public Response<PostResponse> createPost(@RequestBody PostRequest request) {
        postService.write_post(request);
        return Response.success(null);

    }

    @PutMapping("/post")
    public Response<PostResponse>updatePost(@RequestBody PostRequest request) {
        postService.change_post(request);

        return Response.success(null);

    }


    @DeleteMapping("/post")
    public Response<PostResponse> deletePost(@RequestBody PostRequest request) {

        postService.delete_post(request);
        return Response.success(null);
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
