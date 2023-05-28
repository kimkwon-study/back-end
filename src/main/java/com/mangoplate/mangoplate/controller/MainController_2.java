package com.mangoplate.mangoplate.controller;

import com.mangoplate.mangoplate.domain.request.UserJoinRequest;
import com.mangoplate.mangoplate.domain.request.UserLoginRequest;
import com.mangoplate.mangoplate.domain.response.Response;
import com.mangoplate.mangoplate.domain.response.UserRegisterResponse;
import com.mangoplate.mangoplate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MainController_2 {

    private final UserService userService;

    @PostMapping("/register")
    public Response<UserRegisterResponse> register(@RequestBody UserJoinRequest request){
        userService.user_join(request);
        return Response.success(new UserRegisterResponse(request.userId(),request.nickname()));
    }

    @PostMapping("/login")
    public Response<UserRegisterResponse> register(@RequestBody UserLoginRequest request){
        UserRegisterResponse userLoginResponse = userService.user_login(request);
        return Response.success(userLoginResponse);
    }

//    @GetMapping("/post")
//    public Response<PostResponse> getPost(@RequestBody PostRequest request){
//        PostResponse postResponse = userService.get_post(request.getPostId);
//        return Response.success(postResponse);
//    }
//    @GetMapping("/review")
//    public Response<ReviewResponse> getPost(@RequestBody ReviewRequest request){
//        ReviewResponse reviewResponse = userService.get_review(request.getReviewId);
//        return Response.success(postResponse);
//    }


//    @PostMapping("/post")
//    public Response<PostResponse> createPost(@RequestBody PostRequest request){
//        PostResponse postResponse = userService.post_write(request);
//        return Response.success(postResponse);
//    }
//
//    @PostMapping("/review")
//    public Response<ReviewResponse> createReview(@RequestBody ReviewRequest request){
//        ReviewResponse reviewResponse = userService.review_write(request);
//        return Response.success(reviewResponse);
//    }



}
