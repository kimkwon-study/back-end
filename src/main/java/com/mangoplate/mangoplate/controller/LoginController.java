package com.mangoplate.mangoplate.controller;

import com.mangoplate.mangoplate.domain.request.UserJoinRequest;
import com.mangoplate.mangoplate.domain.request.UserLoginRequest;
import com.mangoplate.mangoplate.domain.response.Response;
import com.mangoplate.mangoplate.domain.response.UserJoinResponse;
import com.mangoplate.mangoplate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public Response<UserJoinResponse> register(@RequestBody UserJoinRequest request){
        userService.user_join(request);
        return Response.success(new UserJoinResponse(request.userId(),request.nickname()));
    }

    @PostMapping("/login")
    public Response<UserJoinResponse> register(@RequestBody UserLoginRequest request){
        UserJoinResponse userLoginResponse = userService.user_login(request);
        return Response.success(userLoginResponse);
    }

}