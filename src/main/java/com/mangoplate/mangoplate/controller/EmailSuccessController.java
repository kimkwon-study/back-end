package com.mangoplate.mangoplate.controller;

import com.mangoplate.mangoplate.domain.request.EmailRequest;
import com.mangoplate.mangoplate.domain.response.Response;
import com.mangoplate.mangoplate.service.EmailSuccessService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailSuccessController {

    private final EmailSuccessService service;


    @PostMapping("/email")
    public Response<String> email(@RequestBody EmailRequest request) {
        service.create(request.email());
        return Response.success("이메일이 성공적으로 서버에 전달되었습니다.");
    }

    @PostMapping("/email-check")
    public Response<String> check_email(@RequestBody EmailRequest request){
        System.out.println(request.successKey());
        service.check(request);
        return Response.success("이메일이 성공적으로 인증 되었습니다.");
    }

}
