package com.mangoplate.mangoplate.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //로그인
    LOGIN_NO_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 틀렸습니다."),
    LOGIN_NO_JOIN(HttpStatus.UNAUTHORIZED,"가입되지 않은 아이디입니다."),
    //회원가입
    REGISTER_SAME_ID(HttpStatus.UNAUTHORIZED,"동일한 아이디가 존재합니다."),
    NO_POST(HttpStatus.UNAUTHORIZED,"게시글이 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
